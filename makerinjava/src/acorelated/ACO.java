package acorelated;

import graph.DirectedPheromoneEdge;
import graph.EdgeWeightedPheroDigraph;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Vector;

/**
 * Created by yaokaibin on 15-11-11.
 */
public class ACO {
    private int generation;
    private int group_count;
    //private int node_count;
    private int ant_group;
    private int defaultOrigin;
    private double rho;
    private double bestLength;
    private String bestRoute;
    private AntGroup[] groups;
    public static EdgeWeightedPheroDigraph digraph;
    public static Vector<Integer> destinations;
    public ACO(int _generation,int group,int ant,double _rho,int origin,EdgeWeightedPheroDigraph _digraph){
        generation=_generation;
        group_count=group;
        ant_group=ant;
        bestLength=Double.MAX_VALUE;
        rho=_rho;
        defaultOrigin=origin;
        digraph=_digraph;
    }
    public void initiate(String path){
        initiateAntGroup();
    }
    private void initiateAntGroup(){
        groups=new AntGroup[group_count];
        for (int i=0;i<group_count;i++){
            groups[i]=new AntGroup(ant_group,digraph.V());
            groups[i].initiate(defaultOrigin);
        }
    }
    public void evolve(){
        for (int i=0;i<generation;i++){
            for (int j=0;j<group_count;j++){
                groups[j].iterate(digraph);
                if (groups[j].getLength()<bestLength){
                    bestRoute=groups[j].getRoute();
                    bestLength=groups[j].getLength();
                }
            }
            pheromoneUpdate();
            for (int j=0;j<group_count;j++){
                groups[j].pheromoneUpdate(digraph);
                groups[j].initiate(defaultOrigin);
            }
        }
    }
    private void pheromoneUpdate(){
        Iterable<DirectedPheromoneEdge> edges=digraph.edges();
        for (DirectedPheromoneEdge edge:edges){
            edge.setPheromone(edge.getPheromone()*(1-rho));
        }
    }
    public String getBestRoute(){
        return bestRoute;
    }
    public double getBestLength(){
        return bestLength;
    }
}
