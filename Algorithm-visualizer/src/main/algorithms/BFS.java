package main.algorithms;
import main.utilities.Maze;
import main.utilities.Node;

import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BFS extends PathfindingAlgorithm{

    //breadth first search algorithm
    //BFS explores equally in all directions.

    //This is an incredibly useful algorithm, not only for regular traversal,
    // but also for procedural map generation, flow field pathfinding, distance maps, and other types of map analysis.

    //This may be the algorithm of choice to identify nearby places of interest in GPS.
    //BFS guarantees the shortest path.

    public BFS(Maze maze, Node start, Node end)
    {   super(maze, start, end);

        //initialise the Queue
        Queue queue = new LinkedList<Node>();

        // Put the start node in the queue and set it to already visited
        queue.add(origin);
        origin.setToVisited();

        // While there is node to be handled in the queue
        while (!queue.isEmpty())
        {
            // Handle the node in the front of the line
            Node curNode = (Node) queue.poll();
            // Terminate if the goal is reached

            if (curNode.equals(destiny)) break;

            ////  stuff for animations (ignore)  ///////////////////////////////////////////////////////////////////////////
            if(curNode != origin){
                toPaint.add(curNode);
                colors.add(new Color(138, 240, 137));
            }
            ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

            // Handle neighbors
            ArrayList<Node> neighbours = curNode.getUnvisitedNeighbours(this.maze);
            for (Node neighbour : neighbours)
            {

                ////  stuff for animations (ignore)  ///////////////////////////////////////////////////////////////////////////
                if(neighbour != destiny){
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

}
