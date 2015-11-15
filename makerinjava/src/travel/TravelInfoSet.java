package travel;

import edu.princeton.cs.algs4.In;

import java.util.Vector;

/**
 * Created by yaokaibin on 15-11-15.
 */
public class TravelInfoSet {
    private Vector<TravelInfo> infoCollection=new Vector<>();
    private int infoCount=0;

    public TravelInfoSet(In in) {
        double origLat,destLat,origLng,destLng,time;
        ODDot orig,dest;
        TravelInfo info;
        if (in.hasNextLine()){
            origLat=in.readDouble();
            origLng=in.readDouble();
            destLat=in.readDouble();
            destLng=in.readDouble();
            time=in.readDouble();
            orig=new ODDot(origLat,origLng);
            dest=new ODDot(destLat,destLng);
            info=new TravelInfo(orig,dest,time);
            addTravelInfo(info);
        }
    }
    public void addTravelInfo(TravelInfo info){
        infoCollection.add(info);
        infoCount++;
    }

    public Iterable<TravelInfo> getInfoCollection() {
        return infoCollection;
    }

    public int getInfoCount() {
        return infoCount;
    }
}
