package travel;

import java.util.Random;
import java.util.Vector;

/**
 * Created by yaokaibin on 15-11-15.
 */
public class TravelSetFactory extends AbstractTravellerFactory {
    private int minId;
    private int maxId;
    private int minAmount;
    private int maxAmount;
    private static Random random=new Random();

    public TravelSetFactory(int minId, int maxId, int minAmount, int maxAmount) {
        this.minId = minId;
        this.maxId = maxId;
        this.minAmount = minAmount;
        this.maxAmount = maxAmount;
        if (minId<0||maxId<0||minId>=maxId)throw new IllegalArgumentException("invalid argument");
        if (minAmount<0||maxAmount<0||minAmount>=maxAmount)throw new IllegalArgumentException("invalid argument");
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

    @Override
    public Traveller createSingleTraveller() {
        AbstractTravellerFactory travellerFactory=new TravelInfoFactory(minId,maxId);
        TravelInfo traveller=(TravelInfo)travellerFactory.createSingleTraveller();
        int amount=random.nextInt(maxAmount-minAmount)+minAmount;
        return new TravelSet(amount,traveller);
    }
}
