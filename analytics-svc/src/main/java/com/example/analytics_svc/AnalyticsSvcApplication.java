package com.example.analytics_svc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
public class AnalyticsSvcApplication {

	public static void main(String[] args) {
		SpringApplication.run(AnalyticsSvcApplication.class, args);
	}

}
