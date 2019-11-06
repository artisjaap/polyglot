package be.artisjaap.polyglot.core.model;

import be.artisjaap.polyglot.core.action.to.SortTO;
import be.artisjaap.polyglot.core.action.to.TranslationFilterTO;
import be.artisjaap.polyglot.core.utils.MongoUtils;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.util.StringUtils.hasText;

public class TranslationRepositoryImpl implements TranslationRepositoryCustom {
    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public Page<Translation> findTranslationsForFilter(TranslationFilterTO filter) {
        Query q = new Query();

        Sort sort;
        if(filter.isOrderDefined()){
            List<Sort.Order> order = filter.getOrderByFields().stream().map(sortTO ->
                    (sortTO.getDirection() == SortTO.Direction.ASCENDING) ?
                            Sort.Order.asc(sortTO.getFieldName()) :
                            Sort.Order.desc(sortTO.getFieldName()))
                    .collect(Collectors.toList());
            sort = Sort.by(order);
        }else{
            sort = Sort.by(Sort.Direction.ASC, "_id");
        }
        Pageable pageable = PageRequest.of(filter.page(), filter.pageSize(), sort);

        q.addCriteria(Criteria.where("languagePairId")
                .is(new ObjectId(filter.languagePairId())));

        if (hasText(filter.textFilter())) {
            q.addCriteria(new Criteria().orOperator(
                    Criteria.where("languageA").regex(filter.textFilter(), "i"),
                    Criteria.where("languageB").regex(filter.textFilter(), "i")));
        }

        long count = mongoTemplate.count(q, Translation.class);

        q.with(pageable);
        List<Translation> translations = mongoTemplate.find(q, Translation.class);

        return new PageImpl<>(translations, pageable, count);
    }
}

