package be.artisjaap.mail.model.mongo;

import be.artisjaap.mail.model.Mailing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public class MailingRepositoryImpl implements MailingRepositoryCustom {

	@Autowired
    protected MongoTemplate mongoTemplate;
	
	@Override
	public Page<Mailing> findGebruikersFor(String to, String subject, Integer page) {
		Query q = new Query();
		if(to != null){
			q.addCriteria(Criteria.where("to").regex(to, "i"));
		}
		if(subject != null){
			q.addCriteria(Criteria.where("subject").regex(subject, "i"));
		}

		Sort recenteEerst = new Sort(Sort.Direction.DESC, "timeStamp");
		Pageable pageable = new PageRequest(page, 100, recenteEerst );

		long count = mongoTemplate.count(q, Mailing.class);

		q.with(pageable);

		List<Mailing> list = mongoTemplate.find(q, Mailing.class);
		return new PageImpl<Mailing>(list, pageable, count);
	}

}
