package graph;

import edu.princeton.cs.algs4.DirectedEdge;

/**
 * Created by yaokaibin on 15-11-11.
 */
public class WeightedPheromoneEdge extends DirectedEdge{
    private double pheromone;
    public WeightedPheromoneEdge(int v, int w, double weight, double pheromone){
        super(v,w,weight);
        this.pheromone=pheromone;
    }
    public double getPheromone(){
        return pheromone;
    }
    public void setPheromone(double value){
        pheromone=value;
    }
}