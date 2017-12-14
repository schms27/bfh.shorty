package bfh.shorty.targetservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class TargetserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TargetserviceApplication.class, args);
	}
}
