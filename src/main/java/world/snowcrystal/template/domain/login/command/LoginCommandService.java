package world.snowcrystal.template.domain.login.command;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.bean.WxOAuth2UserInfo;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import world.snowcrystal.template.application.assembler.Assembler;
import world.snowcrystal.template.domain.user.entity.User;
import world.snowcrystal.template.domain.common.enums.ApplicationResponseStatusCode;
import world.snowcrystal.template.domain.common.enums.SessionAttributeEnum;
import world.snowcrystal.template.domain.common.exception.BusinessException;
import world.snowcrystal.template.domain.login.entity.LoginDomainService;
import world.snowcrystal.template.domain.user.dto.command.UserLoginCommand;
import world.snowcrystal.template.domain.user.primitive.Account;
import world.snowcrystal.template.domain.user.primitive.Password;

@Slf4j
@Component
@RequiredArgsConstructor
public class LoginCommandService {
    private final LoginDomainService loginDomainService;
    private final Assembler assembler;

    @Transactional
    public LoginCommandResponse userLogin(UserLoginCommand userLoginCommand, HttpServletRequest request) {
        Account account = Account.of(userLoginCommand.getAccount());
        Password password = Password.of(userLoginCommand.getPassword());
        User user = loginDomainService.userLogin(account, password);
        // 记录用户的登录态
        request.getSession().setAttribute(SessionAttributeEnum.LOGIN_USER.getValue(), user);
        return assembler.toLoginUserVO(user);
    }

    @Transactional
    public boolean userLogout(HttpServletRequest request) {
        if (request.getSession().getAttribute(SessionAttributeEnum.LOGIN_USER.getValue()) == null) {
            throw new BusinessException(ApplicationResponseStatusCode.OPERATION_ERROR, "未登录");
        }
        // 移除登录态
        request.getSession().removeAttribute(SessionAttributeEnum.LOGIN_USER.getValue());
        return true;
    }

    /**
     * 获取当前登录用户
     */
    public LoginCommandResponse getLoginUser(HttpServletRequest request) {
        return assembler.toLoginUserVO(getLoginUserEntity(request));
    }

    /**
     * 获取当前登录用户（允许未登录）
     */
    public LoginCommandResponse getLoginUserPermitNull(HttpServletRequest request) {
        // 先判断是否已登录
        User user = (User) request.getSession().getAttribute(SessionAttributeEnum.LOGIN_USER.getValue());
        if (user == null) {
            return null;
        }
        // 从数据库查询（追求性能的话可以注释，直接走缓存）
        return assembler.toLoginUserVO(user);
    }

    public User getLoginUserEntity(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute(SessionAttributeEnum.LOGIN_USER.getValue());
        if (user == null) {
            throw new BusinessException(ApplicationResponseStatusCode.NOT_LOGIN_ERROR);
        }
        return user;
    }


    public LoginCommandResponse loginByMpOpen(WxOAuth2UserInfo wxOAuth2UserInfo, HttpServletRequest request) {
        // 记录用户的登录态
        User user = loginDomainService.loginByMpOpen(wxOAuth2UserInfo);
        request.getSession().setAttribute(SessionAttributeEnum.LOGIN_USER.getValue(), user);
        return assembler.toLoginUserVO(user);
    }
}
