package be.artisjaap.document.api;

import java.util.List;

public interface DatasetConfigProvider {

	List<String> datasets();

	DatasetConfig forDataset(String dataset);

	List<DocumentImage> imageFields();
}
