package main.algorithms;

import main.utilities.Maze;
import main.utilities.Node;

import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public abstract class PathfindingAlgorithm {

    protected Node origin, destiny;
    protected ArrayList<Node> exploredNodes = new ArrayList<Node>();
    protected Queue toPaint, colors;
    protected Maze maze;

    public PathfindingAlgorithm(Maze maze, Node start, Node end){
        this.origin = start;
        this.destiny = end;
    }

    public void visualizeAlgorithm(){
        visualizeSearch();
        visualizeShortestPath();
    }

    public void visualizeSearch(){

        long past = System.currentTimeMillis();
        double diff = 20;                     //this will be updated 13.3 times per second
        long current;

        while(!(toPaint.isEmpty() && colors.isEmpty())){

            current = System.currentTimeMillis();

            if((current - past) / diff >= 1){
                ((Node) toPaint.poll()).setColor((Color) colors.poll());
                past = current;
            }
        }

    }

    public void visualizeShortestPath(){

        Queue path = new LinkedList<Node>();
        Node n = destiny.getPrevious();


        while(n.getPrevious() != null){
            path.add(n);
            n = n.getPrevious();
        }

        long past = System.currentTimeMillis();
        double diff = 75;                     //this will be updated 13.3 times per second
        long current;

        while(!path.isEmpty()){
            current = System.currentTimeMillis();

            if((current - past) / diff >= 1){
                ((Node) path.poll()).setColor(new Color(212, 212, 0));
                past = current;
            }
        }

    }

    public Node getOrigin(){
        return origin;
    }

    public Node getDestiny(){
        return destiny;
    }

}
