package com.github.claudineysilva.monorepo.apps.foo;
import com.github.claudineysilva.monorepo.libs.contracts.foo.FooDto;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping
@SpringBootApplication
public class FooApplication {

	public static void main(String[] args) {
		SpringApplication.run(FooApplication.class, args);
	}

	@SneakyThrows
	@GetMapping("app-foo")
	FooDto foo() {
		//int sleep = new Random().nextInt(3);
		//log.info(String.format("Sleeping for %s seconds", sleep));
		//Thread.sleep(sleep * 1000);
		return FooDto.builder().id(UUID.randomUUID().toString()).fooAttribute("fooValue").build();
	}


}
