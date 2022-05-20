package main.states;

import main.Main;
import main.Window;
import main.algorithms.BFS;
import main.utilities.Button;
import main.utilities.Maze;
import main.utilities.Node;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class ProgramState extends State{

    private Window window;
    private Main main;
    private int gridWidth, gridHeight, gridStartX, gridStartY;
    private int rowNumber, colNumber;
    private boolean isVisualizing = false;
    private Maze maze;
    private BFS bfs;
    private Button visualizeBFS;
    private BufferedImage goBFS;

    public ProgramState(Main main) {

        this.main = main;
        this.window = main.getWindow();

        gridWidth = 800;
        gridHeight = 600;
        rowNumber = 24;
        colNumber = 32;

        gridStartX = window.getCanvas().getWidth()/2 - gridWidth/2;
        gridStartY = window.getCanvas().getHeight()/2 - gridHeight/2;

        maze = new Maze(rowNumber, colNumber, gridStartX, gridStartY);

        for(int i = 1; i <= 6; i++){
            maze.getNode(i, 27).setAsWall();
        }

        try {
            goBFS = ImageIO.read(new File("resources/goBFS.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        //origin and destiny points

        Node origin = maze.getNode(2, 25);
        Node destination = maze.getNode(4, 30);

        origin.setColor(new Color(9, 110, 19));
        destination.setColor(new Color(6, 12, 128));


        bfs = new BFS(maze, origin, destination);
        visualizeBFS = new Button(gridHeight/2, 700, 150, 40, goBFS);

    }

    public void tick(){

        if(visualizeBFS.isClicked(main.getMouse())){
            isVisualizing = true;
            visualizeAlgorithm(bfs);
        }

    }

    public void render(Graphics g) {

        //we draw every node:

        for (Node n: maze.getNodes()) {
            n.paint(g);
        }

        g.drawImage(visualizeBFS.getButtonImage(), visualizeBFS.getX(), visualizeBFS.getY(), null);

        if(!isVisualizing)
            drawGrid(g);
    }

    public void drawGrid(Graphics g){

        // We draw the grid on top:

        g.setColor(new Color(160, 160, 160));

        // X axis

        for(int x = gridStartX; x <= gridStartX + gridWidth; x += 25){
            g.drawLine(x, gridStartY, x, gridStartY + gridHeight);
        }

        // Y axis

        for(int y = gridStartY; y <= gridStartY + gridHeight; y+=25){
            g.drawLine(gridStartX, y, gridStartX + gridWidth, y);
        }

    }

    public void visualizeAlgorithm(BFS bfs){

        Thread t = new Thread( new Runnable(){

            public void run(){
                bfs.visualizeAlgorithm();
                isVisualizing = false;
            }

        }
        );

        t.start();

    }

    public void visualizeSearch(BFS bfs){

        Thread t = new Thread( new Runnable(){

            public void run(){
                bfs.visualizeSearch();
            }

        }
        );

        t.start();

    }

    public void visualizeSortestPath(BFS bfs){

        Thread t = new Thread( new Runnable(){

            public void run(){
                bfs.visualizeShortestPath();
            }

        }
        );

        t.start();

    }

}