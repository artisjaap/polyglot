package be.artisjaap.schedule.model;

import be.artisjaap.schedule.enums.TriggerType;

public interface ScheduledTaskConfig {

	String getCode();

	Boolean getActief();

	TriggerType getTriggerType();

	String getTriggerSetting();

}