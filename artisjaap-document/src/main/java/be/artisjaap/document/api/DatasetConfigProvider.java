package be.artisjaap.document.api;

import java.util.List;

public interface DatasetConfigProvider {
	DatasetConfig forDataset(String dataset);

	List<DocumentImage> imageFields();
}
