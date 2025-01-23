package edu.harvard.hms.dbmi.avillach.metrics;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class PicSureMetricsApplication {

	public static void main(String[] args) {
		SpringApplication.run(PicSureMetricsApplication.class, args);
	}

}
