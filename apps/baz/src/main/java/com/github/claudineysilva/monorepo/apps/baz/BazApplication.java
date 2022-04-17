package com.github.claudineysilva.monorepo.apps.baz;

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
public class BazApplication {

	@Value("${app.foo}")
	private String appFoo;

	public static void main(String[] args) {
		SpringApplication.run(BazApplication.class, args);
	}

	@GetMapping("app-baz")
	BazDto baz() {
		return BazDto.builder().id(UUID.randomUUID().toString()).bazAttribute("bazValue").build();
	}

	@GetMapping("app-baz/track")
	TrackDto track() {
		log.info("Track by proxy");
		RestTemplate restTemplate = new RestTemplate(); // Yes, should be a bean.
		BazDto bazDto = baz();
		FooDto fooDto = restTemplate.getForObject(appFoo + "/app-foo", FooDto.class);
		return TrackDto.builder()
				.id(bazDto.getId())
				.barAttribute(bazDto.getBazAttribute())
				.fooAttribute(fooDto.getFooAttribute())
				.build();
	}

}
