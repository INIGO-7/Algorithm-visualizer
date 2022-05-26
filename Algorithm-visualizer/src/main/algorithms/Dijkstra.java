package main.algorithms;
import main.utilities.Maze;
import main.utilities.Node;

import java.awt.*;
import java.util.LinkedList;
import java.util.Queue;

public class Dijkstra extends PathfindingAlgorithm{

    private Node curNode;

    public Dijkstra(Maze maze, Node start, Node end)
    {   super(maze, start, end);

        Queue queue = new LinkedList<Node>();

        // Init all distances with infinity
        for (Node node : maze.getNodes()) node.setRootDistance(999999999);

        // Distance to the root itself is zero
        origin.setRootDistance(0);

        // Init queue with the root node
        queue.add(origin);

        // Iterate over the queue until it is empty.
        while (!queue.isEmpty()) {
            curNode = (Node) queue.remove();  // Fetch next closest node
            curNode.setToVisited();  // Mark as discovered

            if (curNode.equals(destiny)) break;

            if(curNode != origin && curNode != destiny) {
                toPaint.add(curNode);
                colors.add(new Color(138, 240, 137));
            }

            // Iterate over unvisited neighbors
            for (Node neighbor : curNode.getUnvisitedNeighbours(maze))
            {
                if(neighbor != destiny){
                    toPaint.add(neighbor);
                    colors.add(new Color(41, 86, 97));
                }

                // Update minimal distance to neighbor
                // Note: distance between to adjacent node is constant and equal 1 in our grid
                long minDistance = Math.min(neighbor.getRootDistance(), curNode.getRootDistance() + 1);
                if (minDistance != neighbor.getRootDistance()) {
                    neighbor.setRootDistance(minDistance);  // update mininmal distance
                    neighbor.setPrevious(curNode);        // update best parent
                }

                // Add neighbor to the queue for further visiting.
                if (!queue.contains(neighbor)) queue.add(neighbor);
            }
        }

        // Done ! At this point we just have to walk back from the end using the parent
        // If end does not have a parent, it means that it has not been found.
    }

}