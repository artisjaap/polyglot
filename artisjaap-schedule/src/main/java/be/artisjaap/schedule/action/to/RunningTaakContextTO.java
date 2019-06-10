package be.artisjaap.schedule.action.to;

import be.artisjaap.schedule.model.TaakParameterEntry;

import java.util.List;

public class RunningTaakContextTO {
    private final String code;
	private final String tijdFormatted;
	private final String omschrijving;
	private final Boolean custom;
	private final List<TaakParameterEntry> parameters;
	private final List<String> messages;
	public String getCode() {
		return code;
	}
	public String getTijdFormatted() {
		return tijdFormatted;
	}
	public String getOmschrijving() {
		return omschrijving;
	}
	public Boolean getCustom() {
		return custom;
	}
	public List<TaakParameterEntry> getParameters() {
		return parameters;
	}
	public List<String> getMessages() {
		return messages;
	}
	
	private RunningTaakContextTO(Builder builder) {
		code = builder.code;
		tijdFormatted = builder.tijdFormatted;
		omschrijving = builder.omschrijving;
		custom = builder.custom;
		parameters = builder.parameters;
		messages = builder.messages;
	}

	public static Builder newBuilder() {
		return new Builder();
	}

	public static Builder newBuilder(RunningTaakContextTO copy) {
		Builder builder = new Builder();
		builder.code = copy.code;
		builder.tijdFormatted = copy.tijdFormatted;
		builder.omschrijving = copy.omschrijving;
		builder.custom = copy.custom;
		builder.parameters = copy.parameters;
		builder.messages = copy.messages;
		return builder;
	}

	public static final class Builder {
		private String code;
		private String tijdFormatted;
		private String omschrijving;
		private Boolean custom;
		private List<TaakParameterEntry> parameters;
		private List<String> messages;

		private Builder() {
		}

		public Builder code(String val) {
			code = val;
			return this;
		}

		public Builder tijdFormatted(String val) {
			tijdFormatted = val;
			return this;
		}

		public Builder omschrijving(String val) {
			omschrijving = val;
			return this;
		}

		public Builder custom(Boolean val) {
			custom = val;
			return this;
		}

		public Builder parameters(List<TaakParameterEntry> val) {
			parameters = val;
			return this;
		}

		public Builder messages(List val) {
			messages = val;
			return this;
		}

		public RunningTaakContextTO build() {
			return new RunningTaakContextTO(this);
		}
	}

	
}
