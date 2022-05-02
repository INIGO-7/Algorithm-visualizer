package main.algorithms;
import main.utilities.Maze;
import main.utilities.Node;

import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BFS {

    //breadh first search algorithm
    //BFS explores equally in all directions.

    //This is an incredibly useful algorithm, not only for regular traversal,
    // but also for procedural map generation, flow field pathfinding, distance maps, and other types of map analysis.

    //This may be the algorithm of choice to identify nearby places of interest in GPS.
    //BFS guarantees the shortest path.

    public BFS(Maze maze, Node start, Node end)
    {
        //initialise the Queue
        Queue queue = new LinkedList<Node>();
        // Put the start node in the queue
        queue.add(start);
        start.setToVisited();
        // While there is node to be handled in the queue
        while (!queue.isEmpty())
        {
            // Handle the node in the front of the line
            Node curNode = (Node) queue.poll();             //returns null if queue is empty
            if(!(curNode == start || curNode == end)) curNode.setColor(new Color(138, 240, 137));
            // Terminate if the goal is reached
            if (curNode == end) break;

            // Handle neighbors
            ArrayList<Node> neighbours = curNode.getUnvisitedNeighbours(maze);
            for (int i = 0; i < neighbours.size(); ++i)
            {
                if(neighbours.get(i) == null) continue;

                neighbours.get(i).setToVisited();
                neighbours.get(i).setParent(curNode);
                queue.add(neighbours.get(i));
            }
        }
        // Done ! At this point we just have to walk back from the end using the parent
        // If end does not have a parent, it means that it has not been found.
    }

}
