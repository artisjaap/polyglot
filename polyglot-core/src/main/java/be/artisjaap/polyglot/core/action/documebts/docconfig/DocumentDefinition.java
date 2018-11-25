package be.artisjaap.polyglot.core.action.documebts.docconfig;

import be.artisjaap.document.action.to.BriefConfigTO;
import be.artisjaap.document.api.DatasetProvider;

public interface DocumentDefinition {
    DocumentCode documentCode();

    BriefConfigTO.Builder buildConfig();

    DatasetProvider documentConfigForPreview();

    BriefConfigTO documentConfigForPreview(String language);

}
