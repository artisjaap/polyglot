package be.artisjaap.polyglot.core.model;

import be.artisjaap.polyglot.core.action.to.ReferenceableTO;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document
public class AbstractDocument {
    @Id
    private ObjectId id;

    private LocalDateTime timestamp = LocalDateTime.now();

    protected void buildCommon(AbstractBuilder<?> builder){
        id = builder.id;
        timestamp = builder.timestamp;
    }

    public ObjectId getId() {
        return id;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }


    public static abstract class AbstractBuilder<T> {
        private ObjectId id;
        private LocalDateTime timestamp;


        public T withId(ObjectId id) {
            this.id = id;
            return (T)this;
        }

        public T withTimeStamp(LocalDateTime timeStamp) {
            this.timestamp = timeStamp;
            return (T)this;
        }

        public T forDocument(AbstractDocument document){
            withId(document.getId());
            withTimeStamp(document.getTimestamp());
            return (T)this;
        }
    }
}
