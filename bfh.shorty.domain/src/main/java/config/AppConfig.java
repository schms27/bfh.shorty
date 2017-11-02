package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import service.*;


/**
 * Created by simon.schmid on 19.10.2017.
 */
@Configuration
public class AppConfig {
    @Bean
    public ConsumerService consumer() {
        ConsumerService consumerService = new Consumer();
        return consumerService;
    }

    @Bean
    public ProducerService producer() {
        ProducerService producerService = new Producer();
        producerService.setProduct("an apple");

        return producerService;
    }

    @Bean
    public StockService stockService() {
        StockService stockService = new StockServiceImpl();
        return stockService;
    }
}
