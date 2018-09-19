package be.artisjaap.polyglot.core.model;

import org.bson.types.ObjectId;

import java.util.List;

public class Lesson extends AbstractDocument{

    private String name;
    private ObjectId languagePairId;
    private List<ObjectId> translations;

}
