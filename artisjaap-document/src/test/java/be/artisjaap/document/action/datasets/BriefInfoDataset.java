package be.artisjaap.document.action.datasets;

import java.time.LocalDateTime;

public class BriefInfoDataset {

    private String briefCode = "CODE";

    public String getBriefCode() {
        return briefCode;
    }

    private LocalDateTime nu = LocalDateTime.now(); //FIXME Use LocalDateUtils

    public String getAanmaakdatum() {
        return "";
    }
}
