package world.snowcrystal.template.domain.common.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;
import world.snowcrystal.template.domain.common.enums.EventStatusEnum;

import java.time.Clock;

@Getter
public class DomainEvent extends ApplicationEvent {


    private final int status = EventStatusEnum.CREATED.ordinal();

    //事件ID
    private String id;


    public DomainEvent() {
        super(Clock.systemDefaultZone());
    }
}
