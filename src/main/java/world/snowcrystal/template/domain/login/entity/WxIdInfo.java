package world.snowcrystal.template.domain.login.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import static lombok.AccessLevel.PRIVATE;

@Value
@Builder
@AllArgsConstructor(access = PRIVATE)
public class WxIdInfo {
    private String pcWxOpenId;
    private String mobileWxOpenId;
    private String wxUnionId;
}
