package com.github.claudineysilva.monorepo.apps.bar;

import com.github.claudineysilva.monorepo.libs.contracts.bar.BarDto;
import com.github.claudineysilva.monorepo.libs.contracts.bar.TrackDto;
import com.github.claudineysilva.monorepo.libs.contracts.baz.BazDto;
import com.github.claudineysilva.monorepo.libs.contracts.foo.FooDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
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

	@Value("${app.baz}")
	private String appBaz;

	@Value("${app.foo}")
	private String appFoo;

	@GetMapping("app-bar")
	BarDto bar() {
		log.info("Bar");
		return BarDto.builder().id(UUID.randomUUID().toString()).barAttribute("app-bar-from-gh-packages").build();
	}

	@GetMapping("app-bar/baz")
	BazDto baz() {
		log.info("Baz by proxy");
		RestTemplate restTemplate = new RestTemplate(); // Yes, should be a bean.
		return restTemplate.getForObject(appBaz + "/app-baz", BazDto.class);
	}

	@GetMapping("app-bar/foo")
	FooDto foo() {
		log.info("Foo by proxy");
		RestTemplate restTemplate = new RestTemplate(); // Yes, should be a bean.
		return restTemplate.getForObject(appFoo + "/app-foo", FooDto.class);
	}

	@GetMapping("app-bar/track")
	TrackDto track() {
		log.info("Track by proxy");
		RestTemplate restTemplate = new RestTemplate(); // Yes, should be a bean.
		BarDto barDto = bar();
		TrackDto trackDto = restTemplate.getForObject(appBaz + "/app-baz/track", TrackDto.class);
		trackDto.setBarAttribute(bar().getBarAttribute());
		return trackDto;
	}
}
