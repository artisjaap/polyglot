package be.artisjaap.document.api;

public class DatasetConfigImpl implements DatasetConfig {
	private final Object data;
	private final String metaName;
	private final Class<?> clazz;
	private final boolean asList;
	
	public DatasetConfigImpl(Object data, String metaName, Class<?> clazz, boolean asList){
		this.data = data;
		this.metaName = metaName;
		this.clazz = clazz;
		this.asList = asList;
	}

	@Override
	public Object data() {
		return data;
	}

	@Override
	public String metaName() {
		return metaName;
	}

	@Override
	public Class<?> metaClass() {
		return clazz;
	}

	@Override
	public boolean isAsList() {
		return asList;
	}
}