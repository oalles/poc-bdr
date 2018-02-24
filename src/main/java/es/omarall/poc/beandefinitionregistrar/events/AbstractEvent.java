package es.omarall.poc.beandefinitionregistrar.events;

import es.omarall.poc.beandefinitionregistrar.annotations.BusinessEvent;
import lombok.Data;
import lombok.Getter;

import java.util.UUID;

@BusinessEvent
@Getter
@Data
public abstract class AbstractEvent {

    public AbstractEvent(){}

}
