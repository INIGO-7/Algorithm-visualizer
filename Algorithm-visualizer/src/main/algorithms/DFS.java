package main.algorithms;

import main.utilities.Maze;
import main.utilities.Node;

import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;

public class DFS extends PathfindingAlgorithm{

    //same as BFS but with a Stack

    public DFS(Maze maze, Node start, Node end) {
        super(maze, start, end);

        toPaint = new LinkedList<Node>();
        colors = new LinkedList<Color>();

        Stack pila = new Stack();
        start.setToVisited();
        pila.push(start);

        while (!pila.empty()) {
            Node curNode = (Node) pila.pop();
            if (curNode == end) {
                break;
            }

            if (curNode != start) {
                toPaint.add(curNode);
                colors.add(new Color(138, 240, 137));
            }

            ArrayList<Node> neighbours = curNode.getUnvisitedNeighbours(maze);

            for (Node neighbour : neighbours) {
                neighbour.setToVisited();
                neighbour.setPrevious(curNode);
                pila.push(neighbour);

                if (neighbour != end) {
                    toPaint.add(neighbour);
                    colors.add(new Color(41, 86, 97));
                }

            }
        }
    }
}
