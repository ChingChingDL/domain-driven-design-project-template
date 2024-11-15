package world.snowcrystal.template.domain.user.event;

import lombok.Getter;
import world.snowcrystal.template.domain.common.event.DomainEvent;
import world.snowcrystal.template.domain.common.type.Id;

@Getter
public class UserCreatedEvent extends DomainEvent {

    private final Id userId;

    public UserCreatedEvent(Id userId) {
        this.userId = userId;
    }
    

}
