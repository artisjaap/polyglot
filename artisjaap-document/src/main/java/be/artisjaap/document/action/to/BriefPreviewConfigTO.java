package be.artisjaap.document.action.to;

import be.artisjaap.document.api.DatasetConfig;
import be.artisjaap.document.api.DatasetConfigImpl;
import be.artisjaap.document.api.DatasetConfigProvider;
import be.artisjaap.document.api.DatasetMeta;
import be.artisjaap.document.api.DatasetProvider;
import be.artisjaap.document.api.DocumentImage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BriefPreviewConfigTO implements DatasetConfigProvider, DatasetProvider {
    @Override
    public List<String> datasets() {
        return new ArrayList<>();
    }

    @Override
    public DatasetConfig forDataset(String dataset) {
        return new DatasetConfigImpl(new IdentityHashMap<>(), dataset, IdentityHashMap.class, false);
    }

    @Override
    public List<DocumentImage> imageFields() {
        return new ArrayList<>();
    }

    @Override
    public DatasetConfig get(String dataset) {
        return new DatasetConfigImpl(new IdentityHashMap<>(), dataset, IdentityHashMap.class, false);
    }


    @Override
    public List<String> datasetNames() {
        return new ArrayList<>();
    }

    @Override
    public List<DatasetMeta> datasetsMeta() {
        return new ArrayList<>();
    }


    class IdentityHashMap<K, V> extends HashMap<K, V> {

        @Override
        public boolean containsKey(Object o) {
            return true;
        }

        @Override
        public V get(Object o) {
            IdentityHashMap<Object, Object> objectObjectIdentityHashMap = new IdentityHashMap<>();
            objectObjectIdentityHashMap.put("", o);
            return (V) objectObjectIdentityHashMap;
        }
    }
}
