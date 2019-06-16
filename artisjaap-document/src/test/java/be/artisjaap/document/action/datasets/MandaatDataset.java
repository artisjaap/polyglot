package be.artisjaap.document.action.datasets;

public class MandaatDataset {
    private String naam = "naam";
    private String voornaam = "voornaam";
    private AdresDataset adres = new AdresDataset();

    public String getNaam() {
        return naam;
    }

    public String getVoornaam() {
        return voornaam;
    }

    public AdresDataset getAdres() {
        return adres;
    }
}
