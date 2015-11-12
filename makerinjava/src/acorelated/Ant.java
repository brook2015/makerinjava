package acorelated;

import java.util.Random;
import java.util.Vector;

/**
 * Created by yaokaibin on 15-11-11.
 */
public class Ant {
    private boolean isOver;
    private int node_count;
    private int current_node;
    private double alpha;
    private double beta;
    private double q;
    private double routeLength;
    private static Random random=new Random();
    private Vector<Integer> route=new Vector<>();
    public Ant(double _q,double _alpha,double _beta){
        q=_q;
        alpha=_alpha;
        beta=_beta;
    }
    public void initiate(int count,int origin){
        isOver=false;
        routeLength=Double.MAX_VALUE;
        node_count=count;
        current_node=origin;
        route.clear();
        route.add(origin);
    }
    public void moveForward(double[][] pheromone,double[][] distance,Vector<Integer> allowedNodes){
        if (isOver)return;
        double sum=0.0;
        for (int i:allowedNodes){
            sum+=Math.pow(pheromone[current_node][i],alpha)*Math.pow(1/distance[current_node][i],beta);
        }
        double ap=0.0;
        double value=random.nextDouble();
        for (int i:allowedNodes){
            ap+=Math.pow(pheromone[current_node][i],alpha)*Math.pow(1/distance[current_node][i],beta)/sum;
            if (ap>=value){
                current_node=i;break;
            }
        }
        route.add(current_node);
        allowedNodes.removeElement(current_node);
        isOver=route.size()==node_count+1;
        if (isOver)routeLength=getLength(distance);
    }
    public String getRoute(){
        String _route="";
        for (Integer r:route){
            _route+=r+";";
        }
        return _route;
    }
    public double getLength(double[][] distance){
        double _length=0.0;
        for (int i=0;i<route.size()-1;i++){
            _length+=distance[route.get(i)][route.get(i+1)];
        }
        return _length;
    }
    public void pheromoneUpdate(double[][] pheromone){
        for (int i=0;i<route.size()-1;i++){
            pheromone[route.get(i)][route.get(i+1)]+=getDelta();
        }
    }
    private double getDelta(){
        return q/routeLength;
    }
}
