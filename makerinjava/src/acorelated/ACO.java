package acorelated;

import edu.princeton.cs.algs4.DijkstraAllPairsSP;
import edu.princeton.cs.algs4.EdgeWeightedDigraph;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Vector;

/**
 * Created by yaokaibin on 15-11-11.
 */
public class ACO {
    private double[][] distance;
    private double[][] pheromone;
    private int generation;
    private int group_count;
    private int node_count;
    private int ant_group;
    private int defaultOrigin;
    private double rho;
    private double bestLength;
    private double defaultPheromone;
    private String bestRoute;
    private AntGroup[] groups;
    public ACO(int _generation,int group,int ant,double _defaultPheromone,double _rho,int origin){
        generation=_generation;
        group_count=group;
        ant_group=ant;
        defaultPheromone=_defaultPheromone;
        bestLength=Double.MAX_VALUE;
        rho=_rho;
        bestRoute="";
        defaultOrigin=origin;
    }
    public void initiate(DijkstraAllPairsSP sp,Vector<Integer> nodes){
        node_count=nodes.size();
        for (int orig:nodes){
            for (int dest:nodes){
                double dist=sp.dist(orig,dest);

            }
        }
        initiatePheromone();
        initiateAntGroup();
    }
    private void initiateDistance(String path) throws IOException{
        distance=new double[node_count][node_count];
        BufferedReader reader=new BufferedReader(new InputStreamReader(new FileInputStream(path)));
        String line;
        int row;
        int column;
        double value;
        while ((line=reader.readLine())!=null){
            String[] split=line.split(" ");
            row=Integer.valueOf(split[0]);
            column=Integer.valueOf(split[1]);
            value=Double.valueOf(split[2]);
            distance[row][column]=value;
        }
    }
    private void initiatePheromone(){
        pheromone=new double[node_count][node_count];
        for (int i=0;i<node_count;i++){
            for (int j=0;j<node_count;j++){
                pheromone[i][j]=defaultPheromone;
            }
        }
    }
    private void initiateAntGroup(){
        groups=new AntGroup[group_count];
        for (int i=0;i<group_count;i++){
            groups[i]=new AntGroup(ant_group,node_count);
            groups[i].initiate(defaultOrigin);
        }
    }
    public void evolve(){
        for (int i=0;i<generation;i++){
            for (int j=0;j<group_count;j++){
                groups[j].iterate(pheromone,distance);
                if (groups[j].getLength(distance)<bestLength){
                    bestRoute=groups[j].getRoute();
                    bestLength=groups[j].getLength(distance);
                }
            }
            pheromoneUpdate();
            for (int j=0;j<group_count;j++){
                groups[j].pheromoneUpdate(pheromone);
                groups[j].initiate(defaultOrigin);
            }
        }
    }
    private void pheromoneUpdate(){
        for (int i=0;i<node_count;i++){
            for (int j=0;j<node_count;j++){
                pheromone[i][j]*=1-rho;
            }
        }
    }
    public String getBestRoute(){
        return bestRoute;
    }
    public double getBestLength(){
        return bestLength;
    }
}
