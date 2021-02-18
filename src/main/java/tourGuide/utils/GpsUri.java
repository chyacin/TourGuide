package tourGuide.utils;

import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

public class GpsUri {

    @Bean
    public WebClient.Builder getWebClient(){
        return WebClient.builder();
    }

    public static final String GPS_GET_USER_LOCATION_URL = "http://localhost:7080/userLocation";

}
