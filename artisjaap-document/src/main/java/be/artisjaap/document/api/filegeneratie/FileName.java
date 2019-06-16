package be.artisjaap.document.api.filegeneratie;

import be.artisjaap.document.api.DatasetProvider;

public interface FileName {
    String filename(DatasetProvider datasetProvider);
}
