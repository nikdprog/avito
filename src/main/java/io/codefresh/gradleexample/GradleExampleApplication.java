package io.codefresh.gradleexample;

import org.hibernate.Cache;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableAutoConfiguration
@SpringBootApplication
public class GradleExampleApplication {


	private static SessionFactory sessionFactory;

	public static void main(String[] args) {
		//Session session = s;


		SpringApplication.run(GradleExampleApplication.class, args);
	}

}
