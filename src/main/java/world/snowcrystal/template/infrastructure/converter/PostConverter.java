package world.snowcrystal.template.infrastructure.converter;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import world.snowcrystal.template.domain.post.entity.Post;
import world.snowcrystal.template.infrastructure.repository.po.PostPO;


@Mapper(componentModel = "spring", imports = {world.snowcrystal.template.domain.post.primitive.Tag.class, cn.hutool.json.JSONUtil.class})
public interface PostConverter {


    @Mapping(source = "po.id", target = "id.value")
    @Mapping(source = "po.userId", target = "userId.value")
    @Mapping(source = "po.title", target = "title.value")
    @Mapping(source = "po.content", target = "content.value")
    @Mapping(expression = "java(JSONUtil.toList(po.getTags(),Tag.class))", target = "tags")
    @Mapping(source = "po.createTime", target = "createTime")
    @Mapping(source = "po.thumbNum", target = "likes.value")
    @Mapping(source = "po.favourNum", target = "favours.value")
    Post domain(PostPO po);

    @Mapping(target = "thumbNum", source = "likes.value")
    @Mapping(target = "favourNum", source = "favours.value")
    @Mapping(source = "entity.id.value", target = "id")
    @Mapping(source = "entity.userId.value", target = "userId")
    @Mapping(source = "entity.title.value", target = "title")
    @Mapping(source = "entity.content.value", target = "content")
    @Mapping(expression = "java(JSONUtil.toJsonStr(entity.getTags().stream().map(Tag::getValue).toList()))", target = "tags")
    @Mapping(source = "entity.createTime", target = "createTime")
    PostPO persistence(Post entity);


}
