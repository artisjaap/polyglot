package be.artisjaap.migrate.model;

public interface InitScript {
    String omschrijving();
	
	String getVersion();

	Integer cardinality();

	void execute();

	boolean altijdUitvoeren();
}
