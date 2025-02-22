package br.com.loanServer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EntityScan(basePackages = "br.com")
@EnableEurekaClient
@EnableDiscoveryClient
@EnableFeignClients
public class LoanServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoanServerApplication.class, args);
	}

}