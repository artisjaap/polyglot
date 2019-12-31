package be.artisjaap.common.utils;

import org.bson.types.ObjectId;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class MongoUtils {

    public static ObjectId toObjectId(String id){
        return new ObjectId(id);
    }

    private MongoUtils(){}

    public static List<ObjectId> toObjectIdList(Collection<String> idsAsString){
        return idsAsString.stream().map(MongoUtils::toObjectId).collect(Collectors.toList());
    }

    public static Set<ObjectId> toObjectIdSet(Collection<String> idsAsString){
        return idsAsString.stream().map(MongoUtils::toObjectId).collect(Collectors.toSet());
    }
}
