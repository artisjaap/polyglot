package be.artisjaap.document.action.datasets;

public class LidDataset{
    private String voornaam = "Voornaam";
    private String naam = "Naam";
    private String antheaNr = "88888888";
    private String synonNr = "11/111111";

    private AdresDataset adres = new AdresDataset();

    public String getVoornaam() {
        return voornaam;
    }

    public String getNaam() {
        return naam;
    }

    public String getAntheaNr() {
        return antheaNr;
    }

    public String getSynonNr() {
        return synonNr;
    }

    public AdresDataset getAdres() {
        return adres;
    }
}