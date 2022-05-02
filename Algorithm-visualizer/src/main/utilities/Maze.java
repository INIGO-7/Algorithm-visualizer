package main.utilities;

import java.awt.*;
import java.util.LinkedList;

public class Maze {

    int x, y, rows, cols;
    private LinkedList<Node> nodes = new LinkedList<>();

    public Maze(int rows, int cols, int startX, int startY){

        this.x = startX;
        this.y = startY;
        this.rows = rows;
        this.cols = cols;

        //initialise the grid nodes

        for(int row = 1; row <= rows; row++) {
            x = startX;

            for(int col = 1; col <= cols; col++) {
                Node n = new Node(row, col, x, y);
                nodes.add(n);
                x+=25;
            }

            y+=25;
        }

        //create walls

        for(Node n : nodes){
            if(n.getRow() == 1 || n.getRow() == rows ||
                    n.getCol() == 1 || n.getCol() == cols){
                n.setColor(Color.BLACK);
                n.setAsWall();
            }
        }

    }

    public LinkedList<Node> getNodes(){
        return nodes;
    }

    public Node getNode(int row, int col){
        for( Node n : nodes ){
            if( n.getRow() == row && n.getCol() == col ) return n;
        }
        return null;
    }

}
