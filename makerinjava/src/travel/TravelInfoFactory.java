package travel;

import java.util.Random;
import java.util.Vector;

/**
 * Created by yaokaibin on 15-11-15.
 */
public class TravelInfoFactory extends AbstractTravellerFactory {
    private static Random random=new Random();

    @Override
    public Iterable<Traveller> createTraveller(int nodeIdLimit, int amount) {
        Vector<Traveller> travellers=new Vector<>(amount);
        for (int i=0;i<amount;i++){
            Traveller traveller=createSingleTraveller(nodeIdLimit);
            travellers.add(traveller);
        }
        return travellers;
    }
    public Traveller createSingleTraveller(int nodeIdLimit){
        TravelInfo info=null;
        int origin=random.nextInt(nodeIdLimit);
        int destination;
        do {
            destination=random.nextInt(nodeIdLimit);
        }while (destination==origin);
        double time=(double)Math.round(random.nextDouble()*130)/10.0+7.0;
        info=new TravelInfo(origin,destination,time);
        return info;
    }
}
