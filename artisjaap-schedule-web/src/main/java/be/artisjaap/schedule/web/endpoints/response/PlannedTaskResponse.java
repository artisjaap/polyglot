package be.artisjaap.schedule.web.endpoints.response;

import be.artisjaap.schedule.action.to.GeplandeTaakTO;
import be.artisjaap.schedule.enums.TaskStatus;
import be.artisjaap.schedule.enums.TriggerType;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class PlannedTaskResponse {
    private String code;
    private String omschrijving;
    private TriggerType triggerType;
    private String triggerSetting;
    private Boolean actief;
    private TaskStatus status;
    private Date nextRunTime;
    private String taskCategoryCode;

    private PlannedTaskResponse(Builder builder) {
        code = builder.code;
        omschrijving = builder.omschrijving;
        triggerType = builder.triggerType;
        triggerSetting = builder.triggerSetting;
        actief = builder.actief;
        status = builder.status;
        nextRunTime = builder.nextRunTime;
        taskCategoryCode = builder.taskCategoryCode;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static PlannedTaskResponse from(GeplandeTaakTO geplandeTaakTO) {
        return PlannedTaskResponse.newBuilder()
                .withActief(geplandeTaakTO.getActief())
                .withCode(geplandeTaakTO.getCode())
                .withNextRunTime(geplandeTaakTO.getNextRunTime())
                .withOmschrijving(geplandeTaakTO.getOmschrijving())
                .withStatus(geplandeTaakTO.getStatus())
                .withTaskCategoryCode(geplandeTaakTO.getTaskCategoryCode())
                .withTriggerSetting(geplandeTaakTO.getTriggerSetting())
                .withTriggerType(geplandeTaakTO.getTriggerType())
                .build();
    }


    public static List<PlannedTaskResponse> from(List<GeplandeTaakTO> geplandeTaakTO) {
        return geplandeTaakTO.stream().map(PlannedTaskResponse::from)
                .collect(Collectors.toList());

    }


    public String getCode() {
        return code;
    }

    public String getOmschrijving() {
        return omschrijving;
    }

    public TriggerType getTriggerType() {
        return triggerType;
    }

    public String getTriggerSetting() {
        return triggerSetting;
    }

    public Boolean getActief() {
        return actief;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public Date getNextRunTime() {
        return nextRunTime;
    }

    public String getTaskCategoryCode() {
        return taskCategoryCode;
    }

    public static final class Builder {
        private String code;
        private String omschrijving;
        private TriggerType triggerType;
        private String triggerSetting;
        private Boolean actief;
        private TaskStatus status;
        private Date nextRunTime;
        private String taskCategoryCode;

        private Builder() {
        }

        public Builder withCode(String code) {
            this.code = code;
            return this;
        }

        public Builder withOmschrijving(String omschrijving) {
            this.omschrijving = omschrijving;
            return this;
        }

        public Builder withTriggerType(TriggerType triggerType) {
            this.triggerType = triggerType;
            return this;
        }

        public Builder withTriggerSetting(String triggerSetting) {
            this.triggerSetting = triggerSetting;
            return this;
        }

        public Builder withActief(Boolean actief) {
            this.actief = actief;
            return this;
        }

        public Builder withStatus(TaskStatus status) {
            this.status = status;
            return this;
        }

        public Builder withNextRunTime(Date nextRunTime) {
            this.nextRunTime = nextRunTime;
            return this;
        }

        public Builder withTaskCategoryCode(String taskCategoryCode) {
            this.taskCategoryCode = taskCategoryCode;
            return this;
        }

        public PlannedTaskResponse build() {
            return new PlannedTaskResponse(this);
        }
    }
}
