package world.snowcrystal.template.domain.favorite.dto.command;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import world.snowcrystal.template.domain.favorite.entity.Favourite;
import world.snowcrystal.template.domain.favorite.entity.FavouriteFactory;
import world.snowcrystal.template.domain.favorite.repository.FavouriteRepository;
import world.snowcrystal.template.domain.identifier.primitive.Id;
import world.snowcrystal.template.domain.login.command.LoginCommandService;

@Service
@RequiredArgsConstructor
public class FavouriteCommandService {

    private final FavouriteRepository favouriteRepository;
    private final FavouriteFactory favouriteFactory;
    private final LoginCommandService loginCommandService;

    /**
     * 收藏文章
     *
     * @return 操作后该文章收藏数量
     */
    @Transactional
    public Long doPostFavour(PostFavourAddCommand command, HttpServletRequest request) {
        Id postId = Id.of(command.getPostId());
        Id userId = loginCommandService.getLoginUserEntity(request).getId();
        Favourite favourite = favouriteFactory.createFavourite(postId, userId);
        favouriteRepository.save(favourite);

        return favouriteRepository.count(postId);
    }

    /**
     * 取消收藏文章
     *
     * @return 操作后该文章收藏数量
     */
    @Transactional
    public Long cancelFavour(PostFavourCancelCommand command) {
        Id postId = Id.of(command.getPostId());
        Id userId = Id.of(command.getUserId());
        Favourite favourite = favouriteRepository.load(postId, userId);
        favouriteRepository.remove(favourite);
        return favouriteRepository.count(postId);
    }
}
