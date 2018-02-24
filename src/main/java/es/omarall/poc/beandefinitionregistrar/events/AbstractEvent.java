package es.omarall.poc.beandefinitionregistrar.events;

import es.omarall.poc.beandefinitionregistrar.annotations.BusinessEvent;

@BusinessEvent
public abstract class AbstractEvent {

    private static String key;

    public static String getKey() {
        return key;
    }
}
