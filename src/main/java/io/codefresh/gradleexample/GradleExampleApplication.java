package io.codefresh.gradleexample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableAutoConfiguration
@SpringBootApplication
public class GradleExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(GradleExampleApplication.class, args);
	}

}
