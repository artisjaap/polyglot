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

	private GeplandeTaakConfig(Builder builder) {
		buildCommon(builder);
		setCode(builder.code);
		setActief(builder.actief);
		setTriggerType(builder.triggerType);
		setTriggerSetting(builder.triggerSetting);
	}

	public static Builder newBuilder() {

		return new Builder();
	}

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


	public static final class Builder extends AbstractBuilder<Builder>{
		private String code;
		private Boolean actief;
		private TriggerType triggerType;
		private String triggerSetting;

		private Builder() {
		}

		public Builder withCode(String val) {
			code = val;
			return this;
		}

		public Builder withActief(Boolean val) {
			actief = val;
			return this;
		}

		public Builder withTriggerType(TriggerType val) {
			triggerType = val;
			return this;
		}

		public Builder withTriggerSetting(String val) {
			triggerSetting = val;
			return this;
		}

		public GeplandeTaakConfig build() {
			return new GeplandeTaakConfig(this);
		}
	}
}
