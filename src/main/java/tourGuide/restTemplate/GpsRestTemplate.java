package tourGuide.restTemplate;


import gpsUtil.location.Attraction;
import gpsUtil.location.VisitedLocation;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import tourGuide.utils.GpsUtilFinal;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
public class GpsRestTemplate {


    //@Async
    //Use rest template instead of web client
    public VisitedLocation getUserLocation(UUID userId) {

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<VisitedLocation> entity = new HttpEntity<VisitedLocation>(headers);

        return restTemplate.exchange
                (GpsUtilFinal.GPS_GET_USER_LOCATION_URL, HttpMethod.GET, entity, VisitedLocation.class).getBody();

    }

    public List<Attraction> getAttractions() {

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<List<Attraction>> entity = new HttpEntity<List<Attraction>>(headers);

        return (List<Attraction>) restTemplate.exchange
                (GpsUtilFinal.GPS_GET_ATTRACTION_URL, HttpMethod.GET, entity, Attraction.class).getBody();

    }

    }
