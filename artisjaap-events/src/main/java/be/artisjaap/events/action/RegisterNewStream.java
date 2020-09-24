package be.artisjaap.events.action;

import be.artisjaap.events.service.StreamServerEventService;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Component
public class RegisterNewStream {

    private final StreamServerEventService streamServerEventService;
    
    public RegisterNewStream(final StreamServerEventService streamServerEventService){
        this.streamServerEventService = streamServerEventService;
    }
    
    public SseEmitter forId(String id){
        return streamServerEventService.registerStream(id);
    }
}
