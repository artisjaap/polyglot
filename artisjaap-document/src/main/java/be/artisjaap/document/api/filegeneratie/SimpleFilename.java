package be.artisjaap.document.api.filegeneratie;

import be.artisjaap.document.api.DatasetProvider;

public class SimpleFilename implements FileName {



    @Override
    public String filename(DatasetProvider datasetProvider) {
        GenericFileName genericFileName = GenericFileName.newBuilder()
                .withFilenameParts("BriefMeta.briefCode")
                .withFilenameParts("BriefMeta.isoCode")
                .withFilenameParts("BriefMeta.aanmaakTimestamp")
                .build();
        return genericFileName.filename(datasetProvider);
        
    }
}
