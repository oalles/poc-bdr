package es.omarall.poc.beandefinitionregistrar;

import es.omarall.poc.beandefinitionregistrar.annotations.EnableBusinessEventMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableBusinessEventMap(basePackages = "es.omarall.poc.beandefinitionregistrar.events")
@Slf4j
public class PocBdrApplication {

	public static void main(String[] args) {
		SpringApplication.run(PocBdrApplication.class, args);
	}

	@Bean
	ApplicationRunner applicationRunner(BusinessEventMap map) {
		return (args) -> {
			log.warn("Map: {}", map.toString());
		};
	}
}
