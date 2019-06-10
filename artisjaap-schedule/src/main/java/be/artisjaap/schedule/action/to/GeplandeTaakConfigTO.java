package be.artisjaap.schedule.action.to;

import be.artisjaap.common.model.ReferenceableTO;
import be.artisjaap.schedule.enums.TriggerType;

public class GeplandeTaakConfigTO extends ReferenceableTO {
    private final String code;
	private final Boolean actief;
	private final TriggerType triggerType;
	private final String triggerSetting;
	
	
	public String getCode() {
		return code;
	}

	public Boolean getActief() {
		return actief;
	}

	public TriggerType getTriggerType() {
		return triggerType;
	}

	public String getTriggerSetting() {
		return triggerSetting;
	}

	private GeplandeTaakConfigTO(Builder builder) {
		code = builder.code;
		actief = builder.actief;
		triggerType = builder.triggerType;
		triggerSetting = builder.triggerSetting;
	}
	
	public static GeplandeTaakConfigTO buildByUserForCode(String code){
		return newBuilder()
			.actief(false)
			.code(code)
			.triggerSetting("")
			.triggerType(TriggerType.USER)
			.build();
	}

	public static Builder newBuilder() {
		return new Builder();
	}

	public static Builder newBuilder(GeplandeTaakConfigTO copy) {
		Builder builder = new Builder();
		builder.code = copy.code;
		builder.actief = copy.actief;
		builder.triggerType = copy.triggerType;
		builder.triggerSetting = copy.triggerSetting;
		return builder;
	}

	public static final class Builder extends AbstractBuilder<Builder> {
		private String code;
		private Boolean actief;
		private TriggerType triggerType;
		private String triggerSetting;

		private Builder() {
		}

		public Builder code(String val) {
			code = val;
			return this;
		}

		public Builder actief(Boolean val) {
			actief = val;
			return this;
		}

		public Builder triggerType(TriggerType val) {
			triggerType = val;
			return this;
		}

		public Builder triggerSetting(String val) {
			triggerSetting = val;
			return this;
		}

		public GeplandeTaakConfigTO build() {
			return new GeplandeTaakConfigTO(this);
		}
	}

	

}
