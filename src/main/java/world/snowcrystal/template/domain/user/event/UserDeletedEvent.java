package world.snowcrystal.template.domain.user.event;

import lombok.Getter;
import world.snowcrystal.template.domain.common.event.DomainEvent;
import world.snowcrystal.template.domain.user.entity.User;

@Getter
public class UserDeletedEvent extends DomainEvent {

    private final Long userId;
    private final User user;

    public UserDeletedEvent(Long userId, User user) {
        this.userId = userId;
        this.user = user;
    }
}
