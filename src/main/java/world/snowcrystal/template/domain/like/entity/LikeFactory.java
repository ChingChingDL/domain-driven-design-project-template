package world.snowcrystal.template.domain.like.entity;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import world.snowcrystal.template.domain.common.enums.ApplicationResponseStatusCode;

import world.snowcrystal.template.domain.identifier.component.SnowFlakeIdentifierGenerator;
import world.snowcrystal.template.domain.identifier.primitive.Id;
import world.snowcrystal.template.domain.like.exception.AlreadyThumpUpException;
import world.snowcrystal.template.domain.like.repository.LikeRepository;

@Component
@RequiredArgsConstructor
public class LikeFactory {

    private final SnowFlakeIdentifierGenerator idGenerator;

    private final LikeRepository likeRepository;


    public Like createLike(Id postId, Id userId) {

        // 检查该用户是否已经收藏过该文章
        if(likeRepository.exists(postId, userId)){
            throw new AlreadyThumpUpException(ApplicationResponseStatusCode.NO_OPERATION,"你已经点赞过了");
        }
        return Like.builder()
                .postId(postId)
                .id(idGenerator.generate())
                .userId(userId)
                .build();
    }


}
