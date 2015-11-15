package graph;

/**
 * Created by yaokaibin on 15-11-15.
 */
public class Location {
    private final double latitude;
    private final double longitude;
    private final LocationType type;

    public Location(double latitude, double longitude, LocationType type) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.type = type;
    }

    @Override
    public String toString() {
        return "Location{" +
                "latitude=" + latitude +
                ", longitude=" + longitude +
                ", type=" + type +
                '}';
    }
}
