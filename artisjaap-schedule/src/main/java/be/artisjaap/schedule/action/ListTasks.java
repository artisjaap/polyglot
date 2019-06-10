package be.artisjaap.schedule.action;

import be.artisjaap.schedule.action.to.GeplandeTaakTO;
import be.artisjaap.schedule.service.ScheduledTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ListTasks {

    @Autowired
    private ScheduledTaskService scheduledTaskService;

    public List<GeplandeTaakTO> allTasks(){
        return scheduledTaskService.lijstVanGeplandeTaken();
    }
}
