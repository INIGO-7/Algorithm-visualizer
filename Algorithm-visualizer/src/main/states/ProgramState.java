package main.states;

import main.Main;
import main.Window;
import main.utilities.Node;

import java.awt.*;
import java.util.LinkedList;

public class ProgramState extends State{

    private Window window;
    private int gridWidth, gridHeight, gridStartX, gridStartY;
    private int rowNumber, colNumber;
    private LinkedList<Node> nodes = new LinkedList<>();

    public ProgramState(Main main){

        this.window = main.getWindow();

        gridWidth = 800;
        gridHeight = 600;
        rowNumber = 24;
        colNumber = 32;

        gridStartX = window.getCanvas().getWidth()/2 - gridWidth/2;
        gridStartY = window.getCanvas().getHeight()/2 - gridHeight/2;

        int x = gridStartX;
        int y = gridStartY;

        //initialise the grid nodes

        for(int row = 1; row <= rowNumber; row++) {
            x = gridStartX;

            for(int col = 1; col <= colNumber; col++) {
                Node n = new Node(row, col, x, y);
                nodes.add(n);
                x+=25;
            }

            y+=25;
        }

        //create walls

        for(Node n : nodes){
            if(n.getRow() == 1 || n.getRow() == rowNumber ||
                n.getCol() == 1 || n.getCol() == colNumber){
                n.setColor(Color.BLACK);
            }
        }
    }

    public void tick(){}

    public void render(Graphics g) {

        //we draw every node:

        for (Node n: nodes) {
            n.paint(g);
        }

        //if(drawingMode == true){
            // We draw the grid on top:

            g.setColor(new Color(150, 150, 150));

            // X axis

            for(int x = gridStartX; x <= gridStartX + gridWidth; x += 25){
                g.drawLine(x, gridStartY, x, gridStartY + gridHeight);
            }

            // Y axis

            for(int y = gridStartY; y <= gridStartY + gridHeight; y+=25){
                g.drawLine(gridStartX, y, gridStartX + gridWidth, y);
            }

            //origin and destiny points

            g.setColor(new Color(156, 0, 0));
            g.fillRect(gridStartX + 101, gridStartY + 101, 24, 24);
            g.setColor(new Color(12, 12, 110));
            g.fillRect(gridStartX + 701, gridStartY + 501, 24, 24);

        //}
    }

}