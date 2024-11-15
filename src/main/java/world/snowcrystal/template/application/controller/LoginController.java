package world.snowcrystal.template.application.controller;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.bean.WxOAuth2UserInfo;
import me.chanjar.weixin.common.bean.oauth2.WxOAuth2AccessToken;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import world.snowcrystal.template.domain.common.ApplicationResponse;
import world.snowcrystal.template.domain.login.command.LoginCommandResponse;
import world.snowcrystal.template.domain.login.command.LoginCommandService;
import world.snowcrystal.template.domain.user.dto.command.UserLoginCommand;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
public class LoginController {
    @Resource
    private LoginCommandService loginCommandService;

    @Resource
    private WxMpService wxMpService;

    /**
     * 用户登录
     */
    @PostMapping("/login")
    public ApplicationResponse<LoginCommandResponse> userLogin(@Nonnull @RequestBody UserLoginCommand userLoginCommand, HttpServletRequest request) {
        LoginCommandResponse loginCommandResponse = loginCommandService.userLogin(userLoginCommand, request);
        return ApplicationResponse.success(loginCommandResponse);
    }

    /**
     * 用户登录（微信开放平台）
     */
    @GetMapping("/login/wx_open")
    public ApplicationResponse<LoginCommandResponse> userLoginByWxOpen(HttpServletRequest request,
                                                                       @RequestParam("code") String code) throws WxErrorException {
        WxOAuth2AccessToken accessToken = wxMpService.getOAuth2Service().getAccessToken(code);
        WxOAuth2UserInfo userInfo = wxMpService.getOAuth2Service().getUserInfo(accessToken, code);
        return ApplicationResponse.success(loginCommandService.loginByMpOpen(userInfo, request));
    }

    /**
     * 用户注销
     */
    @PostMapping("/logout")
    public ApplicationResponse<Boolean> userLogout(@Nonnull HttpServletRequest request) {
//        log.info("User[{}] tried log out.", user.getMemberId());
        return ApplicationResponse.success(loginCommandService.userLogout(request));
    }

    /**
     * 获取当前登录用户 VO
     */
    @GetMapping("/user/me")
    public ApplicationResponse<LoginCommandResponse> getLoginUser(HttpServletRequest request) {
        LoginCommandResponse user = loginCommandService.getLoginUser(request);
        return ApplicationResponse.success(user);
    }

    // endregion


}
