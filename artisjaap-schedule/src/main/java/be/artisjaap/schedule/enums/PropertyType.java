package be.artisjaap.schedule.enums;

public enum PropertyType {
    DOUBLE, INTEGER, STRING, BOOLEAN;

	public Object convert(String value) {
		switch (this){
		case DOUBLE: return Double.valueOf(value);
		case INTEGER: return Integer.valueOf(value);
		case BOOLEAN: return Boolean.valueOf(value);
		default: return value;
		}
	}
	
	public static PropertyType typeOf(Object object){
		if(Double.class.isAssignableFrom(object.getClass())){
			return DOUBLE;
		}
		if(Integer.class.isAssignableFrom(object.getClass())){
			return INTEGER;
		}
		if(Boolean.class.isAssignableFrom(object.getClass())){
			return BOOLEAN;
		}
		return STRING;
	}
}
