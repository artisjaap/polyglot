package be.artisjaap.schedule.action;

import be.artisjaap.schedule.service.ScheduledTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StartTask {

    @Autowired
    private ScheduledTaskService scheduledTaskService;

    public void withCode(String code){
        scheduledTaskService.startTaskMetCode(code);
    }

    public void forSettings(String code){
        //TODO
    }

}
