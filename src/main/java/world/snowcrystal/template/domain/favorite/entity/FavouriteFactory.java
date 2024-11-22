package world.snowcrystal.template.domain.favorite.entity;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import world.snowcrystal.template.domain.common.enums.ApplicationResponseStatusCode;
import world.snowcrystal.template.domain.favorite.exception.AlreadyFavouriteException;
import world.snowcrystal.template.domain.favorite.repository.FavouriteRepository;
import world.snowcrystal.template.domain.identifier.component.SnowFlakeIdentifierGenerator;
import world.snowcrystal.template.domain.identifier.primitive.Id;

@Component
@RequiredArgsConstructor
public class FavouriteFactory {

    private final SnowFlakeIdentifierGenerator idGenerator;

    private final FavouriteRepository favouriteRepository;


    public Favourite createFavourite(Id postId, Id userId) {

        // 检查该用户是否已经收藏过该文章
        if(favouriteRepository.exists(postId, userId)){
            throw new AlreadyFavouriteException(ApplicationResponseStatusCode.NO_OPERATION,"你已经收藏过该文章了");
        }
        return Favourite.builder()
                .postId(postId)
                .id(idGenerator.generate())
                .userId(userId)
                .build();
    }


}
