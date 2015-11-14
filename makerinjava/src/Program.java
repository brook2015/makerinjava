import acorelated.ACO;
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
        try{
            EdgeWeightedDigraph digraph=new EdgeWeightedDigraph(new In(args[0]));
            DijkstraAllPairsSP sp=new DijkstraAllPairsSP(digraph);
            int[] nodes=new int[]{1,3,5,4,7};
            int[] origins=new int[]{1,3};
            WeightedPheromoneDigraph digraphWithPheromone=new WeightedPheromoneDigraph(nodes,origins);
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
            Iterable<WeightedPheromoneEdge> edges=digraphWithPheromone.edges();
            for (WeightedPheromoneEdge e:edges){
                System.out.println(e.toString());
            }
            System.out.println(digraphWithPheromone.E());
            ACO aco=new ACO(5,2,2,0.3,digraphWithPheromone);
            aco.evolve();
            System.out.println("best length: "+aco.getBestLength());
            System.out.println("best route: "+aco.getBestRoute());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
