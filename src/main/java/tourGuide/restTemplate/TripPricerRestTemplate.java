package tourGuide.restTemplate;


import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import tourGuide.utils.TripPricerFinal;
import tripPricer.Provider;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
public class TripPricerRestTemplate {

    public List<Provider> getPrice(String apiKey, UUID attractionId, int adults,
                                   int children, int nightsStay, int rewardsPoints) {

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<List<Provider>> entity = new HttpEntity<List<Provider>>(headers);

        return (List<Provider>) restTemplate.exchange
                (TripPricerFinal.TRIP_PRICER_URL, HttpMethod.GET, entity, Provider.class).getBody();
    }
}


