package travel;

/**
 * Created by yaokaibin on 15-11-15.
 */
public class TravelInfo {
    private ODDot origin;
    private ODDot destination;
    private double arriveTime;

    public TravelInfo(ODDot origin, ODDot destination, double arriveTime) {
        this.origin = origin;
        this.destination = destination;
        this.arriveTime = arriveTime;
    }

    @Override
    public String toString() {
        return "TravelInfo{" +
                "origin=" + origin +
                ", destination=" + destination +
                ", arriveTime=" + arriveTime +
                '}';
    }
}
