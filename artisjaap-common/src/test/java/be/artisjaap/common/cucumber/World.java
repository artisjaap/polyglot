package be.artisjaap.common.cucumber;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static io.cucumber.spring.CucumberTestContext.SCOPE_CUCUMBER_GLUE;

@Scope(SCOPE_CUCUMBER_GLUE)
@Component
public class World {

    private Map<String, Object> objects = new HashMap<>();
    private int counter = 0;

    public void increase() {
        counter++;
    }

    public int getCounter() {
        return counter;
    }

    public void save(String key, Object object){
        objects.put(key, object);
    }

    public <T> Optional<T> get(String key){
        return Optional.ofNullable(objects.get(key))
                .map(o -> (T) o);
    }
}
