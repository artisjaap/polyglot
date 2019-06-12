package be.artisjaap.schedule.service;

import be.artisjaap.common.action.Context;
import be.artisjaap.schedule.action.assembler.GeplandeTaakConfigAssembler;
import be.artisjaap.schedule.action.assembler.TaakJournaalAssembler;
import be.artisjaap.schedule.action.assembler.TaakParametersAssembler;
import be.artisjaap.schedule.action.to.GeplandeTaakConfigTO;
import be.artisjaap.schedule.action.to.GeplandeTaakTO;
import be.artisjaap.schedule.action.to.TaakJournalTO;
import be.artisjaap.schedule.action.to.TaakParametersTO;
import be.artisjaap.schedule.enums.TriggerType;
import be.artisjaap.schedule.model.*;
import be.artisjaap.schedule.model.mongo.GeplandeTaakConfigRepository;
import be.artisjaap.schedule.model.mongo.TaakInfoRepository;
import be.artisjaap.schedule.model.mongo.TaskLogDocumentRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.Trigger;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static java.util.Collections.sort;

@Component
public class ScheduledTaskService {
    private static final Logger logger = LogManager.getLogger();

    @Resource
    private GeplandeTaakConfigAssembler geplandeTaakConfigAssembler;


    @Resource
    private GeplandeTaakConfigRepository geplandeTaakConfigRepository;

    @Resource
    private TaakParametersAssembler taakParametersAssembler;

    @Resource
    private TaakJournaalAssembler taakJournaalAssembler;

    @Resource
    private TaakInfoRepository taakInfoRepository;

    @Resource
    private TaskScheduler taskScheduler;

    @Resource
    private ExecutorService sessionAwareExecutor;

    @Resource
    private List<SchoolcupScheduledTask> scheduledTasks;

    private List<SchoolcupScheduledTaskContext> runningTasks = new ArrayList<>();

    private boolean disableScheduleService = false;

    @Resource
    private TaskLogDocumentRepository taskLogDocumentRepository;

    @Resource
    private ApplicationContext applicationContext;

    void startGeplandeTaken() {
        if (!disableScheduleService) {
            for (SchoolcupScheduledTask scheduledTask : scheduledTasks) {
                SchoolcupScheduledTaskContext holder = new SchoolcupScheduledTaskContext(scheduledTask);
                runningTasks.add(holder);

                ScheduledTaskConfig taakConfig = laadConfiguratieVoor(scheduledTask.getCode());
                if (taakConfig == null) {
                    taakConfig = new EmptyTaskConfig();
                }
                holder.setScheduledTaskConfig(taakConfig);
                logger.info("schedule task " + holder.code() + " with config " + holder.triggerSetting());
                scheduleTaak(holder);
            }
        }
    }

    void stopTaskService() {
        sessionAwareExecutor.shutdownNow();
    }

    public TaakParametersTO taakParametersVoorTaak(String code) {
        Optional<TaakInfo> taakInfo = taakInfoRepository.findByTaakCode(code);
        return taakInfo.map(TaakInfo::getConfig).map(e -> taakParametersAssembler.assembleTO(e,code)).orElseGet(() -> TaakParametersTO.newBuilder().build());
    }

    public List<TaakJournalTO> taakJournal(String code, Integer lastN) {
        Optional<TaakInfo> taakInfo = taakInfoRepository.findByTaakCode(code);
        if(!taakInfo.isPresent()){
            return new ArrayList<>();
        }
        List<TaakJournal> list = taakInfo.get().getJournal().stream().sorted((a, b)-> b.getUitgevoerdOp().compareTo(a.getUitgevoerdOp())).limit(lastN).collect(Collectors.toList());
        return taakJournaalAssembler.assembleTOs(list);

    }

    public GeplandeTaakConfigTO configuratieVoorTaak(String code) {
        Optional<GeplandeTaakConfig> taakConfig = geplandeTaakConfigRepository.findByCode(code);
        return taakConfig.map(geplandeTaakConfigAssembler::assembleTO).orElseGet(() -> GeplandeTaakConfigTO.buildByUserForCode(code));
    }

    public GeplandeTaakTO gelandeTaakSchedule(String code) {
        return runningTaskByCode(code).map(geplandeTaakConfigAssembler::voMakenVan).orElse(new GeplandeTaakTO());
    }

    private GeplandeTaakConfig laadConfiguratieVoor(String code) {

        Optional<GeplandeTaakConfig> taakConfigOptional = geplandeTaakConfigRepository.findByCode(code);
        return taakConfigOptional.orElse(null);
    }

    void scheduleTaak(SchoolcupScheduledTaskContext holder) {
        if (!holder.actief()) {
            return;
        }

        TriggerType triggerType = holder.triggerType();
        String triggerSetting = holder.triggerSetting();

        if(holder.triggerType() == TriggerType.CRON ){
            if(triggerSetting == null){
                logger.error("Cron task niet gescheduled, triggersetting was leeg voor taak: " + holder.code());
            }else {
                Trigger trigger = triggerType.createTriggerFor(triggerSetting);
                ScheduledFuture<?> runningTask = taskScheduler.schedule(holder.runnable(), trigger);
                logger.info("Task " + holder.code() + " will wait " + runningTask.getDelay(TimeUnit.SECONDS) + " seconds before start.");
                holder.setScheduledFuture(runningTask);
            }
        }
    }

    private List<SchoolcupScheduledTaskContext> getSortedRunningTasks() {
        List<SchoolcupScheduledTaskContext> sortedRunningTasks = new ArrayList<>();
        sortedRunningTasks.addAll(runningTasks);
        sort(sortedRunningTasks, new Comparator<SchoolcupScheduledTaskContext>() {

            @Override
            public int compare(SchoolcupScheduledTaskContext task1, SchoolcupScheduledTaskContext task2) {
                return task1.omschrijving().compareTo(task2.omschrijving());
            }

        });
        return sortedRunningTasks;
    }

    public List<GeplandeTaakTO> lijstVanGeplandeTaken() {
        List<GeplandeTaakTO> geplandeTakenVOs = new ArrayList<GeplandeTaakTO>();

        List<SchoolcupScheduledTaskContext> sortedRunningTasks = getSortedRunningTasks();

        for (SchoolcupScheduledTaskContext taskHolder : sortedRunningTasks) {
            GeplandeTaakTO vo = geplandeTaakConfigAssembler.voMakenVan(taskHolder);
            geplandeTakenVOs.add(vo);
        }

        return geplandeTakenVOs;
    }


    private Optional<SchoolcupScheduledTaskContext> runningTaskByCode(String code){
        return runningTasks.stream().filter(t -> t.code().equals(code)).findFirst();
    }

    public void startTaskMetParentTask(String code) {
        for (SchoolcupScheduledTaskContext taskHolder : runningTasks) {
            if (TriggerType.TASK.equals(taskHolder.triggerType()) && code.equals(taskHolder.triggerSetting())) {
                startTaskMetCode(taskHolder.code());
            }
        }

    }

    public void startTaskMetCode(String code) {
        SchoolcupScheduledTaskContext taskContext = taskContextMetCode(code);
        if (!taskContext.actief()) {
            return;
        }
        startTask(taskContext.runnable());
    }

    public void startTask(SchoolcupTask task) {
        String currentUser = Context.userId();
        try {
            TaskLogDocument builder = TaskLogDocument.build().setTaak(task.getCode()).setStatus(TaskLogState.SCHEDULED).setGebruiker(currentUser);
            taskLogDocumentRepository.save(builder.entity());
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }

        sessionAwareExecutor.execute(task);
    }

    public void stopTaskMetCode(String code) {
        SchoolcupScheduledTaskContext taskContext = taskContextMetCode(code);
        taskContext.onderbreken();
    }

    public SchoolcupScheduledTaskContext taskContextMetCode(String code) {
        for (SchoolcupScheduledTaskContext taskHolder : runningTasks) {
            if (taskHolder.code().equals(code)) {
                return taskHolder;
            }
        }
        throw new IllegalArgumentException("No scheduled task with code " + code);
    }

    public void updateGeplandeTaakConfiguratie(GeplandeTaakConfigTO model) {
        String code = model.getCode();
        GeplandeTaakConfig taakConfig = laadConfiguratieVoor(code);
        taakConfig = configuratieAanmakenIndienDezeNogNietBestaat(code, taakConfig);
        taakConfig.setActief(model.getActief());

        taakConfig.setTriggerType(model.getTriggerType());
        taakConfig.setTriggerSetting(model.getTriggerSetting());
        geplandeTaakConfigRepository.save(taakConfig);
        taakStoppenEnHerstarten(taakConfig);
    }

//	public void updateGeplandeTaakConfiguratie(GeplandeTaakTO model) {
//		GeplandeTaakConfig taakConfig = aanpassenTaakConfiguratieVoor(model);
//		taakStoppenEnHerstarten(taakConfig);
//	}

    private GeplandeTaakConfig aanpassenTaakConfiguratieVoor(GeplandeTaakTO model) {
        String code = model.getCode();
        GeplandeTaakConfig taakConfig = laadConfiguratieVoor(code);
        taakConfig = configuratieAanmakenIndienDezeNogNietBestaat(code, taakConfig);
        taakConfig.setActief(model.getActief());

        taakConfig.setTriggerType(model.getTriggerType());

        return taakConfig;
    }

    private GeplandeTaakConfig configuratieAanmakenIndienDezeNogNietBestaat(String code, GeplandeTaakConfig taakConfig) {
        if (taakConfig == null) {
            taakConfig = GeplandeTaakConfig.newBuilder()
                    .withCode(code)
                    .withActief(Boolean.TRUE)
                    .withTriggerType(TriggerType.USER).build();
            geplandeTaakConfigRepository.save(taakConfig);
        }
        return taakConfig;
    }

    //FIXME moet private zijn, oproepen na update config
    public void taakStoppenEnHerstarten(GeplandeTaakConfig newConfig) {
        SchoolcupScheduledTaskContext taskContext = taskContextMetCode(newConfig.getCode());
        taskContext.stopScheduler();
        try {
            taskContext.setScheduledTaskConfig(newConfig);
            scheduleTaak(taskContext);
        } catch (Exception ex) {
            logger.error("er is iets foutgelopen bij herstarten van de taak", ex);
        }
    }

    public void setDisableScheduleService(boolean disableScheduleService) {
        this.disableScheduleService = disableScheduleService;
    }

    void setTaskScheduler(TaskScheduler taskScheduler) {
        this.taskScheduler = taskScheduler;
    }

    void setSessionAwareExecutor(SessionAwareExecutor sessionAwareExecutor) {
        this.sessionAwareExecutor = sessionAwareExecutor;
    }

    public void nextRunVariables(String code, Map<String, Object> parameters){
        TaakInfo taakInfo = taakInfoRepository.findByTaakCode(code).orElse(new TaakInfo(code));

        taakInfo.getNextRunInfo().setParameterized(true);
        taakInfo.getNextRunInfo().setProperties(parameters);

        taakInfoRepository.save(taakInfo);

    }
}
