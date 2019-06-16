package be.artisjaap.schedule.model;

import be.artisjaap.schedule.enums.TaskCategory;
import be.artisjaap.schedule.enums.TaskStatus;

public interface SchoolcupScheduledTask extends SchoolcupTask {

	String getOmschrijving();

	void onderbreken();

	TaskCategory getTaskCategory();

	TaskStatus getTaskStatus();

}
