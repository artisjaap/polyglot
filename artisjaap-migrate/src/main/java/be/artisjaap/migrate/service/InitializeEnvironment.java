package be.artisjaap.migrate.service;

import be.artisjaap.migrate.model.InitScript;
import be.artisjaap.migrate.model.Version;
import be.artisjaap.migrate.model.action.to.InitScriptStatusTO;
import be.artisjaap.migrate.model.action.to.ServerRestartTO;
import be.artisjaap.migrate.model.mongo.VersionRepository;
import com.mongodb.DB;
import com.mongodb.Mongo;
import com.mongodb.client.MongoDatabase;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Component
public class InitializeEnvironment {
    private final static Logger logger = LogManager.getLogger();

	@Autowired
	private VersionRepository versionRepository;

	@Autowired
	private List<InitScript> scriptsUitTeVoeren;

	@Autowired
	private MongoTemplate mongoTemplate;

//	@Autowired
//	private CommunicatieService communicatieService;
	
	@PostConstruct
	public void init() {
		MongoDatabase db = mongoTemplate.getDb();


		ServerRestartTO.Builder srBuilder = ServerRestartTO.newBuilder()
				.datumRestart(LocalDateTime.now())
				.mongodbName(db.getName());
		
		boolean debug = db.getName().contains("test");

//		logger.info("Scripts are being executed on " + db.getName() + "[" + mongo.getAddress().getHost() + ":"
//				+ mongo.getAddress().getPort() + "]");
		if (debug) {
			logger.warn("Connected against debug db, all scripts are reexecuted!! ");

		}
		logger.info("# scripts found: " + scriptsUitTeVoeren.size());

		Collections.sort(scriptsUitTeVoeren, (a, b) -> a.cardinality().compareTo(b.cardinality()));
		scriptsUitTeVoeren.stream().forEach(s -> verwerk(s, debug, srBuilder));
		
		srBuilder.huidigeVersie(versionRepository.findCurrentVersion());
		//communicatieService.verstuurRestartServerInfo(srBuilder.build());

	}
	
	private void verwerk(InitScript script, Boolean debug, ServerRestartTO.Builder srBuilder){
		boolean uitgevoerd = false;	
		if(debug || moetNogVerwerktWorden(script)){
			voerScriptUit(script);
			uitgevoerd = true;
		}
		srBuilder.addInitScript(InitScriptStatusTO.newBuilder()
				.scriptVersie(script.getVersion())
				.scriptOmschrijving(script.omschrijving())
				.scriptstatus(uitgevoerd?"is uitgevoerd":"was al uitgevoerd")
				.build());
		
		logger.info(" -> script: " + script.getVersion() + "....." + (uitgevoerd?"is uitgevoerd":"was al uitgevoerd"));
		
	}

	private boolean moetNogVerwerktWorden(InitScript script) {
		if (script.altijdUitvoeren()) {
			return true;
		}
		Version version = versionRepository.findByVersion(script.getVersion());
		if (version == null) {
			version = maakVersieAan(script);
			return true;
		}
		return !version.getVerwerkt();

	}

	private Version maakVersieAan(InitScript script) {
		Version version;
		version = new Version();
		version.setVersion(script.getVersion());
		versionRepository.save(version);
		return version;
	}

	private void voerScriptUit(InitScript script) {
		logger.info("Script in uitvoering voor versie " + script.getVersion() + " : " + script.omschrijving());
		script.execute();
		versionRepository.zetVerwerkt(script);
	}

}
