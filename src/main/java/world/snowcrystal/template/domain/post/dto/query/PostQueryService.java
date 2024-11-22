package world.snowcrystal.template.domain.post.dto.query;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import world.snowcrystal.template.domain.common.dto.Page;
import world.snowcrystal.template.domain.identifier.primitive.Id;
import world.snowcrystal.template.domain.login.command.LoginCommandService;
import world.snowcrystal.template.domain.post.assembler.PostAssembler;
import world.snowcrystal.template.domain.post.entity.Post;
import world.snowcrystal.template.domain.post.repository.PostRepository;
import world.snowcrystal.template.domain.user.entity.User;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostQueryService {

    private final PostAssembler postAssembler;
    private final PostRepository postRepository;
    private final LoginCommandService loginCommandService;


    public Post findById(final Long id) {
        return postRepository.load(Id.of(id));
    }

    public Page<PostQueryResponse> page(PostQuery postQueryRequest) {
        List<PostQueryResponse> list = Optional
                .ofNullable(postRepository.searchPage(postQueryRequest))
                .orElse(Page.empty(Post.class)).stream()
                .map(postAssembler::toQueryResponse).toList();
        return Page.<PostQueryResponse>builder().records(list).build();
    }


    public Page<PostQueryResponse> mine(PostQuery postQueryRequest, HttpServletRequest httpServletRequest) {
        User loginUser = loginCommandService.getLoginUserEntity(httpServletRequest);
        postQueryRequest.setUserId(loginUser.getId().getValue());
        return page(postQueryRequest);
    }


}
