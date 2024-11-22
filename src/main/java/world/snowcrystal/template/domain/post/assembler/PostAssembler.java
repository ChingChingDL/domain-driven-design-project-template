package world.snowcrystal.template.domain.post.assembler;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import world.snowcrystal.template.domain.post.dto.command.PostEditResponse;
import world.snowcrystal.template.domain.post.dto.query.PostQueryResponse;
import world.snowcrystal.template.domain.post.entity.Post;
import world.snowcrystal.template.domain.post.primitive.Tag;
import world.snowcrystal.template.infrastructure.repository.po.PostPO;

@Mapper(componentModel = "spring",imports = {Tag.class})
public interface PostAssembler {

    @Mapping(source = "po.id", target = "id.value")
    @Mapping(source = "po.userId", target = "userId.value")
    @Mapping(source = "po.title", target = "title.value")
    @Mapping(source = "po.content", target = "content.value")
    @Mapping(expression = "java(cn.hutool.json.JSONUtil.toList(po.getTags(), Tag.class))", target = "tags")
    @Mapping(source = "po.createTime", target = "createTime")
    @Mapping(source = "po.thumbNum", target = "likes.value")
    @Mapping(source = "po.favourNum", target = "favours.value")
    Post domain(PostPO po);


    @Mapping(source = "entity.title.value", target = "title")
    @Mapping(source = "entity.content.value", target = "content")
    @Mapping(expression = "java(entity.getTags().stream().map(Tag::getValue).toList())", target = "tags")
    PostEditResponse toEditResponse(Post entity);

    @Mapping(source = "entity.id.value", target = "id")
    @Mapping(source = "entity.userId.value", target = "userId")
    @Mapping(source = "entity.title.value", target = "title")
    @Mapping(source = "entity.content.value", target = "content")
    @Mapping(expression = "java(entity.getTags().stream().map(Tag::getValue).toList())", target = "tags")
    @Mapping(source = "entity.createTime", target = "createTime")
    @Mapping(source = "entity.likes.value", target = "likes")
    @Mapping(source = "entity.favours.value", target = "favours")
    PostQueryResponse toQueryResponse(Post entity);
}

