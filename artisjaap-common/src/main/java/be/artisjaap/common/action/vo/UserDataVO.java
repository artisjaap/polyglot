package be.artisjaap.common.action.vo;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class UserDataVO {
    private String userId;
    private LocalDateTime activeSince;

    public static UserDataVO anonymous() {
        return UserDataVO.builder().userId("ANONYMOUS").build();
    }


}
