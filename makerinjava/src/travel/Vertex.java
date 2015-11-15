package travel;

import graph.Location;
import graph.LocationType;

/**
 * Created by yaokaibin on 15-11-15.
 */
public class Vertex extends Location{
    private final int index;

    public Vertex(double latitude, double longitude, int index) {
        super(latitude,longitude, LocationType.VERTEX);
        this.index = index;
    }

    public int getIndex() {
        return index;
    }
}
