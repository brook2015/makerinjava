package acorelated;

import java.util.Random;
import java.util.Vector;

/**
 * Created by yaokaibin on 15-11-11.
 */
public class AntGroup {

    private int ant_count;
    private int node_count;
    private Ant[] ants;
    Vector<Integer> allowedNodes;
    private static Random random=new Random();
    public AntGroup(int ant,int node){
        ant_count=ant;
        node_count=node;
        ants=new Ant[ant_count];
        allowedNodes=new Vector<>(node_count);
    }
    public void initiate(int defaultOrigin){
        for (int i=0;i<node_count;i++){
            allowedNodes.add(i);
        }
        allowedNodes.removeElement(defaultOrigin);
        initiateAnt(defaultOrigin);
    }
    private void initiateAnt(int defaultOrigin){
        Vector<Integer> nodes=getNodes(node_count - 1, ant_count);
        for (int i=0;i<ant_count;i++){
            ants[i]=new Ant(1.0,2.0,2.0);
            ants[i].initiate(nodes.get(i),defaultOrigin);
        }
    }
    public double getLength(double[][] distance){
        double length=0.0;
        for (Ant ant:ants){
            length+=ant.getLength(distance);
        }
        return length;
    }
    public String getRoute(){
        String route="\n";
        for (int i=0;i<ants.length;i++){
            route+=String.format("route%1$2d: %2$s\n",i+1,ants[i].getRoute());
        }
        return route;
    }
    public void iterate(double[][] pheromone,double[][] distance){
        while (allowedNodes.size()!=0){
            for (int i=0;i<ant_count;i++){
                ants[i].moveForward(pheromone,distance,allowedNodes);
            }
        }
    }
    public void pheromoneUpdate(double[][] pheromone){
        for (Ant ant:ants) ant.pheromoneUpdate(pheromone);
    }
    public static Vector<Integer> getNodes(int count,int group){
        Vector<Integer> nodes=new Vector<>(group);
        int _group=group;
        int _count=count;
        for (int i=0;i<group-1;i++){
            int value=random.nextInt(_count-_group)+1;
            nodes.add(value);
            _count-=value;
            _group-=1;
        }
        int sum=0;
        for (int node:nodes)sum+=node;
        nodes.add(count-sum);
        return nodes;
    }
    public static Vector<Integer> getOrigins(int upper,int count){
        Vector<Integer> origins=new Vector<>(count);
        int origin;
        while (origins.size()!=count){
            origin=random.nextInt(upper);
            if (!origins.contains(origin)){
                origins.add(origin);
            }
        }
        return origins;
    }
}
