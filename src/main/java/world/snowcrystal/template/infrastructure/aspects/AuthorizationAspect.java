package world.snowcrystal.template.infrastructure.aspects;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import world.snowcrystal.template.domain.login.annotation.AuthorityCheck;
import world.snowcrystal.template.domain.common.enums.ApplicationResponseStatusCode;
import world.snowcrystal.template.domain.common.exception.BusinessException;
import world.snowcrystal.template.domain.login.command.LoginCommandService;
import world.snowcrystal.template.domain.user.entity.User;
import world.snowcrystal.template.domain.user.enums.UserRoleEnum;

/**
 * 权限校验 AOP
 */
@Aspect
@Component
public class AuthorizationAspect {

    @Resource
    private LoginCommandService loginCommandService;

    /**
     * 执行拦截
     */
    @Around("@annotation(authorityCheck)")
    public Object doInterceptor(ProceedingJoinPoint joinPoint, AuthorityCheck authorityCheck) throws Throwable {
        String mustRole = authorityCheck.value().getValue();
        RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
        // 当前登录用户
        User loginUser = loginCommandService.getLoginUserEntity(request);
        UserRoleEnum mustRoleEnum = UserRoleEnum.getEnumByValue(mustRole);
        // 不需要权限，放行
        if (mustRoleEnum == null) {
            return joinPoint.proceed();
        }
        // 必须有该权限才通过
        UserRoleEnum userRoleEnum = loginUser.getRole().getValue();
        // 如果被封号，直接拒绝
        if (UserRoleEnum.BAN.equals(userRoleEnum)) {
            throw new BusinessException(ApplicationResponseStatusCode.NO_AUTH_ERROR);
        }
        // 必须有管理员权限
        if (UserRoleEnum.ADMIN.equals(mustRoleEnum)) {
            // 用户没有管理员权限，拒绝
            if (!UserRoleEnum.ADMIN.equals(userRoleEnum)) {
                throw new BusinessException(ApplicationResponseStatusCode.NO_AUTH_ERROR);
            }
        }
        // 通过权限校验，放行
        return joinPoint.proceed();
    }
}

