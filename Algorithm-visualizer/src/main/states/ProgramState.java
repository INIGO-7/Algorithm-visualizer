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
    private Button generateMaze, paintMaze, BFS, DFS, aStar, dijkstra, visualize, clear;
    private BufferedImage title_img, generateMaze_img, paintMaze_img, BFS_img, DFS_img, aStar_img, dijkstra_img, visualize_img, clear_img;
    private int generateMaze_height, paintMaze_height, algorithms_height_row1, algorithms_height_row2, visualize_height, clear_height;

    public ProgramState(Main main) {

        this.main = main;
        this.window = main.getWindow();

        gridWidth = 800;
        gridHeight = 600;
        rowNumber = 24;
        colNumber = 32;

        gridStartX = window.getCanvas().getWidth()/2 - gridWidth/2 - 200;
        gridStartY = window.getCanvas().getHeight()/2 - gridHeight/2;

        maze = new Maze(rowNumber, colNumber, gridStartX, gridStartY);

        for(int i = 1; i <= 6; i++){
            maze.getNode(i, 27).setAsWall();
        }



        //image load

        try {
            title_img = ImageIO.read(new File("resources/programStateTitle.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            generateMaze_img = ImageIO.read(new File("resources/generateMaze.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            paintMaze_img = ImageIO.read(new File("resources/paintMaze.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            BFS_img = ImageIO.read(new File("resources/BFS.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            DFS_img = ImageIO.read(new File("resources/DFS.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            aStar_img = ImageIO.read(new File("resources/aStar.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            dijkstra_img = ImageIO.read(new File("resources/dijkstra.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            visualize_img = ImageIO.read(new File("resources/visualize.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            clear_img = ImageIO.read(new File("resources/clear.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        //origin and destiny points

        Node origin = maze.getNode(2, 25);
        Node destination = maze.getNode(4, 30);

        origin.setColor(new Color(9, 110, 19));
        destination.setColor(new Color(6, 12, 128));

        //button initialization


        //the top is: x -> main.getWindow().getCanvas().getWidth() - 400
        //            y -> 30 + title_img.getHeight() + 30 + 25 (this is: margin + title's height + margin + separating bar's width)


        generateMaze_height = 30 + title_img.getHeight() + 30 + 25 + 30;
        paintMaze_height = generateMaze_height + generateMaze_img.getHeight() + 30;
        algorithms_height_row1 = paintMaze_height + paintMaze_img.getHeight() + 30 + 25 + 30;
        algorithms_height_row2 = algorithms_height_row1 + DFS_img.getHeight() + 30;
        visualize_height = algorithms_height_row2 + BFS_img.getHeight() + 30 + 25 + 30;
        clear_height = visualize_height + visualize_img.getHeight() + 30;


        generateMaze = new Button(main.getWindow().getCanvas().getWidth() - 400 + 400/2 - generateMaze_img.getWidth()/2,
                generateMaze_height, generateMaze_img.getWidth(), generateMaze_img.getHeight(), generateMaze_img);

        paintMaze = new Button(main.getWindow().getCanvas().getWidth() - 400 + 400/2 - paintMaze_img.getWidth()/2,
                paintMaze_height, paintMaze_img.getWidth(), paintMaze_img.getHeight(), paintMaze_img);

        BFS = new Button(main.getWindow().getCanvas().getWidth() - 400 + 400/2 - BFS_img.getWidth() - 30/2,
                algorithms_height_row1, BFS_img.getWidth(), BFS_img.getHeight(), BFS_img);

        DFS = new Button(main.getWindow().getCanvas().getWidth() - 400 + 400/2 + 30/2,
                algorithms_height_row1, DFS_img.getWidth(), DFS_img.getHeight(), DFS_img);

        aStar = new Button(main.getWindow().getCanvas().getWidth() - 400 + 400/2 - BFS_img.getWidth() - 30/2,
                algorithms_height_row2, aStar_img.getWidth(), aStar_img.getHeight(), aStar_img);

        dijkstra = new Button(main.getWindow().getCanvas().getWidth() - 400 + 400/2 + 30/2,
                algorithms_height_row2, dijkstra_img.getWidth(), dijkstra_img.getHeight(), dijkstra_img);

        visualize = new Button(main.getWindow().getCanvas().getWidth() - 400 + 400/2 - visualize_img.getWidth()/2,
                visualize_height, visualize_img.getWidth(), visualize_img.getHeight(), visualize_img);

        clear = new Button(main.getWindow().getCanvas().getWidth() - 400 + 400/2 - clear_img.getWidth()/2,
                clear_height, clear_img.getWidth(), clear_img.getHeight(), clear_img);


        //TODO algorithm initialization ???? should we do it here ????

        bfs = new BFS(maze, origin, destination);

    }

    public void tick(){

        //here we handle the buttons
        //TODO we suspect that when the user clicks a button it registers multiple clicks instead of one

        if(visualize.isClicked(main.getMouse())){
            isVisualizing = true;
            visualizeAlgorithm(bfs);
        }

        if(clear.isClicked(main.getMouse())){
            clearMaze();
        }

    }

    public void render(Graphics g) {

        //interface:


        //draw boundaries

        g.setColor(new Color(159, 182, 178));
        g.fillRect(main.getWindow().getCanvas().getWidth() - 400, 0, 400, main.getWindow().getCanvas().getHeight());
        g.setColor(Color.decode("#64827c"));

        //separating bars ... margins of 30, bar widths of 25

        g.fillRect(main.getWindow().getCanvas().getWidth() - 400, 0, 25, main.getWindow().getCanvas().getHeight());
        g.fillRect(main.getWindow().getCanvas().getWidth() - 400, generateMaze_height - 30 - 25, 400, 25);
        g.fillRect(main.getWindow().getCanvas().getWidth() - 400, algorithms_height_row1 - 30 - 25, 400, 25);
        g.fillRect(main.getWindow().getCanvas().getWidth() - 400, visualize_height - 30 - 25, 400, 25);

        //buttons and labels   ... the buttons are already defined in the constructor
        g.drawImage(title_img, main.getWindow().getCanvas().getWidth() - 350, 30, null);
        g.drawImage(generateMaze_img, generateMaze.getX(), generateMaze.getY(), null);
        g.drawImage(paintMaze_img, paintMaze.getX(), paintMaze.getY(), null);
        g.drawImage(BFS_img, BFS.getX(), BFS.getY(), null);
        g.drawImage(DFS_img, DFS.getX(), DFS.getY(), null);
        g.drawImage(aStar_img, aStar.getX(), aStar.getY(), null);
        g.drawImage(dijkstra_img, dijkstra.getX(), dijkstra.getY(), null);
        g.drawImage(visualize_img, visualize.getX(), visualize.getY(), null);
        g.drawImage(clear_img, clear.getX(), clear.getY(), null);

        //we draw every node:

        for (Node n: maze.getNodes()) {
            n.paint(g);
        }

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

    public void clearMaze(){
        this.maze.clear();
    }

}