package es.omarall.poc.beandefinitionregistrar;

import lombok.ToString;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@ToString
public class BusinessEventMap extends HashMap<String, Class<?>> {

    private Map<String, Class<?>> eventMap;

    public Map<String, Class<?>> getEventMap() {
        return eventMap;
    }

    public void setEventMap(Map<String, Class<?>> eventMap) {
        this.eventMap = eventMap;
    }

    public Class<?> getClassFromType(String type) {
        Class<?> cl = eventMap.get(type);
        if (cl != null) {
            return cl;
        }

        throw new NoSuchElementException(String.format("ReadyEventMap - There is no registered class for that type. Please provide a concrete(nonabstract) @ReadyEvent with type %s in your classpath.", type));
    }
}
