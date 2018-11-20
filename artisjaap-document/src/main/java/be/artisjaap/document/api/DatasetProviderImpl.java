package be.artisjaap.document.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DatasetProviderImpl implements DatasetProvider {

    private Map<String, DatasetConfig> datasets = new HashMap();

    public static DatasetProviderImpl create(){
        return new DatasetProviderImpl();
    }

    public DatasetProviderImpl add(String datasetName, Object dataset) {
        DatasetConfigImpl config = new DatasetConfigImpl(dataset, datasetName, dataset.getClass(), false);
        datasets.put(datasetName, config);
        return this;
    }

    public DatasetProviderImpl add(String datasetName, Object dataset, boolean isList) {
        DatasetConfigImpl config = new DatasetConfigImpl(dataset, datasetName, dataset.getClass(), isList);
        datasets.put(datasetName, config);
        return this;
    }

    public DatasetProviderImpl add(String datasetName, Object dataset, Class<?> collectionClass) {
        DatasetConfigImpl config = new DatasetConfigImpl(dataset, datasetName, collectionClass, true);
        datasets.put(datasetName, config);
        return this;
    }

    @Override
    public DatasetConfig get(String dataset) {
        return datasets.get(dataset);
    }

    @Override
    public List<DatasetMeta> datasetsMeta() {
        return new ArrayList(datasets.values());
    }

    @Override
    public List<String> datasetNames() {
        return new ArrayList(datasets.keySet());
    }

}
