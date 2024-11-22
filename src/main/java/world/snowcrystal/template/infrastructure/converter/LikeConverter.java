package world.snowcrystal.template.infrastructure.converter;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import world.snowcrystal.template.domain.like.entity.Like;
import world.snowcrystal.template.infrastructure.repository.po.PostLikePO;

@Mapper(componentModel = "spring")
public interface LikeConverter {

    @Mapping(source = "id.value", target = "id")
    @Mapping(source = "postId.value", target = "postId")
    @Mapping(source = "userId.value", target = "userId")
    PostLikePO persistence(Like entity);

    @Mapping(source = "id", target = "id.value")
    @Mapping(source = "postId", target = "postId.value")
    @Mapping(source = "userId", target = "userId.value")
    Like domain(PostLikePO po);


}
