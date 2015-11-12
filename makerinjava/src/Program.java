import edu.princeton.cs.algs4.*;


/**
 * Created by yaokaibin on 15-11-10.
 */
public class Program {
    public static void main(String[] args){
        EdgeWeightedDigraph digraph=new EdgeWeightedDigraph(new In(args[0]));
        DijkstraAllPairsSP sp=new DijkstraAllPairsSP(digraph);
        int[] nodes=new int[]{10,20,30,40,50};

    }
}
