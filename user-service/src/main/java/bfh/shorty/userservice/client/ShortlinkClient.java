package bfh.shorty.userservice.client;

@Configuration
@EnableHystrixDashboard
@EnableTurbine
@EnableCircuitBreaker
public class ShortLinkClient {

    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Autowired
    RestTemplate restTemplate;

    public Resource<ShortLink> getShortLink(String id) {
        ShortLink shortLink = restTemplate.getForObject("http://localhost:8080/shortlinks/" + id, ShortLink.class);
    }

    public Resource<ShortLink> getFallbackShortlink(String id) {
        ShortLink shortLink = new ShortLink();
        return new Resource(shortLink);
    }

}