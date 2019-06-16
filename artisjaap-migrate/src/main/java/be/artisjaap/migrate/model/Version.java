package be.artisjaap.migrate.model;

import be.artisjaap.common.model.AbstractDocument;

public class Version extends AbstractDocument {
    private static final long serialVersionUID = 1L;
	private String version;
	Boolean verwerkt = false;
	
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public Boolean getVerwerkt() {
		return verwerkt;
	}
	public void setVerwerkt(Boolean verwerkt) {
		this.verwerkt = verwerkt;
	}
	
	
	
}