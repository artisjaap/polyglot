package be.artisjaap.polyglot.core.model.verbs;

public enum VerbFinite {
    FIRST_SINGLE, SECOND_SINGLE, THIRD_SINGLE_MALE, THIRD_SINGLE_FEMALE,
    FIRST_PLURAL, SECOND_PLURAL, THIRD_PLURAL_MALE, THIRD_PLURAL_FEMALE,
    ;

    public static VerbFinite getForIndex(int i) {
        switch (i) {
            case 1:
                return FIRST_SINGLE;
            case 2:
                return SECOND_SINGLE;
            case 3:
                return THIRD_SINGLE_MALE;
            case 4:
                return THIRD_SINGLE_FEMALE;
            case 5:
                return FIRST_PLURAL;
            case 6:
                return SECOND_PLURAL;
            case 7:
                return THIRD_PLURAL_MALE;
            case 8:
                return THIRD_PLURAL_FEMALE;
        }
        throw new UnsupportedOperationException("dont know index");
    }
}
