package be.artisjaap.document.api;

import java.util.List;

public interface DatasetProvider extends MetaProvider {

    DatasetConfig get(String dataset) ;

    List<String> datasetNames();

}

