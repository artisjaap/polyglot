package be.artisjaap.polyglot.core.action.to;


import be.artisjaap.polyglot.core.model.AbstractDocument;

import java.time.LocalDateTime;

public class ReferenceableTO {

    private String id;
    private LocalDateTime timeStamp;

    public String id() {
        return id;
    }

    public LocalDateTime timeStamp() {
        return timeStamp;
    }

    protected void buildCommon(AbstractBuilder<?> builder){
        id = builder.id;
        timeStamp = builder.timeStamp;
    }


    public static abstract class AbstractBuilder<T> {
        private String id;
        private LocalDateTime timeStamp;


        public T withId(String id) {
            this.id = id;
            return (T)this;
        }

        public T withTimeStamp(LocalDateTime timeStamp) {
            this.timeStamp = timeStamp;
            return (T)this;
        }

        public T forDocument(AbstractDocument document){
            withId(document.getId().toString());
            withTimeStamp(document.getTimestamp());
            return (T)this;
        }


    }
}
