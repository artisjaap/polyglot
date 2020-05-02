package be.artisjaap.polyglot.core.action.translationloader;

public interface TranslationTag<T> {

    TagName tagName();

    T parsedTag();
}
