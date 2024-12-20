package world.snowcrystal.template.domain.user.event;

import lombok.Getter;
import world.snowcrystal.template.domain.common.event.DomainEvent;
import world.snowcrystal.template.domain.identifier.primitive.Id;
import world.snowcrystal.template.domain.user.primitive.Username;

@Getter
public class UsernameChangedEvent extends DomainEvent {

    private final String oldUsername;
    private final String newUsername;
    private final Long userId;

    public UsernameChangedEvent(Id userId, Username oldUsername, Username newUsername) {
        this.userId = userId.getValue();
        this.oldUsername = oldUsername.getValue();
        this.newUsername = newUsername.getValue();
    }
}
