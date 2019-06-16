package be.artisjaap.migrate.model.scripts;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class UpdateVersionScript extends AbstractInitScript {
    private final static Logger logger = LogManager.getLogger();

	@Override
	public String getVersion() {
		return "0.2";
	}
	
	@Override
	public String omschrijving() {
		return "Verhoog versienummer";
	}

	@Override
	public Integer cardinality() {
		return Integer.MAX_VALUE;
	}

	@Override
	public void execute() {
		logger.info("Upgrade version to " + getVersion());

	}
}
