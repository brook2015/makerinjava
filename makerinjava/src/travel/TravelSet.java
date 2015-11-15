package travel;

/**
 * Created by yaokaibin on 15-11-15.
 */
public class TravelSet implements Traveller {
    private int amount;
    private TravelInfo info;

    public TravelSet(int amount,TravelInfo info) {
        this.amount = amount;
        this.info=info;
    }

    public TravelInfo getInfo() {
        return info;
    }

    public int getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return info+" "+amount;
    }
}
