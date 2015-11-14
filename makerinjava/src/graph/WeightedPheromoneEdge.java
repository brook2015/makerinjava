package graph;

/**
 * Created by yaokaibin on 15-11-11.
 */
public class WeightedPheromoneEdge{
    private final int v;
    private final int w;
    private final double distance;
    private double pheromone;
    public WeightedPheromoneEdge(int v,int w,double distance,double pheromone){
        this.v=v;
        this.w=w;
        this.distance=distance;
        this.pheromone=pheromone;
    }
    public int from(){
        return v;
    }
    public int to(){
        return w;
    }
    public double getDistance(){
        return distance;
    }
    public double getPheromone(){
        return pheromone;
    }
    public void setPheromone(double value){
        pheromone=value;
    }
    public String toString(){
        return String.format("%d->%d %.2f %.2f",v,w,distance,pheromone);
    }
}
