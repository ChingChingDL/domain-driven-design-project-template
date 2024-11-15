package world.snowcrystal.template.infrastructure.component;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import world.snowcrystal.template.domain.identifier.primitive.Id;
import world.snowcrystal.template.domain.identifier.component.SnowFlakeIdentifierGenerator;

public class DefaultSnowFlakeGenerator implements SnowFlakeIdentifierGenerator {

    private final Snowflake INSTANCE = IdUtil.getSnowflake();

    @Override
    public Id generate() {
        return Id.of(INSTANCE.nextId());
    }
}
