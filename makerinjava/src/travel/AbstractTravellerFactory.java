package travel;

/**
 * Created by yaokaibin on 15-11-15.
 */
public abstract class AbstractTravellerFactory {
    public abstract Iterable<Traveller> createTraveller(int amount);
    public abstract Traveller createSingleTraveller();
}
