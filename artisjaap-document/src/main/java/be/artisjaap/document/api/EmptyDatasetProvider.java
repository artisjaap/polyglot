package be.artisjaap.document.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class EmptyDatasetProvider implements DatasetProvider {
    private static final Logger logger = LoggerFactory.getLogger(EmptyDatasetProvider.class);


    public static DatasetProvider create(){
        return new EmptyDatasetProvider();
    }

    @Override
    public DatasetConfig get(String dataset) {
        logger.error("Geen dataset forTemplateId " + dataset);
        return new DatasetConfigImpl(new HashMap<>(), dataset, Object.class, false);
    }

    @Override
    public List<String> datasetNames() {
        return new ArrayList<String>();
    }

    @Override
    public List<DatasetMeta> datasetsMeta() {
        return new ArrayList<>();
    }
}
