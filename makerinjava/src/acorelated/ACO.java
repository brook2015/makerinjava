package acorelated;

import graph.WeightedPheromoneEdge;
import graph.WeightedPheromoneDigraph;

/**
 * Created by yaokaibin on 15-11-11.
 */
public class ACO {
    private int generation;
    private int groupCount;
    private int nodeCount;
    private int antCountOfEachGroup;
    private int[] origins;
    private double rho;
    private double bestLength;
    private String bestRoute;
    private AntGroup[] groups;
    private WeightedPheromoneDigraph digraph;
    public ACO(int generation,
               int groupCount,
               int antCountOfEachGroup,
               double rho,
               int[] origins,
               WeightedPheromoneDigraph digraph){
        if (generation<1||groupCount<1||antCountOfEachGroup<1||
                rho<0.0||origins==null||origins.length==0||digraph==null){
            throw new IllegalArgumentException("argument is invalid.");
        }
        this.generation=generation;
        this.groupCount =groupCount;
        this.antCountOfEachGroup = antCountOfEachGroup;
        this.bestLength=Double.MAX_VALUE;
        this.rho=rho;
        this.origins=origins;
        this.digraph=digraph;
        this.nodeCount=digraph.V();
        this.groups=new AntGroup[groupCount];
        for (int i=0;i<groupCount;i++){
            groups[i]=new AntGroup(antCountOfEachGroup,nodeCount,origins);
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
