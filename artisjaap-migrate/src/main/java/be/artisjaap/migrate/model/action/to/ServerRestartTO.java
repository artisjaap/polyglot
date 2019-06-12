package be.artisjaap.migrate.model.action.to;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ServerRestartTO {
    private final LocalDateTime datumRestart;
	private final String huidigeVersie;
	private final String mongodbName;
	private final List<InitScriptStatusTO> initScripts;
	
	
	
	public LocalDateTime getDatumRestart() {
		return datumRestart;
	}

	public String getHuidigeVersie() {
		return huidigeVersie;
	}

	public String getMongodbName() {
		return mongodbName;
	}

	public List<InitScriptStatusTO> getInitScripts() {
		return initScripts;
	}

	private ServerRestartTO(Builder builder) {
		datumRestart = builder.datumRestart;
		huidigeVersie = builder.huidigeVersie;
		initScripts = builder.initScripts;
		mongodbName = builder.mongodbName;
	}

	public static Builder newBuilder() {
		return new Builder();
	}

	public static Builder newBuilder(ServerRestartTO copy) {
		Builder builder = new Builder();
		builder.datumRestart = copy.datumRestart;
		builder.huidigeVersie = copy.huidigeVersie;
		builder.initScripts = copy.initScripts;
		builder.mongodbName = copy.mongodbName;
		return builder;
	}

	public static final class Builder {
		private LocalDateTime datumRestart;
		private String huidigeVersie;
		private String mongodbName;
		private List<InitScriptStatusTO> initScripts = new ArrayList<>();

		private Builder() {
		}

		public Builder datumRestart(LocalDateTime val) {
			datumRestart = val;
			return this;
		}

		public Builder huidigeVersie(String val) {
			huidigeVersie = val;
			return this;
		}
		
		public Builder mongodbName(String val) {
			mongodbName = val;
			return this;
		}

		public Builder initScripts(List<InitScriptStatusTO> val) {
			initScripts = val;
			return this;
		}
		
		public Builder addInitScript(InitScriptStatusTO val) {
			initScripts.add(val);
			return this;
		}

		public ServerRestartTO build() {
			return new ServerRestartTO(this);
		}
	}

}
