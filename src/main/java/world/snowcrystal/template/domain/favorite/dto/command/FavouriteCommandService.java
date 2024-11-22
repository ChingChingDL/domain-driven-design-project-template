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
    public PostFavouriteResponse doPostFavour(PostFavourAddCommand command, HttpServletRequest request) {
        Id postId = Id.of(command.getPostId());
        Id userId = loginCommandService.getLoginUserEntity(request).getId();
        Favourite favourite = favouriteFactory.createFavourite(postId, userId);
        favouriteRepository.save(favourite);
        return new PostFavouriteResponse(favouriteRepository.count(postId));
    }
//    @Transactional
//    public Favours favour(PostFavourAddCommand postFavourAddCommand, HttpServletRequest request) {
//        User loginUser = loginCommandService.getLoginUserEntity(request);
//        Post post = postRepository.load(Id.of(postFavourAddCommand.getPostId()));
//        Favours favours = post.incrementFavoursAndGet();
//        postRepository.save(post);
//        return favours;
//    }

    /**
     * 取消收藏文章
     *
     * @return 操作后该文章收藏数量
     */
    @Transactional
    public PostFavouriteCancelResponse cancelFavour(PostFavouriteCancelCommand command, HttpServletRequest request) {
        Id userId = loginCommandService.getLoginUserEntity(request).getId();
        Id postId = Id.of(command.getPostId());
        Favourite favourite = favouriteRepository.load(postId, userId);
        favouriteRepository.remove(favourite);
        return new PostFavouriteCancelResponse(favouriteRepository.count(postId));
    }
}
