package be.artisjaap.document.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DatasetInfo {
	private Map<String, List<String>> datasets = new HashMap<>();
	
	public Map<String, List<String>> getDatasets() {
		return datasets;
	}
	
	
	public void addMergefield(String dataset, String mergefield){
		datasets.putIfAbsent(dataset, new ArrayList<>());
		datasets.get(dataset).add(mergefield);
	}
	

}
