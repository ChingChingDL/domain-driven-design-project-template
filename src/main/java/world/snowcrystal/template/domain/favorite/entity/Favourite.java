package world.snowcrystal.template.domain.favorite.entity;

import lombok.Builder;
import lombok.Getter;
import world.snowcrystal.template.domain.identifier.primitive.Id;

import java.util.Date;

@Builder
@Getter
public class Favourite {
    private Id id;
    private Id postId;
    private Id userId;
    private Date updateTime;
    private Date createTime;
}
