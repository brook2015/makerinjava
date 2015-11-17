package acorelated;

import graph.WeightedPheromoneEdge;
import graph.WeightedPheromoneDigraph;

import java.util.Iterator;
import java.util.Random;
import java.util.Vector;

/**
 * Created by yaokaibin on 15-11-11.
 */
public class Ant {
    private int nodeCount;
    private int currentNode;
    private final double alpha;
    private final double beta;
    private final double q;
    private double routeLength;
    private static Random random=new Random();
    private Vector<WeightedPheromoneEdge> path=new Vector<>();
    public Ant(double q,double alpha,double beta){
        this.q=q;
        this.alpha=alpha;
        this.beta=beta;
    }
    public void initiate(int count,int origin){
        routeLength=0;
        nodeCount=count;
        currentNode=origin;
        path.clear();
    }
    public void moveForward(WeightedPheromoneDigraph digraph){
        if (nodeCount==path.size())return;
        double sum=0.0;
        Iterable<WeightedPheromoneEdge> edges=digraph.allowedEdges(currentNode);
        for (WeightedPheromoneEdge edge:edges){
            sum+=Math.pow(edge.getPheromone(),alpha)*Math.pow(1/edge.getDistance(),beta);
        }
        double ap=0.0;
        double value=random.nextDouble();
        for (WeightedPheromoneEdge edge:edges){
            ap+=Math.pow(edge.getPheromone(),alpha)*Math.pow(1/edge.getDistance(),beta)/sum;
            if (ap<value)continue;
            currentNode=edge.to();
            path.add(edge);
            routeLength+=edge.getDistance();
            digraph.addTravelledNode(currentNode);
            break;
        }
    }
    public String getRoute(){
        String _route="";
        for (WeightedPheromoneEdge p:path){
            _route+=p+";";
        }
        return _route;
    }
    public void pheromoneUpdate(){
        for (WeightedPheromoneEdge edge:path){
            edge.setPheromone(edge.getPheromone()+getDelta());
        }
    }
    private double getDelta(){
        return q/routeLength;
    }
    public double getRouteLength(){
        return routeLength;
    }
}
