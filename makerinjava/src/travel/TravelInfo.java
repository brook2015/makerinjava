package travel;

/**
 * Created by yaokaibin on 15-11-15.
 */
public class TravelInfo implements Traveller {
    private int origin;
    private int destination;
    private double arriveTime;

    public TravelInfo(int origin, int destination, double arriveTime) {
        this.origin = origin;
        this.destination = destination;
        this.arriveTime = arriveTime;
    }

    public int getOrigin() {
        return origin;
    }

    public int getDestination() {
        return destination;
    }

    public double getArriveTime() {
        return arriveTime;
    }

    @Override
    public String toString() {
        return String.format("%d->%d %.1f",origin,destination,arriveTime);
    }
}
