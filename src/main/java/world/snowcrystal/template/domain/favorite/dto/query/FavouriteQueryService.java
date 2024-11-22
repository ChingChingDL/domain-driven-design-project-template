package world.snowcrystal.template.domain.favorite.dto.query;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import world.snowcrystal.template.domain.common.dto.Page;
import world.snowcrystal.template.domain.favorite.entity.Favourite;
import world.snowcrystal.template.domain.favorite.repository.FavouriteRepository;
import world.snowcrystal.template.domain.identifier.primitive.Id;
import world.snowcrystal.template.domain.post.entity.Post;
import world.snowcrystal.template.domain.post.repository.PostRepository;

import java.util.List;

@Service
@SuppressWarnings("unused")
@RequiredArgsConstructor
public class FavouriteQueryService {


    private final FavouriteRepository favouriteRepository;
    private final PostRepository postRepository;

    public Long count(PostFavouriteQuery postFavourQuery) {
        return favouriteRepository.count(Id.of(postFavourQuery.getPostId()));
    }

    public Long countUserFavour(UserFavouriteQuery userFavouriteQuery) {
        return favouriteRepository.countUserFavours(Id.of(userFavouriteQuery.getUserId()));
    }

    public List<Favourite> listUserFavours(UserFavouriteQuery userFavouriteQuery) {
        return favouriteRepository.loadUserFavours(Id.of(userFavouriteQuery.getUserId()));
    }

    /**
     * 最近收藏的排在最前面
     */
    public Page<Post> listOnesFavours(UserFavouriteQuery postFavouriteQuery) {
        Id userId = Id.of(postFavouriteQuery.getUserId());
        List<Favourite> favourites = favouriteRepository.loadUserFavours(userId);
        List<Id> postIds = favourites.stream().map(Favourite::getPostId).toList();
        postFavouriteQuery.setSortField("");
        return postRepository.loadBatchByPage(postIds, postFavouriteQuery);
    }

}
