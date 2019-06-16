package be.artisjaap.schedule.model;

public class TaakParameterEntry {

	private String key;
    private Object value;
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public Object getValue() {
		return value;
	}
	public void setValue(Object value) {
		this.value = value;
	}
	
	public static TaakParameterEntry from(String key, Object value){
		TaakParameterEntry entry = new TaakParameterEntry();
		entry.setKey(key);
		entry.setValue(value);
		return entry;
	}
}
