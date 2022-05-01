package main.algorithms;
import java.util.LinkedList;
import java.util.Queue;

public class BFS {

    //breadh first search algorithm
    //BFS explores equally in all directions.

    //This is an incredibly useful algorithm, not only for regular traversal,
    // but also for procedural map generation, flow field pathfinding, distance maps, and other types of map analysis.

    //This may be the algorithm of choice to identify nearby places of interest in GPS.
    //BFS guarantees the shortest path.

    /*
    BFS(LinkedList<Node> maze, Node start, Node end)
    {
        //initialise the Queue
        Queue queue = new LinkedList<Node>();
        // Put the start node in the queue
        queue.add(start);
        start.visited = true;
        // While there is node to be handled in the queue
        while (!queue.isEmpty())
        {
            // Handle the node in the front of the line
            Node curNode = queue.element();
            // Terminate if the goal is reached
            if (curNode == end)
                break;
            // Handle neighbors
            auto neighbors = curNode.GetUnvisitedNeighbors();
            for (auto i = 0; i < neighbors.size(); ++i)
            {
                neighbors[i].visite = true;
                neighbors[i].parent = curNode;
                queue.push(neighbors[i]);
            }
        }
        // Done ! At this point we just have to walk back from the end using the parent
        // If end does not have a parent, it means that it has not been found.
    }

    BFS(LinkedList<Node> maze, Node start, Node end)
    {

    }
    */
}
