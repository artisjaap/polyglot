package be.artisjaap.schedule.model;

import be.artisjaap.common.service.Context;
import be.artisjaap.common.utils.LocalDateUtils;
import be.artisjaap.schedule.action.assembler.TaakParameterEntryAssembler;
import be.artisjaap.schedule.action.to.RunningTaakContextTO;
import be.artisjaap.schedule.enums.PropertyType;
import be.artisjaap.schedule.model.mongo.TaakInfoRepository;
import be.artisjaap.schedule.model.mongo.TaskLogDocumentRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static be.artisjaap.common.utils.LocalDateUtils.durationInMiliseconds;

public abstract class AbstractSchoolcupTask implements SchoolcupTask {
	private static final Logger LOG = LogManager.getLogger();

	@Resource
	private TaskLogDocumentRepository taskLogDocumentRepository;

	@Resource
	private TaakInfoRepository taakInfoRepository;

//	@Resource
//	private CommunicatieService communicatieService;

	@Resource
	private TaakParameterEntryAssembler taakParameterEntryAssembler;

	@Override
	public void run() {
		String code = getCode();
		LocalDateTime startTime = LocalDateUtils.now();
		try {
			LOG.info("Start " + code);
			TaakInfo taakInfo = taakInfoRepository.findByTaakCode(code).orElse(new TaakInfo(code));
			NextRunInfo nextRunInfo = taakInfo.getNextRunInfo();
			Map<String, Object> params = merge(taakInfo, nextRunInfo);

			RunningTaakContext context = new RunningTaakContext(TaakConfig.forParameters(params), nextRunInfo.getParameterized());

			logRunning(getUserCode());
			execute(context);

			nextRunInfo.setParameterized(false);
			long duration = durationInMiliseconds(startTime);

			TaakJournal journal = TaakJournal.newBuilder()
					.messages(context.getMessages())
					.specifiekeConfig(context.getConfig())
					.tijdGelopen(duration)
					.uitgevoerdOp(LocalDateUtils.nowAsDate())
					.build();
			taakInfo.addJournal(journal);
			taakInfoRepository.save(taakInfo);

			RunningTaakContextTO taakContext = RunningTaakContextTO.newBuilder()
					.code(code)
					.custom(nextRunInfo.getParameterized())
					.messages(context.getMessages())
					.omschrijving(getOmschrijving())
					.parameters(createParameters(nextRunInfo.getProperties()))
					.tijdFormatted(LocalDateUtils.msToFormattedTime(duration))
					.build();

		//	communicatieService.verstuurTaakGelopen(taakContext);

			logFinished(getUserCode(), duration);
		} catch (Exception e) {
			long duration = durationInMiliseconds(startTime);
			LOG.error("Onbekend fout opgetreden in taak " + code, e);
			logFailed(getUserCode(), e.getMessage(), duration);
		} finally {
			LOG.info("Stop " + code);
		}
	}

	private List<TaakParameterEntry> createParameters(Map<String, Object> properties) {
		return properties.entrySet().stream().map(e -> TaakParameterEntry.from(e.getKey(), e.getValue())).collect(Collectors.toList());
	}

	private Map<String, Object> merge(TaakInfo taakInfo, NextRunInfo nextRunInfo) {
		Map<String, Object> config = new HashMap<>();


		List<TaakParameterEntry> properties = taakInfo.getConfig().alleProperties();
		for(TaakParameterEntry p : properties){

			String key = p.getKey();
			Object origValue = p.getValue();
			config.put(key,  origValue);


			if(nextRunInfo.getParameterized()){
				if(nextRunInfo.getProperties().containsKey(key)){
					String value = nextRunInfo.getProperties().get(key).toString();
					PropertyType type = PropertyType.typeOf(origValue);

					switch(type){
						case BOOLEAN: config.put(key, Boolean.valueOf(value)); break;
						case DOUBLE:  config.put(key, Double.valueOf(value)); break;
						case INTEGER:  config.put(key, Integer.valueOf(value)); break;
						case STRING: config.put(key, value); break;

					}
				}

			}
		}
		return config;
	}

	public abstract void execute(RunningTaakContext context);

	public final void logRunning(String userCode) {
		log(TaskLogDocument.build()
				.setStatus(TaskLogState.RUNNING)
				.setGebruiker(userCode)
				.setTaak(getCode())
				.entity());
	}

	public final void logFinished(String userCode, Long duration) {
		log(TaskLogDocument.build()
				.setStatus(TaskLogState.FINISHED)
				.setGebruiker(userCode)
				.setTaak(getCode())
				.setDuration(duration)
				.entity());
	}

	public final void logFailed(String userCode, String message, Long duration) {
		log(TaskLogDocument.build()
				.setStatus(TaskLogState.FAILED)
				.setGebruiker(userCode)
				.setTaak(getCode())
				.setBericht(message)
				.setDuration(duration)
				.entity());
	}

	public final void log(TaskLogDocument doc) {
		try {
			taskLogDocumentRepository.save(doc);
		} catch (Exception ex) {
			LOG.error(ex.getMessage());
		}
	}

	public final String getUserCode() {
		return Context.userId();
	}

}
