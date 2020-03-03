package be.artisjaap.common.cucumber;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public abstract class AbstractWorld {
    @Autowired
    protected World world;

    public void increase(){
        world.increase();
    }

    public void save(String key, Object object){
        world.save(key, object);
    }

    public <T> Optional<T> get(String key){
        return world.get(key);
    }

}
