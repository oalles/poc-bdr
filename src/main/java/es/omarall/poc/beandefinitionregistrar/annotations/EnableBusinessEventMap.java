package es.omarall.poc.beandefinitionregistrar.annotations;

import es.omarall.poc.beandefinitionregistrar.BusinessEventMapRegistrar;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(BusinessEventMapRegistrar.class)
public @interface EnableBusinessEventMap {

    String[] value() default {};

    String[] basePackages() default {};
}
