package be.artisjaap.schedule.action;

import be.artisjaap.schedule.action.to.GeplandeTaakConfigTO;
import be.artisjaap.schedule.service.ScheduledTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UpdateConfig {
    @Autowired
    private ScheduledTaskService scheduledTaskService;

    public void forCode(GeplandeTaakConfigTO taskConfig){
        scheduledTaskService.updateGeplandeTaakConfiguratie(taskConfig );
    }
}
