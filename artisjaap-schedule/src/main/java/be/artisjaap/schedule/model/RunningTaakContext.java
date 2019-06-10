package be.artisjaap.schedule.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RunningTaakContext {

	private final List<String> messages = new ArrayList<>();
    private final Boolean customized;
	private final TaakConfig config;
	
	public Boolean isCustomized() {
		return customized;
	}
	
	public void addMessage(String message){
		messages.add(message);
	}
	
	public List<String> getMessages() {
		return messages;
	}
	
	public TaakConfig getConfig() {
		return config;
	}
	
	public <T> Optional<T> parameter(String param){
		return Optional.ofNullable(config.property(param));
	}
	
	public RunningTaakContext(TaakConfig config, Boolean customized){
		this.config = config;
		this.customized = customized;
	}
}
