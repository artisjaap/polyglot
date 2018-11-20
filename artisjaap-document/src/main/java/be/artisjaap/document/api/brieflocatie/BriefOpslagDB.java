package be.artisjaap.document.api.brieflocatie;


public class BriefOpslagDB implements BriefOpslagLocatie {


    @Override
    public BriefLocatieType briefLocatieType() {
        return BriefLocatieType.IN_DB;
    }

    @Override
    public String path() {
        throw new IllegalStateException("Bij DB opslag mag geen path gevraagd worden");
    }
}
