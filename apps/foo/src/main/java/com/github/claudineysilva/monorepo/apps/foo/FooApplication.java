package com.github.claudineysilva.monorepo.apps.foo;
import com.github.claudineysilva.monorepo.libs.contracts.foo.FooDto;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.UUID;

@RestController
@RequestMapping
@SpringBootApplication
public class FooApplication {

	public static void main(String[] args) {
		SpringApplication.run(FooApplication.class, args);
	}

	@GetMapping("foo")
	FooDto foo() {
		return FooDto.builder().id(UUID.randomUUID().toString()).fooAttribute("fooValue!").build();
	}


}
