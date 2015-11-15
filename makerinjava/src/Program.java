import edu.princeton.cs.algs4.DijkstraAllPairsSP;
import edu.princeton.cs.algs4.EdgeWeightedDigraph;
import edu.princeton.cs.algs4.In;


/**
 * Created by yaokaibin on 15-11-10.
 */
public class Program {
    public static void main(String[] args){
        try{
            EdgeWeightedDigraph digraph=new EdgeWeightedDigraph(new In(args[0]));
            DijkstraAllPairsSP sp=new DijkstraAllPairsSP(digraph);

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
