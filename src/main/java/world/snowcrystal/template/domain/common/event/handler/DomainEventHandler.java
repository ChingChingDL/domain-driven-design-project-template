package world.snowcrystal.template.domain.common.event.handler;

import world.snowcrystal.template.domain.common.event.DomainEvent;

public interface DomainEventHandler {

    void handleEvent(Object event);

    boolean supportsEvent(DomainEvent event);
}
