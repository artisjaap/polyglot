package be.artisjaap.document.action.datasets;

import be.aclvb.framework.common.utility.LocalDateUtils;

import java.time.LocalDateTime;

public class BriefInfoDataset {

    private String briefCode = "CODE";

    public String getBriefCode() {
        return briefCode;
    }

    private LocalDateTime nu = LocalDateUtils.now();

    public String getAanmaakdatum() {
        return LocalDateUtils.format(nu);
    }
}
