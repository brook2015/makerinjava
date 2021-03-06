package graph;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;

import java.util.*;

/**
 * Created by yaokaibin on 15-11-11.
 */
public class WeightedPheromoneDigraph {
    private static int V;
    private static int E;
    private static Map<Integer,Bag<WeightedPheromoneEdge>> map;
    private static int[] vertex;
    private static int[] terminals;
    private static Random random=new Random();
    private Set<Integer> travel=new HashSet<>();

    public WeightedPheromoneDigraph(int V){
        WeightedPheromoneDigraph.V=V;
        WeightedPheromoneDigraph.E=0;
        WeightedPheromoneDigraph.map=new HashMap<>();
        for (int v:vertex)map.put(v,new Bag<>());
    }

    public static void setVertex(int[] vertex) {
        WeightedPheromoneDigraph.vertex = vertex;
    }

    public static void setTerminals(int[] terminals) {
        WeightedPheromoneDigraph.terminals = terminals;
    }

    public void initiate(int[] origins){
        travel.clear();
        for (int o:origins)travel.add(o);
    }
    public Set<Integer> getTravel(){
        return travel;
    }
    public static int[] getVertex(){
        return vertex;
    }
    public static int[] getOrigin(int k){
        if (k<=0)throw new IllegalArgumentException("invalid value of k");
        int[] origins=new int[k];
        for (int i=0;i<k;i++){
            origins[i]=random.nextInt(terminals.length);
        }
        return origins;
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
            addEdge(new WeightedPheromoneEdge(v,w,weight,0.1));
        }
    }
    public static int V(){
        return V;
    }
    public static int E(){
        return E;
    }
    public static void addEdge(WeightedPheromoneEdge edge) throws IllegalArgumentException{
        if (!map.containsKey(edge.from()))throw new IllegalArgumentException("invalid edge");
        map.get(edge.from()).add(edge);
        E++;
    }
    public Iterable<WeightedPheromoneEdge> allowedEdges(int v){
        Bag<WeightedPheromoneEdge> bag=new Bag<>();
        for (WeightedPheromoneEdge edge:map.get(v)){
            if (!travel.contains(edge.to())){
                bag.add(edge);
            }
        }
        return bag;
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
