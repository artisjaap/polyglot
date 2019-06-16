package be.artisjaap.schedule.action.assembler;

import be.artisjaap.common.action.Assembler;
import be.artisjaap.schedule.action.to.GeplandeTaakConfigTO;
import be.artisjaap.schedule.action.to.GeplandeTaakTO;
import be.artisjaap.schedule.enums.TaskStatus;
import be.artisjaap.schedule.enums.TriggerType;
import be.artisjaap.schedule.model.GeplandeTaakConfig;
import be.artisjaap.schedule.model.SchoolcupScheduledTaskContext;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class GeplandeTaakConfigAssembler implements Assembler<GeplandeTaakConfigTO, GeplandeTaakConfig> {

    @Override
    public GeplandeTaakConfig assembleEntity(GeplandeTaakConfigTO t) {
        return GeplandeTaakConfig.newBuilder()
                .withActief(t.getActief())
                .withCode(t.getCode())
                .withTriggerSetting(t.getTriggerSetting())
                .withTriggerType(t.getTriggerType())
                .build();

    }

    @Override
    public GeplandeTaakConfigTO assembleTO(GeplandeTaakConfig e) {
        return GeplandeTaakConfigTO.newBuilder()
                .code(e.getCode())
                .triggerType(e.getTriggerType())
                .triggerSetting(e.getTriggerSetting())
                .actief(e.getActief())
                .build();

    }

    public GeplandeTaakTO voMakenVan(SchoolcupScheduledTaskContext taskContext) {
        GeplandeTaakTO vo = new GeplandeTaakTO();
        vo.setCode(taskContext.code());
        vo.setOmschrijving(taskContext.omschrijving());

        TriggerType triggerType = taskContext.triggerType();
        vo.setTriggerType(triggerType);

        vo.setTriggersetting(taskContext.triggerSetting());

        vo.setActief(taskContext.actief());

        TaskStatus status = taskContext.status();
        vo.setStatus(status);

        Date nextRunTime = taskContext.nextRunTime();
        vo.setNextRun(nextRunTime);

        vo.setTaskCategoryCode(taskContext.taskCategoryCode());

        return vo;
    }

}
