package world.snowcrystal.template.application.controller;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import world.snowcrystal.template.application.service.PostThumbService;
import world.snowcrystal.template.domain.common.ApplicationResponse;
import world.snowcrystal.template.domain.common.enums.ApplicationResponseStatusCode;
import world.snowcrystal.template.domain.common.exception.BusinessException;
import world.snowcrystal.template.domain.login.command.LoginCommandService;
import world.snowcrystal.template.domain.post.dto.command.PostThumbAddCommand;
import world.snowcrystal.template.domain.user.entity.User;

/**
 * 文章点赞接口
 *
 *
 *
 */
//@RestController
@RequestMapping("/post_thumb")
@Slf4j
public class PostThumbController {

    @Resource
    private PostThumbService postThumbService;

    @Resource
    private LoginCommandService loginCommandService;

    /**
     * 点赞 / 取消点赞
     *
     * @param postThumbAddCommand
     * @param request
     * @return resultNum 本次点赞变化数
     */
    @PostMapping("/")
    public ApplicationResponse<Integer> doThumb(@RequestBody PostThumbAddCommand postThumbAddCommand,
                                                HttpServletRequest request) {
        if (postThumbAddCommand == null || postThumbAddCommand.getPostId() <= 0) {
            throw new BusinessException(ApplicationResponseStatusCode.PARAMS_ERROR);
        }
        // 登录才能点赞
        final User loginUser = loginCommandService.getLoginUserEntity(request);
        long postId = postThumbAddCommand.getPostId();
//        int result = postThumbService.doPostThumb(postId, loginUser);
        return ApplicationResponse.success(0);
    }

}
