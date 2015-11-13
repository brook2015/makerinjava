import acorelated.AntGroup;
import edu.princeton.cs.algs4.DijkstraAllPairsSP;
import edu.princeton.cs.algs4.EdgeWeightedDigraph;
import edu.princeton.cs.algs4.In;
import graph.WeightedPheromoneDigraph;
import graph.WeightedPheromoneEdge;


/**
 * Created by yaokaibin on 15-11-10.
 */
public class Program {
    public static void main(String[] args){
        EdgeWeightedDigraph digraph=new EdgeWeightedDigraph(new In(args[0]));
        DijkstraAllPairsSP sp=new DijkstraAllPairsSP(digraph);
        int[] nodes=new int[]{1,3,5};
        WeightedPheromoneDigraph digraphWithPheromone=new WeightedPheromoneDigraph(nodes.length);
        double dist;
        WeightedPheromoneEdge edge;
        for (int orig:nodes){
            for (int dest:nodes){
                dist=sp.dist(orig,dest);
                edge=new WeightedPheromoneEdge(orig,dest,dist,0.0);
                digraphWithPheromone.addEdge(edge);
            }
        }
        System.out.println(digraphWithPheromone.E());
    }
}
