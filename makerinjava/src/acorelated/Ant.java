package acorelated;

import graph.DirectedPheromoneEdge;
import graph.EdgeWeightedPheroDigraph;

import java.util.Iterator;
import java.util.Random;
import java.util.Vector;
/**
 * Created by yaokaibin on 15-11-11.
 */
public class Ant {
    private boolean isOver;
    private int current_node;
    private int complete;
    private double alpha;
    private double beta;
    private double q;
    private double routeLength;
    private static Random random=new Random();
    private Vector<Integer> route=new Vector<>();
    public Ant(double _q,double _alpha,double _beta){
        q=_q;
        alpha=_alpha;
        beta=_beta;
    }
    public void initiate(int count,int origin){
        isOver=false;
        routeLength=Double.MAX_VALUE;
        current_node=origin;
        route.clear();
        route.add(origin);
    }
    public void moveForward(EdgeWeightedPheroDigraph digraph){
        if (isOver)return;
        double sum=0.0;
        for (DirectedPheromoneEdge edge:digraph.adj(current_node)){
            int target=edge.to();
            if (route.contains(target))continue;
            sum+=Math.pow(edge.getPheromone(),alpha)*Math.pow(1/edge.weight(),beta);
        }
        double ap=0.0;
        double value=random.nextDouble();
        for (DirectedPheromoneEdge edge:digraph.adj(current_node)){
            int target=edge.to();
            if (route.contains(target))continue;
            ap+=Math.pow(edge.getPheromone(),alpha)*Math.pow(1/edge.weight(),beta)/sum;
            if (ap>=value){
                current_node=target;
                if (ACO.destinations.contains(current_node))complete+=1;
                routeLength+=edge.weight();
                break;
            }
        }
        route.add(current_node);
        isOver=complete==ACO.destinations.size();
    }
    public String getRoute(){
        String _route="";
        for (Integer r:route){
            _route+=r+";";
        }
        return _route;
    }
    public void pheromoneUpdate(EdgeWeightedPheroDigraph digraph){
        Iterator<Integer> iterator=route.iterator();
        int current=iterator.next();
        while (iterator.hasNext()){
            int next=iterator.next();
            Iterable<DirectedPheromoneEdge> edges=digraph.adj(current);
            for (DirectedPheromoneEdge edge:edges){
                if (next==edge.to()){
                    edge.setPheromone(edge.getPheromone()+getDelta());
                }
            }
            current=next;
        }
    }
    private double getDelta(){
        return q/routeLength;
    }
    public double getRouteLength(){
        return routeLength;
    }
}
