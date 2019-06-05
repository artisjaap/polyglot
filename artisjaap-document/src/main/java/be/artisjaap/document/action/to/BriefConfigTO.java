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

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class BriefConfigTO implements DatasetConfigProvider {
    private String taal;
    private String code;
    private DatasetProvider datasetProvider;
    private FileName bestandsnaam;
    private BriefOpslagLocatie opslagSettingsTO;
    private BriefPathNameSettingsTO briefPathNameSettingsTO;
    private Set<String> datasetsBlacklist;
    private List<DocumentImage> documentImages;
    private boolean dryRun;


    public static Builder newBuilder(BriefConfigTO copy) {
        Builder builder = new Builder();
        builder.taal = copy.taal;
        builder.code = copy.code;
        builder.datasetProvider = copy.datasetProvider;
        builder.bestandsnaam = copy.bestandsnaam;
        builder.opslagSettingsTO = copy.opslagSettingsTO;
        builder.briefPathNameSettingsTO = copy.briefPathNameSettingsTO;
        builder.datasetsBlacklist = copy.datasetsBlacklist;
        builder.documentImages = copy.documentImages;
        return builder;
    }

    public BriefOpslagLocatie getOpslagSettingsTO() {
        return opslagSettingsTO;
    }

    public BriefPathNameSettingsTO getBriefPathNameSettingsTO() {
        return briefPathNameSettingsTO;
    }

    public String getCode() {
        return code;
    }

    public DatasetProvider getDatasetProvider() {
        return datasetProvider;
    }

    public List<String> datasets() {
        return datasetProvider.datasetsMeta().stream().map(DatasetMeta::metaName).collect(Collectors.toList());
    }

    public String getTaal() {
        return taal;
    }

    public FileName getBestandsnaam() {
        return bestandsnaam;
    }

    public Set<String> getDatasetsBlacklist() {
        return datasetsBlacklist;
    }

    public boolean dryRun() {
        return dryRun;
    }

    private BriefConfigTO(Builder builder) {
        taal = builder.taal;
        code = builder.code;
        datasetProvider = builder.datasetProvider;
        bestandsnaam = builder.bestandsnaam;
        opslagSettingsTO = builder.opslagSettingsTO;
        briefPathNameSettingsTO = builder.briefPathNameSettingsTO;
        datasetsBlacklist = builder.datasetsBlacklist;
        documentImages = builder.documentImages;
        dryRun = builder.dryRun;
    }

    public static Builder newBuilder() {
        return new Builder();
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

    public static final class Builder {
        private String code;
        private DatasetProvider datasetProvider = EmptyDatasetProvider.create();
        private BriefOpslagLocatie opslagSettingsTO = BriefLocatieFactory.voorDB();
        private FileName bestandsnaam = FileGeneratieFactory.simpleFilename();
        private BriefPathNameSettingsTO briefPathNameSettingsTO;
        private String taal;
        private Set<String> datasetsBlacklist = new HashSet<>();
        private List<DocumentImage> documentImages = new ArrayList<>();
        private boolean dryRun = false;

        private Builder() {
        }

        public Builder withCode(String code) {
            this.code = code;
            return this;
        }

        public Builder withDatasetProvider(DatasetProvider datasetProvider) {
            this.datasetProvider = datasetProvider;
            return this;
        }

        public Builder withOpslagLocatie(BriefOpslagLocatie opslagSettingsTO) {
            this.opslagSettingsTO = opslagSettingsTO;
            return this;
        }

        public Builder withDatasetsToBlacklist(Set<String> blacklist) {
            this.datasetsBlacklist = blacklist;
            return this;
        }

        public Builder withBriefPathNameSettingsTO(BriefPathNameSettingsTO briefPathNameSettingsTO) {
            this.briefPathNameSettingsTO = briefPathNameSettingsTO;
            return this;
        }

        public Builder withDatasetsBlacklist(Set<String> datasetsBlacklist) {
            this.datasetsBlacklist = datasetsBlacklist;
            return this;
        }

        public Builder withBestandsnaam(FileName bestandsnaam) {
            this.bestandsnaam = bestandsnaam;
            return this;
        }

        public Builder withOpslagSettingsTO(BriefOpslagLocatie opslagSettingsTO) {
            this.opslagSettingsTO = opslagSettingsTO;
            return this;
        }

        public Builder withDryRun(Boolean dryRun) {
            this.dryRun = dryRun;
            return this;
        }

        public Builder addDatasetToBlacklist(String datasetName) {
            this.datasetsBlacklist.add(datasetName);
            return this;
        }


        public Builder addImage(DocumentImage documentImage) {
            this.documentImages.add(documentImage);
            return this;
        }


        public Builder withQrData(String qrData) {
            addImage(DocumentImage.newBuilder().withName("qr").withImage(QrUtils.maakQrMetData(qrData)).build());
            return this;
        }

        public BriefConfigTO build() {


            return new BriefConfigTO(this);
        }

        public Builder withTaal(String taal) {
            this.taal = taal;
            return this;
        }
    }
}
