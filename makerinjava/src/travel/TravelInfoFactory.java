package travel;

import java.util.Random;
import java.util.Vector;

/**
 * Created by yaokaibin on 15-11-15.
 */
public class TravelInfoFactory extends AbstractTravellerFactory {
    private int minId;
    private int maxId;
    private double minTime=7.0;
    private double maxTime=9.5;
    private static Random random=new Random();

    public TravelInfoFactory(int minId, int maxId) {
        this.minId = minId;
        this.maxId = maxId;
        if (minId<0||maxId<0||minId>=maxId)throw new IllegalArgumentException("invalid argument");
    }

    @Override
    public Iterable<Traveller> createTraveller(int amount) {
        Vector<Traveller> travellers=new Vector<>(amount);
        for (int i=0;i<amount;i++){
            Traveller traveller=createSingleTraveller();
            travellers.add(traveller);
        }
        return travellers;
    }
    public Traveller createSingleTraveller(){
        int origin=random.nextInt(maxId-minId)+minId;
        int destination;
        do {
            destination=random.nextInt(maxId-minId)+minId;
        }while (destination==origin);
        double time=(double)Math.round(random.nextDouble()*(maxTime-minTime)*10)/10.0+minTime;
        return new TravelInfo(origin,destination,time);
    }
}
