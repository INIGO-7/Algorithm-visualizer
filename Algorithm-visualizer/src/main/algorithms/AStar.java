package main.algorithms;

import main.utilities.Maze;
import main.utilities.Node;

import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class AStar extends PathfindingAlgorithm{

    //PSEUDOCODE:
    /*
    OPEN //the set of nodes to be evaluated
    CLOSED //the set of nodes already evaluated
    add the start node to OPEN

    loop
            current = node in OPEN with the lowest f_cost
            remove current from OPEN
            add current to CLOSED

            if current is the target node //path has been found
                return

            foreach neighbour of the current node
                if neighbour is not traversable or neighbour is in CLOSED
                    skip to the next neighbour

                if new path to neighbour is shorter OR neighbour is not in OPEN
                    set f_cost of neighbour
                    set parent of neighbour to current
                    if neighbour is not in OPEN
                        add neighbour to OPEN

    */

    private Node curNode;
    private ArrayList<Node> OPEN = new ArrayList<>();
    private ArrayList<Node> CLOSED = new ArrayList<>();

    public AStar(Maze maze, Node start, Node end)
    {   super(maze, start, end);

        // Distance to the root itself is zero
        origin.setRootDistance(0);
        origin.setDestinyDistance(Math.abs(origin.getRow() - destiny.getRow()) + Math.abs(origin.getCol() - destiny.getCol()));
        origin.setFCost((int) (origin.getRootDistance() + origin.getDestinyDistance()));

        // Init queue with the root node
        OPEN.add(origin);

        // Iterate over the priority queue until it is empty.
        while (!OPEN.isEmpty()) {

            curNode = (Node) getNodeSmallestFCost();  // Fetch next closest node
            OPEN.remove(curNode);
            CLOSED.add(curNode);
            curNode.setToVisited();  // Mark as discovered

            if (curNode.equals(destiny)) break;

            if(curNode != origin && curNode != destiny) {
                toPaint.add(curNode);
                colors.add(new Color(138, 240, 137));
            }

            // Iterate over unvisited neighbors
            for (Node neighbor : curNode.getUnvisitedNeighbours(maze))
            {
                if(CLOSED.contains(neighbor)) continue;

                neighbor.setFCost(distanceToOrigin(neighbor) + distanceToDestiny(neighbor));
                neighbor.setPrevious(curNode);
                OPEN.add(neighbor);

                if(neighbor != destiny){
                    toPaint.add(neighbor);
                    colors.add(new Color(41, 86, 97));
                }
            }
        }

        // Done ! At this point we just have to walk back from the end using the parent
        // If end does not have a parent, it means that it has not been found.
    }

    private Node getNodeSmallestFCost(){

        int lowest_FCost = 999999;
        Node toReturn = new Node(1, 1, 50, 50);
        toReturn.setFCost(lowest_FCost);

        for (Node n : OPEN) {
            if(n.getFCost() < lowest_FCost){
                toReturn = n;
                lowest_FCost = n.getFCost();
            }
        }

        return toReturn;
    }

    private int distanceToOrigin(Node n) {
        //we use manhattan distance as we cannot move diagonally
        //formule: | start.x - end.x | + | start.y - end.y |

        return Math.abs(origin.getCol() - n.getCol()) + Math.abs(origin.getRow() - n.getRow());
    }

    private int distanceToDestiny(Node n) {
        //manhattan distance
        return Math.abs(destiny.getCol() - n.getCol()) + Math.abs(destiny.getRow() - n.getRow());
    }

}
