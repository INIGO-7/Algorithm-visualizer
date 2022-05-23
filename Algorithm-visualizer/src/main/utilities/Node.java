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

    //these will be the dimensions and the possible colors of the node for visualizations

    public static final int NODE_SIZE = 25;       //as each node is represented as a square, we set the size of each side
                                                  //to 25 pixels

    //node possible colors
    public static final Color DEFAULT_COLOR = new Color(120, 120, 120);
    public static final Color ORIGIN_COLOR = new Color(9, 110, 19);
    public static final Color DESTINY_COLOR = new Color(6, 12, 128);
    public static final Color WALL_COLOR = Color.BLACK;

    private Color color = DEFAULT_COLOR;

    //this variable tells us if the node has already been examined.
    private boolean visited;

    public Node(int row, int column, int x, int y){

        this.row = row;
        this.column = column;
        this.x = x;
        this.y = y;

        this.visited = false;                  //at the beginning it's impossible that the node has already been visited
        this.isWall = false;                   //or the node being a wall.
        this.previous = null;                  //the node hasn't been visited, so it doesn't have a parent.
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
        g.fillRect(x, y, Node.NODE_SIZE, Node.NODE_SIZE);   //the node is painted in x and y coordinates, with a size of 25 height x 25width
    }

    public void setColor(Color c){
        this.color = c;
    }

    public void setAsWall(){
        color = Node.WALL_COLOR;
        this.isWall = true;
    }

    public void unsetAsWall(){
        color = Node.DEFAULT_COLOR;
        this.isWall = false;
    }

    public void setToDefault(){

        this.visited = false;
        this.isWall = false;
        this.previous = null;
        this.color = Node.DEFAULT_COLOR;

    }

    public boolean isDefault(){
        if(!visited && !isWall && previous == null && color == DEFAULT_COLOR){
            return true;
        } else return false;
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

    public int getX(){ return x; }

    public int getY(){ return y; }

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
