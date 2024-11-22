package world.snowcrystal.template.domain.like.dto.query;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import world.snowcrystal.template.domain.common.dto.Page;
import world.snowcrystal.template.domain.identifier.primitive.Id;
import world.snowcrystal.template.domain.like.entity.Like;
import world.snowcrystal.template.domain.like.repository.LikeRepository;
import world.snowcrystal.template.domain.post.entity.Post;
import world.snowcrystal.template.domain.post.repository.PostRepository;

import java.util.List;

@Service
@SuppressWarnings("unused")
@RequiredArgsConstructor
public class PostLikeQueryService {

    private final LikeRepository likeRepository;
    private final PostRepository postRepository;

    public Long count(PostLikeQuery postFavourQuery) {
        return likeRepository.count(Id.of(postFavourQuery.getPostId()));
    }

    public Long countUserFavour(UserLikeQuery userLikeQuery) {
        return likeRepository.countUserLikes(Id.of(userLikeQuery.getUserId()));
    }

    public List<Like> listUserLikes(UserLikeQuery userLikeQuery) {
        return likeRepository.loadUserLikes(Id.of(userLikeQuery.getUserId()));
    }

    /**
     * 最近点赞的排在最前面
     */
    public Page<Post> listOnesLikes(UserLikeQuery postLikeQuery) {
        Id userId = Id.of(postLikeQuery.getUserId());
        List<Like> likes = likeRepository.loadUserLikes(userId);
        List<Id> postIds = likes.stream().map(Like::getPostId).toList();
        postLikeQuery.setSortField("");
        return postRepository.loadBatchByPage(postIds, postLikeQuery);
    }

}
