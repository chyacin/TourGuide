package dtoModel;

import gpsUtil.location.Attraction;
import gpsUtil.location.VisitedLocation;
import tourGuide.utils.DistanceCalculator;

public class ClosestAttractionDTO implements Comparable<ClosestAttractionDTO>{
    VisitedLocation visitedLocation;
    Attraction attraction;


    public ClosestAttractionDTO(VisitedLocation location, Attraction attraction) {
        this.visitedLocation = location;
        this.attraction = attraction;
    }

    public VisitedLocation getVisitedLocation() {
        return visitedLocation;
    }

    public void setVisitedLocation(VisitedLocation visitedLocation) {
        this.visitedLocation = visitedLocation;
    }

    public Attraction getAttraction() {
        return attraction;
    }

    public void setAttraction(Attraction attraction) {
        this.attraction = attraction;
    }

    public double distanceBetweenUserAndAttractionInMiles(){
        // Get the point location of Attraction
        double latAttraction = attraction.latitude;
        double longAttraction = attraction.longitude;
        // get the point location for User
        double latUser = visitedLocation.location.latitude;
        double longUser = visitedLocation.location.longitude;
        // Get the distance between the two locations
        return DistanceCalculator.distance(latAttraction, longAttraction, latUser, longUser, "M");
    }

    @Override
    public int compareTo(ClosestAttractionDTO other) {
        if (this.distanceBetweenUserAndAttractionInMiles() < other.distanceBetweenUserAndAttractionInMiles()){
            return 1;
        }
        else if (this.distanceBetweenUserAndAttractionInMiles() > other.distanceBetweenUserAndAttractionInMiles()){
            return -1;
        }
        return 0;
    }
}
