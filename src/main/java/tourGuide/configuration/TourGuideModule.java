package tourGuide.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import gpsUtil.GpsUtil;
import rewardCentral.RewardCentral;
import tourGuide.restTemplate.GpsRestTemplate;
import tourGuide.service.RewardsService;

@Configuration
public class TourGuideModule {
	
	@Bean
	public GpsRestTemplate getGpsRestTemplate() {
		return new GpsRestTemplate();
	}
	
	@Bean
	public RewardsService getRewardsService() {
		return new RewardsService(getGpsRestTemplate(), getRewardCentral());
	}
	
	@Bean
	public RewardCentral getRewardCentral() {
		return new RewardCentral();
	}
	
}
