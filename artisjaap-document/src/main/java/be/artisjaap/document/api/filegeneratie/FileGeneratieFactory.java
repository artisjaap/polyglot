package be.artisjaap.document.api.filegeneratie;

public class FileGeneratieFactory {
    public static FileName simpleFilename() {
        return new SimpleFilename();
    }

    public static GenericFileName.Builder fromDatasets() {
        return GenericFileName.newBuilder();
    }
}
