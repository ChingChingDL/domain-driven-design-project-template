package world.snowcrystal.template.domain.common.event.consumer;


import world.snowcrystal.template.domain.common.event.DomainEvent;

import java.util.function.Consumer;

@FunctionalInterface
public interface DomainEventConsumer extends Consumer<DomainEvent> {
    @Override
    void accept(DomainEvent event);
}
