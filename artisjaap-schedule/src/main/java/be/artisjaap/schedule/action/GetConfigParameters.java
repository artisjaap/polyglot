package be.artisjaap.schedule.action;

import be.artisjaap.schedule.action.to.TaakParametersTO;
import be.artisjaap.schedule.service.ScheduledTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GetConfigParameters {
    @Autowired
    private ScheduledTaskService scheduledTaskService;

    public TaakParametersTO forTask(String code){
        return scheduledTaskService.taakParametersVoorTaak(code);
    }
}
