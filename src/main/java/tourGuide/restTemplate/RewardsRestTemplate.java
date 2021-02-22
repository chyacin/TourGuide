package tourGuide.restTemplate;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import tourGuide.utils.RewardsCentralFinal;

import java.util.Arrays;
import java.util.UUID;

@Service
public class RewardsRestTemplate {

    public int getAttractionRewardPoints(UUID attractionId, UUID userId){

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Integer> entity = new HttpEntity<Integer>(headers);

        return restTemplate.exchange
                (RewardsCentralFinal.REWARDS_GET_ATTRACTION_REWARD_POINTS_URL, HttpMethod.GET, entity, Integer.class).getBody();
    }
}
