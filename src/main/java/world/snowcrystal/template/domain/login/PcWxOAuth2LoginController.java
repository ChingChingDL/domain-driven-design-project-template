package world.snowcrystal.template.domain.login;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import world.snowcrystal.template.domain.login.entity.WxJwtService;

@Slf4j
@Validated
@RequiredArgsConstructor
public class PcWxOAuth2LoginController {
//    private final MemberRepository memberRepository;
//    private final LoginCommandService loginCommandService;
    private final WxJwtService wxJwtService;
//    private final JwtCookieFactory jwtCookieFactory;
//    private final MryRateLimiter mryRateLimiter;
//    private final PropertyService propertyService;
//    private final PcWxAuthService pcWxAuthService;

    @GetMapping("/pc-wx/auth2-callback")
    public String callback(@RequestParam("code") @Size(max = 100) String code,
                           @CookieValue(value = "fromUrl", defaultValue = "") String fromUrl,
                           HttpServletResponse response) {
        // rate limiter

        //未绑定时，返回登录界面，并同时带上openId和unionId(包含在jwt中)以便后续成功登陆后自动绑定

        //每次微信登录时均获取最新的昵称和头像并保存

        //登录成功，植入cookie

        //如果没有先前页面，则重定向到默认主页
        return null;
    }
}
