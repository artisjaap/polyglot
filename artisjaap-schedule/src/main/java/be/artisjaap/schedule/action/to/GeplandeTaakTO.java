package be.artisjaap.schedule.action.to;

import be.artisjaap.schedule.enums.TaskStatus;
import be.artisjaap.schedule.enums.TriggerType;

import java.util.Date;

public class GeplandeTaakTO {
    private String code;
	private String omschrijving;
	private TriggerType triggerType;
	private String triggerSetting;
	private Boolean actief;
	private TaskStatus status;
	private Date nextRunTime;
	private String taskCategoryCode;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getOmschrijving() {
		return omschrijving;
	}

	public void setOmschrijving(String omschrijving) {
		this.omschrijving = omschrijving;
	}

	public TriggerType getTriggerType() {
		return triggerType;
	}

	public void setTriggerType(TriggerType triggerType) {
		this.triggerType = triggerType;

	}

	public String getTriggerSetting() {
		return triggerSetting;
	}

	public void setTriggersetting(String triggerSetting) {
		this.triggerSetting = triggerSetting;
	}

	public Boolean getActief() {
		return actief;
	}

	public void setActief(boolean actief) {
		this.actief = actief;
	}

	public TaskStatus getStatus() {
		return status;
	}

	public void setStatus(TaskStatus status) {
		this.status = status;

	}

	public Date getNextRunTime() {
		return nextRunTime;
	}

	public void setNextRun(Date nextRunTime) {
		this.nextRunTime = nextRunTime;

	}

	public String getTaskCategoryCode() {
		return taskCategoryCode;
	}

	public void setTaskCategoryCode(String taskCategoryCode) {
		this.taskCategoryCode = taskCategoryCode;

	}

}
