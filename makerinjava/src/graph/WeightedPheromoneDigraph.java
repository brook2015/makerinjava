package graph;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.Vector;

/**
 * Created by yaokaibin on 15-11-11.
 */
public class WeightedPheromoneDigraph {
    private final int V;
    private int E;
    private HashMap<Integer,WeightedPheromoneEdge> edges;
    private Set<Integer> travelled;
    public WeightedPheromoneDigraph(int V){
        this.V=V;
        this.E=0;
        this.edges=new HashMap<>();
        travelled =new HashSet<>();
    }
    public Set<Integer> getTravelled(){
        return travelled;
    }
    public WeightedPheromoneDigraph(In in){
        this(in.readInt());
        int E=in.readInt();
        if (E<0)throw new IllegalArgumentException("Number of edges must be nonnegative");
        for (int i=0;i<E;i++){
            int v=in.readInt();
            int w=in.readInt();
            if (v < 0 || v >= V) throw new IndexOutOfBoundsException("vertex " + v + " is not between 0 and " + (V-1));
            if (w < 0 || w >= V) throw new IndexOutOfBoundsException("vertex " + w + " is not between 0 and " + (V-1));
            double weight=in.readDouble();
            addEdge(new WeightedPheromoneEdge(v,w,weight,0.0));
        }
    }
    public int V(){return V;}
    public int E(){return E;}
    public void addEdge(WeightedPheromoneEdge edge){
        edges.put(edge.from(), edge);
        E++;
    }
    public Iterable<WeightedPheromoneEdge> allowedEdges(int v){
        Vector<WeightedPheromoneEdge> edges=new Vector<>();
        for (WeightedPheromoneEdge edge: this.edges[v]){
            if (!travelled.contains(edge.to())){
                edges.add(edge);
            }
        }
        return edges;
    }
    public Iterable<WeightedPheromoneEdge> adj(int v){
        return edges[v];
    }
    public void addTravelledNode(int node){
        travelled.add(node);
    }
    public Iterable<WeightedPheromoneEdge> edges(){
        Bag<WeightedPheromoneEdge> bag=new Bag<>();
        for (int v=0;v<V;v++){
            for (WeightedPheromoneEdge e: this.edges[v]){
                bag.add(e);
            }
        }
        return bag;
    }
}
