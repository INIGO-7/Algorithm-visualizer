package main.utilities;

import java.awt.*;
import java.util.LinkedList;

public class Node {

    //this is the node that discovered this one as its neighbour
    private Node previous;

    //row and column of the Node relative to the grid
    private int row, column;

    //these are the real coordinates relative to the screen
    private int x, y;

    //this will be the color and dimensions of the node for visualizations
    private Color color;
    private int size;

    //this variable tells us if the node has already been examined.
    private boolean visited;

    public Node(int row, int column, int x, int y){

        this.row = row;
        this.column = column;
        this.x = x;
        this.y = y;
        this.size = 25;                        //as each node is represented as a square, we set the size of each side
                                               //to 25 pixels

        this.visited = false;                  //at the beginning it's impossible that the node has already been visited
        this.previous = null;                    //the node hasn't been visited, so it doesn't have a parent
        this.color = new Color(80, 80, 80);     //default color
    }

    public LinkedList<Node> getUnvisitedNeighbours(){
        //handle wall nodes

        LinkedList<Node> neighbours = new LinkedList<>();

        return neighbours;
    }

    public void setParent(Node n){
        this.previous = n;
    }

    public void Paint(Graphics g){
        g.setColor(color);
        g.fillRect(x, y, size, size);   //the node is painted in x and y coordinates, with a size of 25 height x 25width
    }
}
