package main.states;

import main.Main;
import main.Window;
import main.algorithms.BFS;
import main.utilities.Maze;
import main.utilities.Node;

import java.awt.*;

public class ProgramState extends State{

    private Window window;
    private int gridWidth, gridHeight, gridStartX, gridStartY;
    private int rowNumber, colNumber;
    private Maze maze;

    public ProgramState(Main main){

        this.window = main.getWindow();

        gridWidth = 800;
        gridHeight = 600;
        rowNumber = 24;
        colNumber = 32;

        gridStartX = window.getCanvas().getWidth()/2 - gridWidth/2;
        gridStartY = window.getCanvas().getHeight()/2 - gridHeight/2;

        maze = new Maze(rowNumber, colNumber, gridStartX, gridStartY);

        //origin and destiny points

        Node origin = maze.getNode(4, 4);
        Node destination = maze.getNode(4, 30);

        origin.setColor(new Color(156, 0, 0));
        destination.setColor(new Color(12, 12, 110));

        BFS bfs = new BFS(maze, origin, destination);

    }

    public void tick(){}

    public void render(Graphics g) {

        //we draw every node:

        for (Node n: maze.getNodes()) {
            n.paint(g);
        }

        //if(drawingMode == true)
            drawGrid(g);
    }

    public void drawGrid(Graphics g){

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

    }

}