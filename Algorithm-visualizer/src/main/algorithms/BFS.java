package main.algorithms;
import main.utilities.Maze;
import main.utilities.Node;

import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BFS {

    //breadth first search algorithm
    //BFS explores equally in all directions.

    //This is an incredibly useful algorithm, not only for regular traversal,
    // but also for procedural map generation, flow field pathfinding, distance maps, and other types of map analysis.

    //This may be the algorithm of choice to identify nearby places of interest in GPS.
    //BFS guarantees the shortest path.

    private Node origin, destiny;
    private ArrayList<Node> exploredNodes = new ArrayList<Node>();
    private Queue toPaint, colors;

    public BFS(Maze maze, Node start, Node end)
    {

        this.origin = start;
        this.destiny = end;
        toPaint = new LinkedList<Node>();
        colors = new LinkedList<Color>();

        //initialise the Queue
        Queue queue = new LinkedList<Node>();

        // Put the start node in the queue and set it to already visited
        queue.add(start);
        start.setToVisited();

        // While there is node to be handled in the queue
        while (!queue.isEmpty())
        {
            // Handle the node in the front of the line
            Node curNode = (Node) queue.poll();

            // Terminate if the goal is reached
            if (curNode == end) break;

            ////  stuff for animations (ignore)  ///////////////////////////////////////////////////////////////////////////
            if(curNode != start){
                toPaint.add(curNode);
                colors.add(new Color(138, 240, 137));
            }
            ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

            // Handle neighbors
            ArrayList<Node> neighbours = curNode.getUnvisitedNeighbours(maze);
            for (int i = 0; i < neighbours.size(); ++i)
            {

                Node neighbour = neighbours.get(i);

                ////  stuff for animations (ignore)  ///////////////////////////////////////////////////////////////////////////
                if(neighbour != end){
                    toPaint.add(neighbour);
                    colors.add(new Color(41, 86, 97));
                }
                ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

                neighbour.setToVisited();
                neighbour.setPrevious(curNode);
                queue.add(neighbour);
            }
        }
        // Done ! At this point we just have to walk back from the end using the parent
        // If end does not have a parent, it means that it has not been found.
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
