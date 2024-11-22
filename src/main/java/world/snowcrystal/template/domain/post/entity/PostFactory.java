package world.snowcrystal.template.domain.post.entity;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import world.snowcrystal.template.domain.identifier.component.SnowFlakeIdentifierGenerator;
import world.snowcrystal.template.domain.post.primitive.Content;
import world.snowcrystal.template.domain.post.primitive.Tag;
import world.snowcrystal.template.domain.post.primitive.Title;
import world.snowcrystal.template.domain.post.repository.PostRepository;
import world.snowcrystal.template.domain.user.entity.User;

import java.util.List;


@Component
@RequiredArgsConstructor
public class PostFactory {

    private final PostRepository postRepository;
    private final SnowFlakeIdentifierGenerator identifierGenerator;

    /**
     * 将自动生成 id
     */
    public Post create(Content content, List<Tag> tags, Title title, User user) {

        return Post.builder()
                .title(title)
                .userId(user.getId())
                .id(identifierGenerator.generate())
                .content(content)
                .tags(tags)
                .build();
    }

}
