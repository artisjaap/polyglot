package be.artisjaap.polyglot.core.action.to;

public class SortTO {

    public  enum Direction {ASCENDING,DESCENDING}

    private Direction direction;
    private String fieldName;

    public Direction getDirection() {
        return direction;
    }

    public String getFieldName() {
        return fieldName;
    }

    private SortTO(Builder builder) {
        direction = builder.direction;
        fieldName = builder.fieldName;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static final class Builder {
        private String fieldName;
        private Direction direction = Direction.ASCENDING;


        private Builder() {
        }

        public Builder withDirection(Direction direction) {
            this.direction = direction;
            return this;
        }

        public Builder withFieldName(String fieldName) {
            this.fieldName = fieldName;
            return this;
        }


        public SortTO build() {
            return new SortTO(this);
        }
    }
}
