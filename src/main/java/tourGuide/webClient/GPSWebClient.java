package tourGuide.webClient;


import gpsUtil.location.VisitedLocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import tourGuide.utils.GpsUri;

import java.util.UUID;

@Service
public class GPSWebClient {

    @Autowired
    WebClient.Builder webClientBuilder;


    //@Async
    //Use rest template instead of web client
    public VisitedLocation getUserLocation(UUID userId){
        VisitedLocation  visitedLocation = webClientBuilder.build()
                .get()
                .uri(GpsUri.GPS_GET_USER_LOCATION_URL)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .retrieve()
                .bodyToMono(VisitedLocation.class)
                .block();
        return visitedLocation;
    }
}
