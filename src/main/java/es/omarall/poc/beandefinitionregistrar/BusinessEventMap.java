package es.omarall.poc.beandefinitionregistrar;

import lombok.ToString;

import java.util.HashMap;
import java.util.Map;

@ToString
public class BusinessEventMap extends HashMap<String, Class<?>> {

    public Map<String, Class<?>> getEventMap() {
        return eventMap;
    }

    public void setEventMap(Map<String, Class<?>> eventMap) {
        this.eventMap = eventMap;
    }

    private Map<String, Class<?>> eventMap;

    public Class<?> getClassFromType(String type) {
        return eventMap.get(type);
    }


}
