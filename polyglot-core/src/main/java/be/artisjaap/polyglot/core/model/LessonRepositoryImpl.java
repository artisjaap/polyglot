package be.artisjaap.polyglot.core.model;

import be.artisjaap.polyglot.core.action.to.LessonFilterTO;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

import static org.springframework.util.StringUtils.hasText;

public class LessonRepositoryImpl implements LessonRepositoryCustom {
    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public Page<Lesson> findLessonsForFilter(LessonFilterTO filter) {
        Query q = new Query();
        Pageable pageable = PageRequest.of(filter.page(), filter.pageSize(), Sort.Direction.ASC, "_id");
        q.addCriteria(Criteria.where("languagePairId")
                .is(new ObjectId(filter.languagePairId())));

        if (hasText(filter.textFilter())) {
            q.addCriteria(Criteria.where("name").regex(filter.textFilter(), "i"));
        }

        long count = mongoTemplate.count(q, Lesson.class);
        q.with(pageable);
        List<Lesson> translations = mongoTemplate.find(q, Lesson.class);

        return new PageImpl<>(translations, pageable, count);
    }
}
