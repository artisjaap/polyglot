package be.artisjaap.events.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class StreamServerEventService {

    private final static long ONE_DAY = 24 * 60 * 60 * 1000;
    
    private Map<String, List<SseEmitter>> eventStreams = new HashMap();
    
    public SseEmitter registerStream(String clientId){
        SseEmitter emitter = new SseEmitter(ONE_DAY);
        List<SseEmitter> sseEmitters = eventStreams.computeIfAbsent(clientId, key -> new ArrayList<>());
        sseEmitters.add(emitter);

        emitter.onCompletion(() -> {
            synchronized (this.eventStreams){
                this.eventStreams.remove(emitter);
            }
        });
        
        return emitter;
    }
    
    private List<SseEmitter> all() {
        return this.eventStreams.values().stream().flatMap(l -> l.stream()).collect(Collectors.toList());
    }
    
    public void broadcast(Object object){
        
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String data =  objectMapper.writeValueAsString(object);

            all().forEach(sseEmitter -> {
                try {
                    sseEmitter.send(data, MediaType.TEXT_EVENT_STREAM);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        
    }
    
    
}
