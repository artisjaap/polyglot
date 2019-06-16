package be.artisjaap.migrate.model.action.to;

public class InitScriptStatusTO {

    private final String scriptVersie;
	private final String scriptOmschrijving;
	private final String scriptstatus;
	
	
	public String getScriptVersie() {
		return scriptVersie;
	}

	public String getScriptstatus() {
		return scriptstatus;
	}
	
	public String getScriptOmschrijving() {
		return scriptOmschrijving;
	}

	private InitScriptStatusTO(Builder builder) {
		scriptVersie = builder.scriptVersie;
		scriptstatus = builder.scriptstatus;
		scriptOmschrijving = builder.scriptOmschrijving;
	}

	public static Builder newBuilder() {
		return new Builder();
	}

	public static Builder newBuilder(InitScriptStatusTO copy) {
		Builder builder = new Builder();
		builder.scriptVersie = copy.scriptVersie;
		builder.scriptstatus = copy.scriptstatus;
		builder.scriptOmschrijving = copy.scriptOmschrijving;
		return builder;
	}

	public static final class Builder {
		private String scriptVersie;
		private String scriptstatus;
		private String scriptOmschrijving;

		private Builder() {
		}

		public Builder scriptVersie(String val) {
			scriptVersie = val;
			return this;
		}

		public Builder scriptstatus(String val) {
			scriptstatus = val;
			return this;
		}
		
		public Builder scriptOmschrijving(String val) {
			scriptOmschrijving = val;
			return this;
		}
		

		public InitScriptStatusTO build() {
			return new InitScriptStatusTO(this);
		}
	}

	
}
