package graph;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;

import java.util.*;

/**
 * Created by yaokaibin on 15-11-11.
 */
public class WeightedPheromoneDigraph {
    private final int V;
    private int E;
    private Map<Integer,Bag<WeightedPheromoneEdge>> map;
    private Set<Integer> travel;
    private static int[] vertex;
    private static int[] origin;
    public WeightedPheromoneDigraph(int V){
        this.V=V;
        this.E=0;
        this.map=new HashMap<>();
        this.travel=new HashSet<>();
    }
    public WeightedPheromoneDigraph(int[] _vertex,int[] _origin){
        this(_vertex.length);
        vertex=_vertex;
        origin=_origin;
        for (int v:_vertex){
            map.put(v,new Bag<>());
        }
    }
    public void initiate(){
        travel.clear();
        for (int o:origin){
            travel.add(o);
        }
    }
    public Set<Integer> getTravel(){
        return travel;
    }
    public static int[] getVertex(){
        return vertex;
    }
    public static int[] getOrigin(){
        return origin;
    }
    public boolean isContinue(){
        return travel.size()!=V;
    }
    public WeightedPheromoneDigraph(In in) throws Exception{
        this(in.readInt());
        int E=in.readInt();
        if (E<0)throw new IllegalArgumentException("Number of map must be nonnegative");
        for (int i=0;i<E;i++){
            int v=in.readInt();
            int w=in.readInt();
            if (v < 0 || v >= V) throw new IndexOutOfBoundsException("vertex " + v + " is not between 0 and " + (V-1));
            if (w < 0 || w >= V) throw new IndexOutOfBoundsException("vertex " + w + " is not between 0 and " + (V-1));
            double weight=in.readDouble();
            addEdge(new WeightedPheromoneEdge(v,w,weight,0.0));
        }
    }
    public int V(){
        return V;
    }
    public int E(){
        return E;
    }
    public void addEdge(WeightedPheromoneEdge edge) throws Exception{
        if (!map.containsKey(edge.from()))throw new Exception("key error");
        Bag<WeightedPheromoneEdge> edges=map.get(edge.from());
        edges.add(edge);
        E++;
    }
    public Iterable<WeightedPheromoneEdge> allowedEdges(int v){
        Vector<WeightedPheromoneEdge> edges=new Vector<>();
        for (WeightedPheromoneEdge edge: map.get(v)){
            if (!travel.contains(edge.to())){
                edges.add(edge);
            }
        }
        return edges;
    }
    public Iterable<WeightedPheromoneEdge> adj(int v){
        return map.get(v);
    }
    public void addTravelledNode(int node){
        travel.add(node);
    }
    public Iterable<WeightedPheromoneEdge> edges(){
        Bag<WeightedPheromoneEdge> bag=new Bag<>();
        for (int k:map.keySet()){
            for (WeightedPheromoneEdge e: map.get(k)){
                bag.add(e);
            }
        }
        return bag;
    }
}
