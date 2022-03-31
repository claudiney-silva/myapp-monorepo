package com.github.claudineysilva.monorepo.apps.bar;

import com.github.claudineysilva.monorepo.libs.contracts.bar.BarDto;
import com.github.claudineysilva.monorepo.libs.contracts.baz.BazDto;
import com.github.claudineysilva.monorepo.libs.contracts.foo.FooDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping
@SpringBootApplication
public class BarApplication {

	public static void main(String[] args) {
		SpringApplication.run(BarApplication.class, args);
	}

	@GetMapping("bar")
	BarDto bar() {
		log.info("Bar");
		return BarDto.builder().id(UUID.randomUUID().toString()).barAttribute("barValue!!").build();
	}

	@GetMapping("bazByProxy")
	BazDto baz() {
		log.info("Baz by proxy");
		var restTemplate = new RestTemplate(); // Yes, should be a bean.
		return restTemplate.getForObject("http://localhost:8081/baz", BazDto.class);
	}

	@GetMapping("fooByProxy")
	FooDto foo() {
		log.info("Foo by proxy");
		var restTemplate = new RestTemplate(); // Yes, should be a bean.
		return restTemplate.getForObject("http://localhost:8082/foo", FooDto.class);
	}
}
