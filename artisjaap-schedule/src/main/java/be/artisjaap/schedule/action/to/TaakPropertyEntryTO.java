package be.artisjaap.schedule.action.to;

import be.artisjaap.schedule.enums.PropertyType;

public class TaakPropertyEntryTO {
    private final String key;
	private final String value;
	private final PropertyType type;
	
	
	public String getKey() {
		return key;
	}

	public String getValue() {
		return value;
	}
	
	public PropertyType getType(){
		return type;
	}

	private TaakPropertyEntryTO(Builder builder) {
		key = builder.key;
		value = builder.value;
		type = builder.type;
	}

	public static Builder newBuilder() {
		return new Builder();
	}

	public static Builder newBuilder(TaakPropertyEntryTO copy) {
		Builder builder = new Builder();
		builder.key = copy.key;
		builder.value = copy.value;
		builder.type = copy.type;
		return builder;
	}

	public static final class Builder {
		private String key;
		private String value;
		private PropertyType type;
		private Builder(){
			
		}
		
		public Builder key(String val) {
			key = val;
			return this;
		}

		public Builder value(String val) {
			value = val;
			return this;
		}
		
		public Builder propertyType(PropertyType val) {
			type = val;
			return this;
		}

		public TaakPropertyEntryTO build() {
			return new TaakPropertyEntryTO(this);
		}
	}

}
