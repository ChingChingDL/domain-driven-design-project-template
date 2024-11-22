package world.snowcrystal.template.domain.post.dto.command;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import world.snowcrystal.template.domain.common.command.DeleteCommand;
import world.snowcrystal.template.domain.favorite.dto.command.PostFavourAddCommand;
import world.snowcrystal.template.domain.like.dto.command.PostLikeAddCommand;
import world.snowcrystal.template.domain.identifier.primitive.Id;
import world.snowcrystal.template.domain.login.command.LoginCommandService;
import world.snowcrystal.template.domain.post.entity.Post;
import world.snowcrystal.template.domain.post.entity.PostFactory;
import world.snowcrystal.template.domain.post.primitive.*;
import world.snowcrystal.template.domain.post.repository.PostRepository;
import world.snowcrystal.template.domain.user.entity.User;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostCommandService {


    private final PostRepository postRepository;
    private final PostFactory postFactory;
    private final LoginCommandService loginCommandService;

    @Transactional
    public Id addPost(PostAddCommand postAddCommand, HttpServletRequest request) {

        // TODO Rate limit
        User user = loginCommandService.getLoginUserEntity(request);
        List<Tag> tags = Optional.of(postAddCommand.getTags()).orElse(List.of()).stream().distinct().map(Tag::of).toList();
        Title title = Title.of(postAddCommand.getTitle());
        Content content = Content.of(postAddCommand.getContent());
        Post post = postFactory.create(content, tags, title, user);
        postRepository.save(post);
        return post.getId();
    }

    @Transactional
    public void deletePost(DeleteCommand command, HttpServletRequest request) {
        User user = loginCommandService.getLoginUserEntity(request);
        Post post = postRepository.load(Id.of(command.getId()), user.getId());
        post.delete();
        postRepository.save(post);
    }


    @Transactional
    public Post editPost(PostEditCommand postEditCommand,HttpServletRequest request) {
        User user = loginCommandService.getLoginUserEntity(request);
        String content = postEditCommand.getContent();
        String title = postEditCommand.getTitle();
        List<String> tags = postEditCommand.getTags();
        Post post = postRepository.load(Id.of(postEditCommand.getId()),user.getId());
        if (content != null) {
            post.modifyContent(Content.of(content));
        }
        if (title != null) {
            post.modifyTitle(Title.of(title));
        }
        if (tags != null) {
            post.modifyTags(tags.stream().map(Tag::of).toList());
        }
        postRepository.save(post);
        return post;
    }


}
