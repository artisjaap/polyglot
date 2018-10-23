package be.artisjaap.polyglot.web.endpoints.response;

import be.artisjaap.core.model.ReferenceableTO;

public abstract class AbstractReferenceableResponse implements ReferenceableResponse {

    private String id;

    @Override
    public String getId() {
        return id;
    }

    protected void buildCommon(AbstractBuilder<?> builder) {
        id = builder.id;
    }


    public static abstract class AbstractBuilder<T> {
        private String id;

        private T withId(String id) {
            this.id = id;
            return (T) this;
        }

        public T forDocument(ReferenceableTO document) {
            withId(document.id());
            return (T) this;
        }
    }
}
