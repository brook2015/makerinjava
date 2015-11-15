package travel;

/**
 * Created by yaokaibin on 15-11-15.
 */
public class TravelSet implements Traveller {
    private int amount;
    private static TravelInfo info;

    public TravelSet(int amount) {
        this.amount = amount;
    }

    public static TravelInfo getInfo() {
        return info;
    }

    public static void setInfo(TravelInfo info) {
        TravelSet.info = info;
    }

    public int getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return String.format(info+" amount: "+amount);
    }
}
