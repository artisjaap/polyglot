package be.artisjaap.schedule.enums;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.scheduling.support.PeriodicTrigger;

public enum TriggerType {
    PERIODIC,
	CRON,
	USER,
	TASK;

	public Trigger createTriggerFor(String configuration) {
		switch (this) {
		case PERIODIC:
			long longValue = Long.parseLong(configuration);
			PeriodicTrigger periodicTrigger = new PeriodicTrigger(longValue, TimeUnit.MINUTES);
			periodicTrigger.setFixedRate(true);
			return periodicTrigger;
		case CRON:
			return new CronTrigger(configuration);
		default:
			return new NoTrigger();
		}

	}

	public boolean validConfiguration(String configuration) {
		try {
			createTriggerFor(configuration);
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

	private static class NoTrigger implements org.springframework.scheduling.Trigger {
		@Override
		public Date nextExecutionTime(TriggerContext triggerContext) {
			return null;
		}

	}
}
