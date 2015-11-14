package acorelated;

import graph.WeightedPheromoneEdge;
import graph.WeightedPheromoneDigraph;

/**
 * Created by yaokaibin on 15-11-11.
 */
public class ACO {
    private int generation;
    private int groupCount;
    private int k;
    private double rho;
    private double bestLength;
    private String bestRoute;
    private AntGroup[] groups;
    private WeightedPheromoneDigraph digraph;
    public ACO(int generation,int groupCount,int k,double rho,WeightedPheromoneDigraph digraph){
        this.generation=generation;
        this.groupCount =groupCount;
        this.k = k;
        this.bestLength=Double.MAX_VALUE;
        this.rho=rho;
        this.digraph=digraph;
        this.groups=new AntGroup[groupCount];
        for (int i=0;i<groupCount;i++){
            groups[i]=new AntGroup(k,digraph);
            groups[i].initiate();
        }
    }
    public void evolve(){
        for (int i=0;i<generation;i++){
            for (int j=0;j<groupCount;j++){
                groups[j].iterate(digraph);
                double length=groups[j].getLength();
                if (length<bestLength){
                    bestRoute=groups[j].getRoute();
                    bestLength=length;
                }
            }
            pheromoneUpdate();
            for (int j=0;j<groupCount;j++){
                groups[j].pheromoneUpdate();
                groups[j].initiate();
            }

        }
    }
    private void pheromoneUpdate(){
        Iterable<WeightedPheromoneEdge> edges=digraph.edges();
        for (WeightedPheromoneEdge e:edges){
            e.setPheromone(e.getPheromone()*(1-rho));
        }
    }
    public String getBestRoute(){
        return bestRoute;
    }
    public double getBestLength(){
        return bestLength;
    }
}
