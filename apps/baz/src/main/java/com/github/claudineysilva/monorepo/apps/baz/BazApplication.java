package com.github.claudineysilva.monorepo.apps.baz;

import com.github.claudineysilva.monorepo.libs.contracts.baz.BazDto;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.UUID;

@RestController
@RequestMapping
@SpringBootApplication
public class BazApplication {

	public static void main(String[] args) {
		SpringApplication.run(BazApplication.class, args);
	}

	@GetMapping("app-baz")
	BazDto baz() {
		return BazDto.builder().id(UUID.randomUUID().toString()).bazAttribute("bazValue").build();
	}

}
