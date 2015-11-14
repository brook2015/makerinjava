package acorelated;

import graph.WeightedPheromoneDigraph;

import java.util.*;

/**
 * Created by yaokaibin on 15-11-11.
 */
public class AntGroup {
    private int antCount;
    private Ant[] ants;
    private static Random random=new Random();
    private WeightedPheromoneDigraph digraph;
    public AntGroup(int antCount,WeightedPheromoneDigraph digraph){
        this.antCount=antCount;
        this.ants=new Ant[antCount];
        this.digraph=digraph;
    }
    public void initiate(){
        int[] nodes=group(digraph.V() - antCount,antCount);
        int[] origins=WeightedPheromoneDigraph.getOrigin();
        for (int i=0;i<antCount;i++){
            ants[i]=new Ant(1.0,2.0,2.0);
            ants[i].initiate(nodes[i],origins[i]);
        }
    }
    public double getLength(){
        double length=0.0;
        for (Ant ant:ants){
            length+=ant.getRouteLength();
        }
        return length;
    }
    public String getRoute(){
        String route="\n";
        for (int i=0;i<ants.length;i++){
            route+=String.format("route%1$2d: %2$s\n",i+1,ants[i].getRoute());
        }
        return route;
    }
    public void iterate(WeightedPheromoneDigraph digraph){
        while (digraph.isContinue()){
            for (int i=0;i<antCount;i++){
                ants[i].moveForward(digraph);
            }
        }
        digraph.initiate();
    }
    public void pheromoneUpdate(){
        for (Ant ant:ants){
            ant.pheromoneUpdate();
        }
    }
    public static int[] group(int count, int group) throws IllegalArgumentException{
        if (count<=group||count<=0||group<=0){
            throw new IllegalArgumentException("arguments are invalid.");
        }
        int[] nodes=new int[group];
        int g=group,value;
        for (int i=0;i<group-1;i++){
            value=random.nextInt(count-g)+1;
            nodes[i]=value;
            count-=value;
            g-=1;
        }
        nodes[group-1]=count;
        return nodes;
    }
}
