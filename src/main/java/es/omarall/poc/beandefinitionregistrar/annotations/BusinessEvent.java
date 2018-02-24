package es.omarall.poc.beandefinitionregistrar.annotations;

import java.lang.annotation.*;

@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface BusinessEvent {
}
