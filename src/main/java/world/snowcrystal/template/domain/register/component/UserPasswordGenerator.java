package world.snowcrystal.template.domain.register.component;

import world.snowcrystal.template.domain.user.primitive.Password;

/**
 * 至少生成 16 个字符
 * @author tianqing
 */
public interface UserPasswordGenerator {
    Password generate();
}
