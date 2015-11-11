import edu.princeton.cs.algs4.DijkstraSP;
import edu.princeton.cs.algs4.DirectedEdge;
import edu.princeton.cs.algs4.EdgeWeightedDigraph;
import edu.princeton.cs.algs4.In;

import java.util.Scanner;

/**
 * Created by yaokaibin on 15-11-10.
 */
public class Program {
    public static void main(String[] args){
        EdgeWeightedDigraph G;
        G=new EdgeWeightedDigraph(new In(args[0]));
        int s=Integer.parseInt(args[1]);
        DijkstraSP dijkstraSP=new DijkstraSP(G,s);
        int v=120;
        if (dijkstraSP.hasPathTo(v)){
            System.out.println(dijkstraSP.distTo(v));
        }
        System.out.println("route:");
        Iterable<DirectedEdge> edges=dijkstraSP.pathTo(v);
        for (DirectedEdge edge:edges){
            System.out.println(edge.toString());
        }
        Scanner scanner=new Scanner("hello");
    }
}
