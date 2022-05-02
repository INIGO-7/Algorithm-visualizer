package main.utilities;

import java.awt.*;
import java.util.ArrayList;
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

    public ArrayList<Node> getUnvisitedNeighbours(Maze maze){

        //a node can have neighbours in 4 directions:

        LinkedList<Node> neighbours = new LinkedList<>();
        ArrayList<Node> accessibleNeighbours = new ArrayList<>();

        neighbours.add(maze.getNode(row + 1, column));      //to the right
        neighbours.add(maze.getNode(row - 1, column));      //to the left
        neighbours.add(maze.getNode(row, column + 1));       //downwards
        neighbours.add(maze.getNode(row, column - 1));       //upwards

        for( Node n : neighbours ){
            if(n == null || n.isVisited() || n.isWall) continue;
            accessibleNeighbours.add(n);
        }

        return accessibleNeighbours;
    }

    public void paint(Graphics g){
        g.setColor(color);
        g.fillRect(x, y, size, size);   //the node is painted in x and y coordinates, with a size of 25 height x 25width
    }

    public void setColor(Color c){
        this.color = c;
    }

    public void setAsWall(){
        color = Color.BLACK;
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
    public void setPrevious(Node n){
        this.previous = n;
    }

    public int getRow(){
        return row;
    }

    public int getCol(){
        return column;
    }

    public Node getPrevious(){
        return previous;
    }

    public boolean isWall(){
        return isWall;
    }

    public boolean isVisited(){
        return visited;
    }

}
