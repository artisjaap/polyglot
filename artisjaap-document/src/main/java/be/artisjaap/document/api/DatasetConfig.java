package be.artisjaap.document.api;

public interface DatasetConfig extends DatasetMeta {
    Object data();

    boolean isAsList();
}