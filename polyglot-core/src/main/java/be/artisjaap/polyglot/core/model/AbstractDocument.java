package be.artisjaap.polyglot.core.model;

import be.artisjaap.polyglot.core.action.to.ReferenceableTO;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document
public class AbstractDocument implements Referenceable{
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

        private T withId(ObjectId id) {
            this.id = id;
            return (T)this;
        }

        private T withTimeStamp(LocalDateTime timeStamp) {
            this.timestamp = timeStamp;
            return (T)this;
        }

        public T forDocument(ReferenceableTO document) {
            withId(new ObjectId(document.id()));
            withTimeStamp(document.timeStamp());
            return (T)this;
        }
    }
}
