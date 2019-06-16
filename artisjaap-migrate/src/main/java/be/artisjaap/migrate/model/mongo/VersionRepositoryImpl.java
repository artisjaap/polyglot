package be.artisjaap.migrate.model.mongo;

import be.artisjaap.migrate.model.InitScript;
import be.artisjaap.migrate.model.Version;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.List;

public class VersionRepositoryImpl implements VersionRepositoryCustom {
    @Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public void zetVerwerkt(InitScript script) {
		Query query = new Query(Criteria.where("version").is(script.getVersion()));
		Update update = new Update();
		update.set("verwerkt", true);
		mongoTemplate.updateFirst(query, update, Version.class);
	}

	@Override
	public String findCurrentVersion() {
		Query query = new Query();
		query.with(new Sort(Direction.DESC, "timeStamp"));
		query.limit(1);

		List<Version> find = mongoTemplate.find(query, Version.class);
		if (find.size() == 1) {
			return find.iterator().next().getVersion();
		}
		return "-";
	}

}
