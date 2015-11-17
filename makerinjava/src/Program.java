import acorelated.ACO;
import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.DijkstraAllPairsSP;
import edu.princeton.cs.algs4.EdgeWeightedDigraph;
import edu.princeton.cs.algs4.In;
import graph.WeightedPheromoneDigraph;
import graph.WeightedPheromoneEdge;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by yaokaibin on 15-11-10.
 */
public class Program {
    public static void main(String[] args){
        try{
            EdgeWeightedDigraph digraph=new EdgeWeightedDigraph(new In(args[0]));
            DijkstraAllPairsSP sp=new DijkstraAllPairsSP(digraph);
            int[] vertex=new int[]{0,1,2,3,4,5,6};
            int[] terminals=new int[]{1,3,5};
            WeightedPheromoneDigraph wpd=new WeightedPheromoneDigraph(vertex,terminals);
            for (int o:vertex){
                for (int d:vertex){
                    double dist=sp.dist(o,d);
                    WeightedPheromoneEdge edge=new WeightedPheromoneEdge(o,d,dist,0.1);
                    wpd.addEdge(edge);
                    System.out.println(edge);
                }
            }
            ACO aco=new ACO(200,20,2,0.1,wpd);
            aco.evolve();
            System.out.println(aco.getBestRoute());
            System.out.println(aco.getBestLength());
            /*WeightedPheromoneDigraph digraphWithPheromone=new WeightedPheromoneDigraph(nodes,origins);
            digraphWithPheromone.initiate();
            double dist;
            WeightedPheromoneEdge edge;
            for (int orig:nodes){
                for (int dest:nodes){
                    dist=sp.dist(orig,dest);
                    edge=new WeightedPheromoneEdge(orig,dest,dist,0.1);
                    digraphWithPheromone.addEdge(edge);
                }
            }
            System.out.println(digraphWithPheromone.E());
            ACO aco=new ACO(200,200,origins.length,0.1,digraphWithPheromone);
            aco.evolve();
            System.out.println("best length: "+aco.getBestLength());
            System.out.println("best route: "+aco.getBestRoute());*/
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
