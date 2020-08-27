package be.artisjaap.polyglot.core.model;

public enum  ProgressStatus {
    KNOWN("NEW"),  IN_PROGRESS("KNOWN"), NEW("IN_PROGRESS"),ON_HOLD("IN_PROGRESS");

    private final String logicalNext;

    ProgressStatus(String next){
        this.logicalNext = next;
    }

    public ProgressStatus logicalNextStatus() {
        return ProgressStatus.valueOf(logicalNext);
    }
}
