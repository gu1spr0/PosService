package com.pgt360.payment;

import com.pgt360.payment.server.Server;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@RequiredArgsConstructor
@EnableJpaRepositories(basePackages = {"com.pgt360.payment"})
@EntityScan(basePackages = {"com.pgt360.payment"})
@SpringBootApplication
public class PaymentApplication {

	public static void main(String[] args) {
		SpringApplication.run(PaymentApplication.class, args);
	}
	private final Server server;

	@Bean
	public ApplicationListener<ApplicationReadyEvent> readyEventApplicationListener(){
		return new ApplicationListener<ApplicationReadyEvent>() {
			@Override
			public void onApplicationEvent(ApplicationReadyEvent event) {
				server.start();
			}
		};
	}
}
