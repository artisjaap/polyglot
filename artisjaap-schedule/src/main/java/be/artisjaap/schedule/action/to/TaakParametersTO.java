package be.artisjaap.schedule.action.to;

import be.artisjaap.common.model.ReferenceableTO;

import java.util.ArrayList;
import java.util.List;

public class TaakParametersTO extends ReferenceableTO {
    private String code;
	private List<TaakPropertyEntryTO> properties;
	
	public List<TaakPropertyEntryTO> getProperties() {
		return properties;
	}
	
	public String getCode() {
		return code;
	}
	
	private TaakParametersTO(Builder builder) {
		buildCommon(builder);
		properties = builder.properties;
		code = builder.code;
	}

	public static Builder newBuilder() {
		return new Builder();
	}


	public static final class Builder extends AbstractBuilder<Builder>{
		private String code;
		private List<TaakPropertyEntryTO> properties = new ArrayList<>();

		
		private Builder() {
		}

		public Builder properties(List<TaakPropertyEntryTO> val) {
			properties = val;
			return this;
		}
		
		public Builder addProperty(TaakPropertyEntryTO propertyEntryTO){
			properties.add(propertyEntryTO);
			return this;
		}
		
		public Builder code(String var){
			code = var;
			return this;
		}

		public TaakParametersTO build() {
			return new TaakParametersTO(this);
		}
	}

}


