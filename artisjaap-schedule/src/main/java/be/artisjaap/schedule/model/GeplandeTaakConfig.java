package be.artisjaap.schedule.model;

import be.artisjaap.common.model.AbstractDocument;
import be.artisjaap.schedule.enums.TriggerType;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "taak_geplande_taken_config")
public class GeplandeTaakConfig extends AbstractDocument implements ScheduledTaskConfig {

	private String code;
    private Boolean actief;
	private TriggerType triggerType;
	private String triggerSetting;

	@Override
	public String getCode() {
		return code;
	}

	@Override
	public Boolean getActief() {
		return actief;
	}

	@Override
	public TriggerType getTriggerType() {
		return triggerType;
	}

	@Override
	public String getTriggerSetting() {
		return triggerSetting;
	}

	public void setTriggerSetting(String triggerSetting) {
		this.triggerSetting = triggerSetting;
	}

	public void setActief(Boolean actief) {
		this.actief = actief;

	}

	public void setTriggerType(TriggerType triggerType) {
		this.triggerType = triggerType;

	}

	public void setCode(String code) {
		this.code = code;

	}

}
