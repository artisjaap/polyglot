package be.artisjaap.document.action.to;

import be.artisjaap.document.api.DatasetConfig;
import be.artisjaap.document.api.DatasetConfigProvider;
import be.artisjaap.document.api.DatasetMeta;
import be.artisjaap.document.api.DatasetProvider;
import be.artisjaap.document.api.DocumentImage;
import be.artisjaap.document.api.EmptyDatasetProvider;
import be.artisjaap.document.api.brieflocatie.BriefLocatieFactory;
import be.artisjaap.document.api.brieflocatie.BriefOpslagLocatie;
import be.artisjaap.document.api.filegeneratie.FileGeneratieFactory;
import be.artisjaap.document.api.filegeneratie.FileName;
import be.artisjaap.document.utils.QrUtils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Singular;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Builder(toBuilder = true)
@Data
@AllArgsConstructor
public class BriefConfigTO implements DatasetConfigProvider {
    private String taal;
    private String code;
    private DatasetProvider datasetProvider;
    private FileName bestandsnaam;
    private BriefOpslagLocatie opslagSettingsTO;
    private BriefPathNameSettingsTO briefPathNameSettingsTO;
    @Builder.Default
    private Set<String> datasetsBlacklist = new HashSet<>();

    @Builder.Default
    private List<DocumentImage> documentImages = new ArrayList<>();
    private boolean dryRun;

    @Override
    public List<String> datasets() {
        return datasetProvider.datasetsMeta().stream().map(DatasetMeta::metaName).collect(Collectors.toList());
    }

    @Override
    public DatasetConfig forDataset(String dataset) {
        return datasetProvider.get(dataset);
    }

    @Override
    public List<DocumentImage> imageFields() {
        return documentImages;
    }

    public boolean isBlacklisted(String datasetNaam) {
        return datasetsBlacklist.contains(datasetNaam);
    }
}
