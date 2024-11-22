package world.snowcrystal.template.domain.like.entity;

import lombok.Builder;
import lombok.Getter;
import world.snowcrystal.template.domain.identifier.primitive.Id;

import java.util.Date;

@Builder
@Getter
public class Like {
    private Id id;
    private Id postId;
    private Id userId;
    private Date createTime;
    private Date updateTime;
}
