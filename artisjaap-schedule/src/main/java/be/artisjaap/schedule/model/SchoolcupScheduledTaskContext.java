package be.artisjaap.schedule.model;

import be.artisjaap.common.utils.LocalDateUtils;
import be.artisjaap.schedule.enums.TaskStatus;
import be.artisjaap.schedule.enums.TriggerType;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class SchoolcupScheduledTaskContext {

	private SchoolcupScheduledTask antheaScheduledTask;
	private ScheduledTaskConfig scheduledTaskConfig;
	private ScheduledFuture<?> scheduledFuture;

	public SchoolcupScheduledTaskContext(SchoolcupScheduledTask task) {
		this.antheaScheduledTask = task;
	}

	public void setScheduledFuture(ScheduledFuture<?> scheduledFuture) {
		this.scheduledFuture = scheduledFuture;
	}

	public void setScheduledTaskConfig(ScheduledTaskConfig scheduledTaskConfig) {
		this.scheduledTaskConfig = scheduledTaskConfig;
	}

	public ScheduledTaskConfig getScheduledTaskConfig() {
		return scheduledTaskConfig;
	}

	public SchoolcupTask runnable() {
		return antheaScheduledTask;
	}

	public String taskCategoryCode() {
		return antheaScheduledTask.getTaskCategory().name();
	}

	public boolean actief() {
		return Boolean.TRUE.equals(scheduledTaskConfig.getActief());
	}

	public TriggerType triggerType() {
		return scheduledTaskConfig.getTriggerType();
	}

	public String triggerSetting() {
		return scheduledTaskConfig.getTriggerSetting();
	}

	public String code() {
		return antheaScheduledTask.getCode();
	}

	public String omschrijving() {
		return antheaScheduledTask.getOmschrijving();
	}

	public TaskStatus status() {
		return antheaScheduledTask.getTaskStatus();
	}

	public void onderbreken() {
		antheaScheduledTask.onderbreken();
	}

	public void stopScheduler() {
		if (scheduledFuture != null) {
			scheduledFuture.cancel(false);
		}
	}

	public Date nextRunTime() {
		if (scheduledFuture == null) {
			return null;
		}

		long delay = scheduledFuture.getDelay(TimeUnit.MINUTES);
		if (delay < 1) {
			return null;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(LocalDateUtils.nowAsDate());
		calendar.add(Calendar.MINUTE, (int) delay);
		calendar.add(Calendar.MINUTE, 1);
		return calendar.getTime();
	}
}