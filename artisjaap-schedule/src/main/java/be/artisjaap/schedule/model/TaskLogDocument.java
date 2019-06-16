package be.artisjaap.schedule.model;

import be.artisjaap.common.model.AbstractDocument;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "taak_log_document")
public class TaskLogDocument extends AbstractDocument {

	private TaskLogState state;
    private String gebruiker;
	private String taak;
	private Long duration;
	private String message;
	
	

	public TaskLogState getState() {
		return state;
	}

	public String getGebruiker() {
		return gebruiker;
	}

	public String getTaak() {
		return taak;
	}

	public Long getDuration() {
		return duration;
	}

	public String getMessage() {
		return message;
	}

	public static TaskLogDocument build() {
		return new TaskLogDocument();
	}

	public TaskLogDocument setStatus(TaskLogState state) {
		this.state = state;
		return this;
	}

	public TaskLogDocument setGebruiker(String gebruiker) {
		this.gebruiker = gebruiker;
		return this;
	}

	public TaskLogDocument setTaak(String taak) {
		this.taak = taak;
		return this;
	}

	public TaskLogDocument entity() {
		return this;
	}

	public TaskLogDocument setDuration(Long duration) {
		this.duration = duration;
		return this;
	}

	public TaskLogDocument setBericht(String message) {
		this.message = message;
		return this;
	}
}
