package be.artisjaap.document.action.datasets;

public class SecretariaatDataset {
    private String omschrijving = "SECR";
    private String email = "Email";
    private String telefoonnummer = "0448768464";
    private String faxnummer = "44456876";
    private AdresDataset adres = new AdresDataset();

    public String getOmschrijving() {
        return omschrijving;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefoonnummer() {
        return telefoonnummer;
    }

    public String getFaxnummer() {
        return faxnummer;
    }

    public AdresDataset getAdres() {
        return adres;
    }
}
