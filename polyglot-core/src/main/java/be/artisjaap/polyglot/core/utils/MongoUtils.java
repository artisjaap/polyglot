package be.artisjaap.polyglot.core.utils;

import be.artisjaap.polyglot.core.action.to.SortTO;
import org.springframework.data.domain.Sort;

public class MongoUtils {

    public final static Sort.Direction of(SortTO.Direction direction){
        switch(direction){
            case ASCENDING:
                return Sort.Direction.ASC;
            case DESCENDING:
                return Sort.Direction.DESC;
            default:
                throw new UnsupportedOperationException("Sort direction unknown");
        }
    }
}
