package world.snowcrystal.template.domain.register.component;

import world.snowcrystal.template.domain.user.primitive.Username;


/**
 * 生成默认的用户名
 * @author tianqing
 */
public interface UsernameGenerator {
    Username generate();
}
