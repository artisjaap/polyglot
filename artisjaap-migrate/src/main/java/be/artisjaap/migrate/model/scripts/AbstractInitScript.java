package be.artisjaap.migrate.model.scripts;

import be.artisjaap.migrate.model.InitScript;
import com.mongodb.DB;
import com.mongodb.client.MongoDatabase;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.CollectionOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.regex.Matcher;

@Component
public abstract class AbstractInitScript implements InitScript {
    private final static Logger logger = LogManager.getLogger();

	public final CollectionOptions DEFAULT_CAPPED_50MB = new CollectionOptions(50L * 1024 * 1024, 10000L, true);

	@Autowired
	protected MongoTemplate mongoTemplate;

	public void createCollection(Class<?> entityClass) {
		dropCollectionIfExists(entityClass);
		mongoTemplate.createCollection(entityClass);
	}

	private void dropCollectionIfExists(Class<?> entityClass) {
		if (mongoTemplate.collectionExists(entityClass)) {
			mongoTemplate.dropCollection(entityClass);
			logger.info("Collection: " + entityClass.getName() + " was dropped.");
		}
	}

	protected void createCollection(Class<?> entityClass, CollectionOptions options) {
		dropCollectionIfExists(entityClass);
		mongoTemplate.createCollection(entityClass, options);
		logger.info("Collection: " + entityClass.getName() + " was created.");

	}

	protected void createDefaultCappedCollection(Class<?> entityClass) {
		createCollection(entityClass, DEFAULT_CAPPED_50MB);

	}

	protected void executeExternalJson(String jsonResource) {
		BufferedReader read = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream(jsonResource)));
		StringBufferWrapper sb = new StringBufferWrapper();

		read.lines().forEach(s -> {
			sb.append(s);
			Boolean isEndingLine = ScriptReader.isEndingLine(s);

			if (isEndingLine) {
				Matcher matcher = ScriptReader.matchSaveCommand(sb.toString());
				if (matcher.find()) {

					String collection = matcher.group(ScriptReader.COLLECTION);
					String insertScript = matcher.group(ScriptReader.INSERT_SCRIPT);
					logger.info(insertScript + " ---> " + collection);
					try {
						mongoTemplate.save(insertScript, collection);

					} catch (Exception e) {
						logger.info(" --> Something went wrong... " + e.getMessage());
					}

				}
				sb.clear();
			}
		});
	}

	@Override
	public boolean altijdUitvoeren() {
		return false;
	}

	public boolean connectedToTestDB() {
		MongoDatabase db = mongoTemplate.getDb();
		boolean debug = db.getName().contains("test");
		return debug;
	}

}

class StringBufferWrapper {

	StringBuffer sb;

	StringBufferWrapper() {
		this.sb = new StringBuffer();
	}

	public void clear() {
		sb = new StringBuffer();
	}

	public void append(String string) {
		sb.append(string);
	}

	@Override
	public String toString() {
		return sb.toString();
	}
}