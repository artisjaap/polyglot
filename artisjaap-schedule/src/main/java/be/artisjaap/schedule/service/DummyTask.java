package be.artisjaap.schedule.service;

import be.artisjaap.common.service.Context;
import be.artisjaap.schedule.enums.TaskCategory;
import be.artisjaap.schedule.enums.TaskStatus;
import be.artisjaap.schedule.model.AbstractSchoolcupTask;
import be.artisjaap.schedule.model.RunningTaakContext;
import be.artisjaap.schedule.model.SchoolcupScheduledTask;
import org.springframework.stereotype.Component;

@Component
public class DummyTask extends AbstractSchoolcupTask implements SchoolcupScheduledTask {

	@Override
    public String getCode() {
		return "DUMMY";
	}

	@Override
	public void execute(RunningTaakContext context) {
		
		context.addMessage("Een dummytaak heeft geen enkel functioneel nut {paramameterized?" + context.isCustomized() +"}");
		System.out.println("Running..." + Context.userId());
	}

	@Override
	public String getOmschrijving() {
		return "Dummy";
	}

	@Override
	public void onderbreken() {

	}

	@Override
	public TaskCategory getTaskCategory() {
		return TaskCategory.GENERAL;
	}

	@Override
	public TaskStatus getTaskStatus() {
		return null;
	}

}
