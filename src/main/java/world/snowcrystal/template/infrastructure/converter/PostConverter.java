package world.snowcrystal.template.infrastructure.converter;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import world.snowcrystal.template.domain.post.entity.Post;
import world.snowcrystal.template.infrastructure.repository.po.PostPO;

@Mapper(componentModel = "spring")
public interface PostConverter {


    @Mapping(source = "po.id", target = "id.value")
    @Mapping(source = "po.userId", target = "userId.value")
    @Mapping(source = "po.title", target = "title.value")
    @Mapping(source = "po.content", target = "content.value")
    @Mapping(expression = "JSONUtil.toList(tags, Tag.class)", target = "tags")
    @Mapping(source = "po.createTime", target = "createTime")
    @Mapping(source = "po.thumbNum", target = "likes.value")
    @Mapping(source = "po.favourNum", target = "favourNum.value")
    Post toEntity(PostPO po);

    @Mapping(source = "entity.id.value", target = "id")
    @Mapping(source = "entity.userId.value", target = "userId")
    @Mapping(source = "entity.title.value", target = "title")
    @Mapping(source = "entity.content.value", target = "content")
    @Mapping(source = "entity.tags", target = "tags")
    @Mapping(source = "entity.createTime", target = "createTime")
    PostPO toPO(Post entity);

}
