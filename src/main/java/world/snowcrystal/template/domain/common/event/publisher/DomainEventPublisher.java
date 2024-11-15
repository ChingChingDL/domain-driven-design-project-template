package world.snowcrystal.template.domain.common.event.publisher;

import world.snowcrystal.template.domain.common.event.DomainEvent;

import java.util.function.Function;

@FunctionalInterface
public interface DomainEventPublisher extends Function<DomainEvent, Void> {

    void publish(DomainEvent event);

    @Override
    default  Void apply(DomainEvent event){
        publish(event);
        return null;
    }
}
