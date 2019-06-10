package be.artisjaap.schedule.model;

import be.artisjaap.schedule.enums.TriggerType;

public class EmptyTaskConfig implements ScheduledTaskConfig {

	@Override
    public String getCode() {
		return "NO_CONFIG";
	}

	@Override
	public Boolean getActief() {
		return Boolean.FALSE;
	}

	@Override
	public TriggerType getTriggerType() {
		return TriggerType.USER;
	}

	@Override
	public String getTriggerSetting() {
		return "";
	}

}