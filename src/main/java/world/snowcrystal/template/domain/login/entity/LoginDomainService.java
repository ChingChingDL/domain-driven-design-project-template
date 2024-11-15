package world.snowcrystal.template.domain.login.entity;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.bean.WxOAuth2UserInfo;
import org.springframework.stereotype.Component;
import world.snowcrystal.template.domain.common.enums.ApplicationResponseStatusCode;
import world.snowcrystal.template.domain.common.exception.BusinessException;
import world.snowcrystal.template.domain.register.entity.RegisterDomainService;
import world.snowcrystal.template.domain.user.entity.User;
import world.snowcrystal.template.domain.user.repository.UserRepository;
import world.snowcrystal.template.domain.user.type.*;


@Slf4j
@Component
@RequiredArgsConstructor
public class LoginDomainService {
    private final UserRepository userRepository;

    private final RegisterDomainService registerDomainService;


    public User userLogin(Account account, Password password) {
        // 查询用户是否存在
        User user = userRepository.load(account);
        // 用户存在检查，密码检查
        if (user == null || !user.passwordMatches(password)) {
            log.info("user login failed, account cannot match password");
            throw new BusinessException(ApplicationResponseStatusCode.PARAMS_ERROR, "用户不存在或密码错误");
        }
        return user;
    }

    public User loginByMpOpen(WxOAuth2UserInfo wxOAuth2UserInfo) {
        UnionId unionId = new UnionId(wxOAuth2UserInfo.getUnionId());
        MpOpenId mpOpenId = new MpOpenId(wxOAuth2UserInfo.getOpenid());
        // 单机锁
        synchronized (unionId.getValue().intern()) {
            // 查询用户是否已存在
            User user = userRepository.load(unionId);
            // 被封号，禁止登录
            if (user != null && user.isBanned()) {
                throw new BusinessException(ApplicationResponseStatusCode.FORBIDDEN_ERROR, "该用户已被封，禁止登录");
            }
            // 用户不存在则创建
            if (user == null) {
                user = registerDomainService.createUser(new Username(wxOAuth2UserInfo.getNickname()))
                        .setUnionId(unionId)
                        .setMpOpenId(mpOpenId)
                        .changeAvatar(wxOAuth2UserInfo.getHeadImgUrl());
                userRepository.save(user);
            }
            return user;
        }
    }
}
