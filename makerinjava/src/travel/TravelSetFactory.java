package travel;

import java.util.Random;
import java.util.Vector;

/**
 * Created by yaokaibin on 15-11-15.
 */
public class TravelSetFactory extends AbstractTravellerFactory {
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

    @Override
    public Traveller createSingleTraveller(int nodeIdLimit) {
        AbstractTravellerFactory travellerFactory=new TravelInfoFactory();
        TravelInfo traveller=(TravelInfo)travellerFactory.createSingleTraveller(nodeIdLimit);
        int amount=random.nextInt(5)+1;
        TravelSet travelSet=new TravelSet(amount);
        TravelSet.setInfo(traveller);
        return travelSet;
    }
}
