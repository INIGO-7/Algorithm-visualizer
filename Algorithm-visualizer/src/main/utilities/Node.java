package main.utilities;

import java.awt.*;
import java.util.LinkedList;

public class Node {

    //this is the node that discovered this one as its neighbour
    private Node previous;

    //tells us if this is a wall or not
    private boolean isWall;

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
                                               //to 25 pixels.

        this.visited = false;                  //at the beginning it's impossible that the node has already been visited
        this.isWall = false;                   //or the node being a wall.
        this.previous = null;                  //the node hasn't been visited, so it doesn't have a parent.

        this.color = new Color(80, 80, 80);     //default color.
    }

    public Node[] getUnvisitedNeighbours(){


        /*
        if(!(neighbours[i].isVisited() && neighbours[i].isWall())){

        }

        return neighbours;*/
        return new Node[3];
    }

    public void setParent(Node n){
        this.previous = n;
    }

    public void paint(Graphics g){
        g.setColor(color);
        g.fillRect(x, y, size, size);   //the node is painted in x and y coordinates, with a size of 25 height x 25width
    }

    public void setColor(Color c){
        this.color = c;
    }

    public void setAsWall(){
        this.isWall = true;
    }

    public void unsetAsWall(){
        this.isWall = false;
    }

    public void setToVisited(){
        this.visited = true;
    }

    public void setToUnvisited(){
        this.visited = false;
    }

    public int getRow(){
        return row;
    }

    public int getCol(){
        return column;
    }

    public boolean isWall(){
        return isWall;
    }

    public boolean isVisited(){
        return visited;
    }

}
