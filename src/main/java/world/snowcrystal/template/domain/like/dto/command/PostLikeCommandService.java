package world.snowcrystal.template.domain.like.dto.command;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import world.snowcrystal.template.domain.identifier.primitive.Id;
import world.snowcrystal.template.domain.like.entity.Like;
import world.snowcrystal.template.domain.like.entity.LikeFactory;
import world.snowcrystal.template.domain.like.repository.LikeRepository;
import world.snowcrystal.template.domain.login.command.LoginCommandService;

@Service
@RequiredArgsConstructor
public class PostLikeCommandService {

    private final LikeRepository likeRepository;
    private final LoginCommandService loginCommandService;
    private final LikeFactory likeFactory;

    /**
     * 点赞文章
     *
     * @return 操作后该文章点赞数量
     */
    @Transactional
    public PostLikeResponse doPostLike(PostLikeAddCommand command, HttpServletRequest request) {
        Id postId = Id.of(command.getPostId());
        Id userId = loginCommandService.getLoginUserEntity(request).getId();
        Like like = likeFactory.createLike(postId, userId);
        likeRepository.save(like);
        return new PostLikeResponse(likeRepository.count(postId));
    }
//    @Transactional
//    public Likes like(PostLikeAddCommand postLikeAddCommand) {
//        Post post = postRepository.load(Id.of(postLikeAddCommand.getPostId()), Id.of(postLikeAddCommand.getUserId()));
//        Likes likes = post.incrementLikesAndGet();
//        postRepository.save(post);
//        return likes;
//    }

    /**
     * 取消点赞文章
     *
     * @return 取消点赞文章后该文章点赞数量
     */
    @Transactional
    public PostLikeCancelResponse cancelLike(PostLikeCancelCommand command,HttpServletRequest request) {
        Id userId = loginCommandService.getLoginUserEntity(request).getId();
        Id postId = Id.of(command.getPostId());
        Like like = likeRepository.load(postId, userId);
        likeRepository.remove(like);
        return new PostLikeCancelResponse(likeRepository.count(postId));
    }


}
