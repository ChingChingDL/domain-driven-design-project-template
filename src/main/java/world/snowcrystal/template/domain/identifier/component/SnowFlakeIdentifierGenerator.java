package world.snowcrystal.template.domain.identifier.component;

import world.snowcrystal.template.domain.identifier.primitive.Id;

public interface SnowFlakeIdentifierGenerator {

    Id generate();
}
