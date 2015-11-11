package graph;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;

import java.util.Iterator;
import java.util.Set;

/**
 * Created by yaokaibin on 15-11-11.
 */
public class EdgeWeightedPheroDigraph {
    private final int V;
    private int E;
    private Bag<DirectedPheromoneEdge>[] adj;
    private Set<Integer> allowedNodes;
    public EdgeWeightedPheroDigraph(int V){
        this.V=V;
        this.E=0;
        adj=(Bag<DirectedPheromoneEdge>[])new Bag[V];
        for (int v=0;v<V;v++){
            adj[v]=new Bag<>();
        }
    }
    public EdgeWeightedPheroDigraph(In in){
        this(in.readInt());
        int E=in.readInt();
        if (E<0)throw new IllegalArgumentException("Number of edges must be nonnegative");
        for (int i=0;i<E;i++){
            int v=in.readInt();
            int w=in.readInt();
            if (v < 0 || v >= V) throw new IndexOutOfBoundsException("vertex " + v + " is not between 0 and " + (V-1));
            if (w < 0 || w >= V) throw new IndexOutOfBoundsException("vertex " + w + " is not between 0 and " + (V-1));
            double weight=in.readDouble();
            //double pheromone=in.readDouble();
            //addEdge(new DirectedPheromoneEdge(v,w,weight,pheromone));
            addEdge(new DirectedPheromoneEdge(v,w,weight,0.0));
            allowedNodes.add(v);
        }
    }
    public int V(){return V;}
    public int E(){return E;}
    public void addEdge(DirectedPheromoneEdge e){
        adj[e.from()].add(e);
    }
    public Iterable<DirectedPheromoneEdge> adj(int v){
        return adj[v];
    }
    public Iterable<DirectedPheromoneEdge> edges(){
        Bag<DirectedPheromoneEdge> bag=new Bag<>();
        for (int v=0;v<V;v++){
            for (DirectedPheromoneEdge e:adj[v]){
                bag.add(e);
            }
        }
        return bag;
    }
    public Set<Integer> getAllowedNodes(){
        return allowedNodes;
    }
}
