import edu.princeton.cs.algs4.DijkstraSP;
import edu.princeton.cs.algs4.DirectedEdge;
import edu.princeton.cs.algs4.EdgeWeightedDigraph;
import edu.princeton.cs.algs4.In;
import graph.DirectedPheromoneEdge;
import graph.EdgeWeightedPheroDigraph;

import java.util.Scanner;

/**
 * Created by yaokaibin on 15-11-10.
 */
public class Program {
    public static void main(String[] args){
        EdgeWeightedPheroDigraph digraph=new EdgeWeightedPheroDigraph(new In(args[0]));
        Iterable<DirectedPheromoneEdge> edges=digraph.edges();
        for (DirectedPheromoneEdge e:edges){
            System.out.println(e);
        }
    }
}
