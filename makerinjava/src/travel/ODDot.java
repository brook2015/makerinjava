package travel;

import graph.Location;
import graph.LocationType;

/**
 * Created by yaokaibin on 15-11-15.
 */
public class ODDot extends Location {
    public ODDot(double latitude, double longitude) {
        super(latitude, longitude, LocationType.OD);
    }
}
