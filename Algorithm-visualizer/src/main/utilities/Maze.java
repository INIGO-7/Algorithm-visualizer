package main.utilities;

import java.awt.*;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Maze {

    private int x, y, rows, cols;
    private int startX = 83, startY = 84;
    private Node origin, destiny;
    private LinkedList<Node> nodes = new LinkedList<>();

    public Maze(int rows, int cols){

        this.x = 83;
        this.y = 84;
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

        origin = getNode(ThreadLocalRandom.current().nextInt(2, rows-1),
                ThreadLocalRandom.current().nextInt(2, cols-1));

        destiny = getNode(ThreadLocalRandom.current().nextInt(2, rows-1),
                ThreadLocalRandom.current().nextInt(2, cols-1));

        origin.setColor(Node.ORIGIN_COLOR);
        destiny.setColor(Node.DESTINY_COLOR);

        //create walls

        setWalls();

    }

    public void generate(){

        clear();        //we clear the grid first

        //then we have to set the maze to a checkers-like board

        for(int i = 2; i < rows; i++) {
            if(i % 2 == 0){
                for (int j = 3; j < cols; j += 2) {
                    getNode(i, j).setAsWall();
                }
            }else{
                for(int j = 2; j < cols; j++){
                    getNode(i, j).setAsWall();
                }
            }
        }

        /*      ITERATIVE DFS-LIKE MAZE GENERATOR ALGORITHM

          1. Choose the initial node, mark it as visited and push it to the stack
          2. While the stack is not empty

            1. Pop a node from the stack and make it a current node
            2. If the current node has any neighbours which have not been visited

                1. Push the current node to the stack
                2. Choose one of the unvisited neighbours
                3. Remove the wall between the current node and the chosen node
                4. Mark the chosen node as visited and push it to the stack
        */

        ArrayList<Node> originOrDestiny = new ArrayList<>();
        Stack<Node> stack = new Stack<>();
        ArrayList<Node> neighbours = new ArrayList<>();
        Node current, chosen;

        Node first = getNode(2, 2);
        first.setToVisited();
        stack.push(first);

        while(!stack.isEmpty()){
            current = stack.pop();
            originOrDestiny.add(current);
            neighbours = getUnvisitedNeighbours_maze(current);

            if(!neighbours.isEmpty()){
                stack.push(current);

                Collections.shuffle(neighbours);
                chosen = neighbours.get(0);

                if(chosen.getCol() > current.getCol()){
                    getNode(current.getRow(), current.getCol()+1).unsetAsWall();

                }else if(chosen.getCol() < current.getCol()){
                    getNode(current.getRow(), current.getCol()-1).unsetAsWall();

                }else if(chosen.getRow() < current.getRow()){
                    getNode(current.getRow()-1, current.getCol()).unsetAsWall();

                }else{
                    getNode(current.getRow()+1, current.getCol()).unsetAsWall();
                }

                chosen.setToVisited();
                stack.push(chosen);
            }

        }

        //reset every "visited" attribute in the nodes

        for(Node n : nodes){
            n.setToUnvisited();
        }

        //have to stablish origin and destiny on nodes that aren't walls & we have explored.

        origin = originOrDestiny.get(ThreadLocalRandom.current().nextInt(0, originOrDestiny.size()));
        originOrDestiny.remove(origin);
        destiny = originOrDestiny.get(ThreadLocalRandom.current().nextInt(0, originOrDestiny.size()));

        origin.setColor(Node.ORIGIN_COLOR);
        destiny.setColor(Node.DESTINY_COLOR);

        setWalls();

    }

    public LinkedList<Node> getNodes(){
        return nodes;
    }

    public ArrayList<Node> getUnvisitedNeighbours_maze(Node n){
        //a node can have neighbours in 4 directions:

        LinkedList<Node> neighbours = new LinkedList<>();
        ArrayList<Node> accessibleNeighbours = new ArrayList<>();

        if(n.getRow() + 2 < cols) neighbours.add(getNode(n.getRow() + 2, n.getCol()));      //to the right
        if(n.getRow() - 2 > 1) neighbours.add(getNode(n.getRow() - 2, n.getCol()));      //to the left
        if(n.getRow() + 2 < rows) neighbours.add(getNode(n.getRow(), n.getCol() + 2));       //downwards
        if(n.getRow() - 2 > 1) neighbours.add(getNode(n.getRow(), n.getCol() - 2));       //upwards

        for( Node node : neighbours ){
            if(node == null || node.isVisited() || node.isWall()) continue;
            accessibleNeighbours.add(node);
        }

        return accessibleNeighbours;
    }

    public Node getNode(int row, int col){
        for( Node n : nodes ){
            if( n.getRow() == row && n.getCol() == col ) return n;
        }
        return null;
    }

    public int getRow(Node n){
        return n.getRow();
    }

    public int getCol(Node n){
        return n.getCol();
    }

    public void clear(){

        for(Node n : nodes){
            if(!(n.getRow() == 1 || n.getRow() == rows ||
                    n.getCol() == 1 || n.getCol() == cols)){
                n.setToDefault();
            }
        }

    }

    public void clearVisualization(){

        for(Node n : nodes){
            if(!n.isWall() && n.getColor() != Node.ORIGIN_COLOR && n.getColor() != Node.DESTINY_COLOR)
                n.setToDefault();
        }

    }

    public void setWalls(){
        for(Node n : nodes){
            if(n.getRow() == 1 || n.getRow() == rows ||
                    n.getCol() == 1 || n.getCol() == cols){
                n.setColor(Color.BLACK);
                n.setAsWall();
            }
        }
    }

    public Node getOrigin(){
        return origin;
    }

    public Node getDestiny(){
        return destiny;
    }

    public void setOrigin(Node origin){
        this.origin = origin;
    }

    public void setDestiny(Node destiny){
        this.destiny = destiny;
    }

    public int getStartX(){
        return startX;
    }

    public int getStartY(){
        return startY;
    }

}
