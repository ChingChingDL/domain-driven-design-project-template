package world.snowcrystal.template.domain.user.dto.query;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import world.snowcrystal.template.domain.user.assembler.UserAssembler;
import world.snowcrystal.template.domain.common.dto.Page;
import world.snowcrystal.template.domain.common.exception.ThrowUtils;
import world.snowcrystal.template.domain.user.entity.User;
import world.snowcrystal.template.domain.common.enums.ApplicationResponseStatusCode;
import world.snowcrystal.template.domain.identifier.primitive.Id;
import world.snowcrystal.template.domain.user.repository.UserRepository;
import world.snowcrystal.template.domain.user.service.UserDomainService;
import world.snowcrystal.template.infrastructure.repository.po.UserPO;

import java.util.List;

/**
 * 用户服务实现
 */
@Service
@Slf4j
public class UserQueryService {

    @Resource
    private UserDomainService userDomainService;

    @Resource
    private UserRepository userRepository;

    @Resource
    private UserAssembler userAssembler;


    public UserQueryResponse getUserVO(Id id) {
        User user = userRepository.load(id);
        return userAssembler.toUserVO(user);

    }
    /**
     * 获取脱敏的用户信息
     */

    public List<UserQueryResponse> getUserVO(List<User> userList) {
        return List.of();
    }


    public User getById(Id id) {
        return userRepository.load(id);
    }


    public Page<UserPO> page(UserQuery userQueryRequest) {
        return userRepository.loadBatch(userQueryRequest);
    }


    public Page<UserQueryResponse> pageVO(UserQuery userQueryRequest) {
        // rate limiter
        long size = userQueryRequest.getSize();
        // 限制爬虫
        ThrowUtils.throwIf(size > 20, ApplicationResponseStatusCode.PARAMS_ERROR);

        return null;
    }

}
