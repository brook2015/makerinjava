package graph;

/**
 * Created by yaokaibin on 15-11-11.
 */
public class DirectedPheromoneEdge {
    private final int v;
    private final int w;
    private final double weight;
    private double pheromone;
    public DirectedPheromoneEdge(int v,int w,double weight,double pheromone){
        this.v=v;
        this.w=w;
        this.weight=weight;
        this.pheromone=pheromone;
    }
    public int from(){
        return v;
    }
    public int to(){
        return w;
    }
    public double weight(){
        return weight;
    }
    public double getPheromone(){
        return pheromone;
    }
    public void setPheromone(double value){
        pheromone=value;
    }
    public String toString(){
        return String.format("%d->%d %.2f %.2f",v,w,weight,pheromone);
    }
}
