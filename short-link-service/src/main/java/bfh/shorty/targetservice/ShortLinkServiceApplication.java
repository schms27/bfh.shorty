package bfh.shorty.targetservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ShortLinkServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShortLinkServiceApplication.class, args);
	}
}