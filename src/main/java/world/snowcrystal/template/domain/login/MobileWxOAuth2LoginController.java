package world.snowcrystal.template.domain.login;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Validated
@RequiredArgsConstructor
public class MobileWxOAuth2LoginController {


    @GetMapping("/mobile-wx/auth2-callback")
    public String callback(@RequestParam("code") @Size(max = 100) String code,
                           @CookieValue(value = "fromUrl", defaultValue = "") String fromUrl,
                           HttpServletResponse response) {
        // rate limiter

        //未绑定时，返回登录界面，并同时带上openId和unionId(包含在jwt中)以便后续成功登陆后自动绑定

        //每次微信登录时均尝试获取最新的昵称和头像并保存
        //登录成功，植入cookie
        //如果没有先前页面，则重定向到默认主页
        return null;
    }
}
