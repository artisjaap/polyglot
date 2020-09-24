package be.artisjaap.events.action;

import be.artisjaap.events.service.StreamServerEventService;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class BroadcastSse {

    private final StreamServerEventService streamServerEventService;
    
    public BroadcastSse(final StreamServerEventService streamServerEventService) {
        this.streamServerEventService = streamServerEventService;
    }
    
    public void object(Object object){
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(() -> {
            SseEmitter.SseEventBuilder event = SseEmitter.event().data(object);
            
            streamServerEventService.broadcast(object);
        });
    }
}
