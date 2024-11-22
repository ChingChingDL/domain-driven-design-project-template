package world.snowcrystal.template.domain.post.assembler;

import org.mapstruct.Mapper;
import world.snowcrystal.template.domain.post.dto.command.PostEditResponse;
import world.snowcrystal.template.domain.post.dto.query.PostQueryResponse;
import world.snowcrystal.template.domain.post.entity.Post;

@Mapper(componentModel = "spring")
public interface PostAssembler {


    Post toEntity();
    PostEditResponse toEditResponse(Post post);
    PostQueryResponse toQueryResponse(Post post);
}

