package be.artisjaap.schedule.action;

import be.artisjaap.schedule.action.to.TaakJournalTO;
import be.artisjaap.schedule.service.ScheduledTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JournalForTask {
    @Autowired
    private ScheduledTaskService scheduledTaskService;

    public List<TaakJournalTO> withCode(String code){
        return scheduledTaskService.taakJournal(code, 5);
    }
}
