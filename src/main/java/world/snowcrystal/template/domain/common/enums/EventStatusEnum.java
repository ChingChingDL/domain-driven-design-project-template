package world.snowcrystal.template.domain.common.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape= JsonFormat.Shape.NUMBER_INT)
public enum EventStatusEnum {
    //状态，CREATED(刚创建)，PUBLISH_SUCCEED(已发布成功)， PUBLISH_FAILED(发布失败)
    CREATED, PUBLISH_SUCCEED, PUBLISH_FAILED;
}
