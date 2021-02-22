package tourGuide;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.junit.Ignore;
import org.junit.Test;

import gpsUtil.GpsUtil;
import gpsUtil.location.Attraction;
import gpsUtil.location.VisitedLocation;
import rewardCentral.RewardCentral;
import tourGuide.helper.InternalTestHelper;
import tourGuide.restTemplate.GpsRestTemplate;
import tourGuide.restTemplate.RewardsRestTemplate;
import tourGuide.service.RewardsService;
import tourGuide.service.TourGuideService;
import tourGuide.model.User;
import tourGuide.model.UserReward;

public class TestRewardsService {

	@Test
	public void userGetRewards() {
		GpsRestTemplate gpsRestTemplate = new GpsRestTemplate();
		RewardsService rewardsService = new RewardsService(gpsRestTemplate, new RewardsRestTemplate());

		InternalTestHelper.setInternalUserNumber(0);
		TourGuideService tourGuideService = new TourGuideService(gpsRestTemplate, rewardsService);
		
		User user = new User(UUID.randomUUID(), "jon", "000", "jon@tourGuide.com");
		Attraction attraction = gpsRestTemplate.getAttractions().get(0);
		user.addToVisitedLocations(new VisitedLocation(user.getUserId(), attraction, new Date()));
		tourGuideService.trackUserLocation(user);
		List<UserReward> userRewards = user.getUserRewards();
		tourGuideService.tracker.stopTracking();
		assertTrue(userRewards.size() == 1);
	}
	
	@Test
	public void isWithinAttractionProximity() {
		GpsRestTemplate gpsRestTemplate = new GpsRestTemplate();
		RewardsService rewardsService = new RewardsService(gpsRestTemplate, new RewardsRestTemplate());
		Attraction attraction = gpsRestTemplate.getAttractions().get(0);
		assertTrue(rewardsService.isWithinAttractionProximity(attraction, attraction));
	}
	
	@Ignore // Needs fixed - can throw ConcurrentModificationException
	@Test
	public void nearAllAttractions() {
		GpsRestTemplate gpsRestTemplate = new GpsRestTemplate();
		RewardsService rewardsService = new RewardsService(gpsRestTemplate, new RewardsRestTemplate());
		rewardsService.setProximityBuffer(Integer.MAX_VALUE);

		InternalTestHelper.setInternalUserNumber(1);
		TourGuideService tourGuideService = new TourGuideService(gpsRestTemplate, rewardsService);
		
		rewardsService.calculateRewards(tourGuideService.getAllUsers().get(0));
		List<UserReward> userRewards = tourGuideService.getUserRewards(tourGuideService.getAllUsers().get(0));
		tourGuideService.tracker.stopTracking();

		assertEquals(gpsRestTemplate.getAttractions().size(), userRewards.size());
	}
	
}
