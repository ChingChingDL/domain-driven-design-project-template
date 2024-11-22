package world.snowcrystal.template.infrastructure.converter;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import world.snowcrystal.template.domain.favorite.entity.Favourite;
import world.snowcrystal.template.infrastructure.repository.po.PostFavourPO;

@Mapper(componentModel = "spring")
public interface FavourConverter {

    @Mapping(source = "postId.value", target = "postId")
    @Mapping(source = "userId.value", target = "userId")
    @Mapping(source = "id.value", target = "id")
    PostFavourPO persistence(Favourite entity);

    @Mapping(source = "postId", target = "postId.value")
    @Mapping(source = "userId", target = "userId.value")
    @Mapping(source = "id", target = "id.value")
    Favourite domain(PostFavourPO po);




}
