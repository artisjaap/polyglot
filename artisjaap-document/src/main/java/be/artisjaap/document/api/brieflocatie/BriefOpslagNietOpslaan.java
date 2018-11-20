package be.artisjaap.document.api.brieflocatie;

public class BriefOpslagNietOpslaan implements BriefOpslagLocatie {
    @Override
    public BriefLocatieType briefLocatieType() {
        return BriefLocatieType.NIET_OPSLAAN;
    }

    @Override
    public String path() {
        throw new UnsupportedOperationException();
    }
}
