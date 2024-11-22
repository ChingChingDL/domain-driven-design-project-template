package world.snowcrystal.template.domain.login.annotation;

import world.snowcrystal.template.domain.user.enums.UserRoleEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 权限校验
 *
 *
 *
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AuthorityCheck {

    /**
     * 必须有某个角色
     */
    UserRoleEnum value() default UserRoleEnum.ADMIN;

}

