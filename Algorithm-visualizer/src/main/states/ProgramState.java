package main.states;

import main.Main;
import main.Window;
import main.algorithms.*;
import main.utilities.Assets;
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
    private int originCount, destinyCount, wallCount;
    private Maze maze;
    private boolean isPaintModeOn, isVisualizing;
    private PathfindingAlgorithm algorithm;
    private Button generateMaze, paintMaze, BFS, DFS, aStar, dijkstra, visualize, clear, clearVis, origin, destiny,
            walls, backArrow;

    private int generateMaze_height, paintMaze_height, algorithms_height_row1, algorithms_height_row2, visualize_height,
            clear_height, origin_height, destiny_height, walls_height, backArrow_height, clearVis_height;

    private ArrayList<Button> pathfindingAlgorithm_buttons;

    //variables for testing

    public ProgramState(Main main) {

        this.main = main;
        this.window = main.getWindow();
        isPaintModeOn = false;

        rowNumber = 25;
        colNumber = 33;
        maze = new Maze(rowNumber, colNumber);

        gridWidth = colNumber * Node.NODE_SIZE;
        gridHeight = rowNumber * Node.NODE_SIZE;

        originCount = 1;
        destinyCount = 1;
        wallCount = (rowNumber - 2) * (colNumber - 2);  //number of nodes that aren't walls at the beggining

        pathfindingAlgorithm_buttons = new ArrayList<>();

        //button initialization

        //the top is: x -> main.getWindow().getCanvas().getWidth() - 400
        //            y -> 30 + title_img.getHeight() + 30 + 25 (this is: margin + title's height + margin + separating bar's width)


        generateMaze_height = 30 + Assets.title_img.getHeight() + 30 + 25 + 50;
        paintMaze_height = generateMaze_height + Assets.generateMaze_img.getHeight() + 30;
        algorithms_height_row1 = paintMaze_height + Assets.paintMaze_img.getHeight() + 30 + 25 + 50;
        algorithms_height_row2 = algorithms_height_row1 + Assets.DFS_img1.getHeight() + 10;
        backArrow_height = generateMaze_height - 20;
        origin_height = generateMaze_height;
        destiny_height = origin_height + Assets.origin_img1.getHeight() + 10;
        walls_height = destiny_height + Assets.origin_img1.getHeight() + 10;
        visualize_height = algorithms_height_row2 + Assets.BFS_img1.getHeight() + 30 + 25 + 10;
        clear_height = visualize_height + Assets.visualize_img.getHeight() + 8;
        clearVis_height = clear_height + Assets.clearVis_img.getHeight() + 8;


        generateMaze = new Button(main.getWindow().getCanvas().getWidth() - 400 + 400/2 - Assets.generateMaze_img.getWidth()/2,
                generateMaze_height, Assets.generateMaze_img.getWidth(), Assets.generateMaze_img.getHeight(), Assets.generateMaze_img, Assets.generateMaze_img);

        paintMaze = new Button(main.getWindow().getCanvas().getWidth() - 400 + 400/2 - Assets.paintMaze_img.getWidth()/2,
                paintMaze_height, Assets.paintMaze_img.getWidth(), Assets.paintMaze_img.getHeight(), Assets.paintMaze_img, Assets.paintMaze_img);

        BFS = new Button(main.getWindow().getCanvas().getWidth() - 400 + 400/2 - Assets.BFS_img1.getWidth() - 30/2,
                algorithms_height_row1, Assets.BFS_img1.getWidth(), Assets.BFS_img1.getHeight(), Assets.BFS_img1, Assets.BFS_img2);

        DFS = new Button(main.getWindow().getCanvas().getWidth() - 400 + 400/2 + 30/2,
                algorithms_height_row1, Assets.DFS_img1.getWidth(), Assets.DFS_img1.getHeight(), Assets.DFS_img1, Assets.DFS_img2);

        aStar = new Button(main.getWindow().getCanvas().getWidth() - 400 + 400/2 - Assets.BFS_img1.getWidth() - 30/2,
                algorithms_height_row2, Assets.aStar_img1.getWidth(), Assets.aStar_img1.getHeight(), Assets.aStar_img1, Assets.aStar_img2);

        dijkstra = new Button(main.getWindow().getCanvas().getWidth() - 400 + 400/2 + 30/2,
                algorithms_height_row2, Assets.dijkstra_img1.getWidth(), Assets.dijkstra_img1.getHeight(), Assets.dijkstra_img1, Assets.dijkstra_img2);

        backArrow = new Button(main.getWindow().getCanvas().getWidth() - 400 + 25 + 10,
                backArrow_height, Assets.backArrow_img.getWidth(), Assets.backArrow_img.getHeight(), Assets.backArrow_img, Assets.backArrow_img);

        origin = new Button(main.getWindow().getCanvas().getWidth() - 400 + 400/2 - Assets.origin_img1.getWidth()/2,
                origin_height, Assets.origin_img1.getWidth(), Assets.origin_img1.getHeight(), Assets.origin_img1, Assets.origin_img2);

        destiny = new Button(main.getWindow().getCanvas().getWidth() - 400 + 400/2 - Assets.destiny_img1.getWidth()/2,
                destiny_height, Assets.destiny_img1.getWidth(), Assets.destiny_img1.getHeight(), Assets.destiny_img1, Assets.destiny_img2);

        walls = new Button(main.getWindow().getCanvas().getWidth() - 400 + 400/2 - Assets.walls_img1.getWidth()/2,
                walls_height, Assets.walls_img1.getWidth(), Assets.walls_img1.getHeight(), Assets.walls_img1, Assets.walls_img2);

        visualize = new Button(main.getWindow().getCanvas().getWidth() - 400 + 400/2 - Assets.visualize_img.getWidth()/2,
                visualize_height, Assets.visualize_img.getWidth(), Assets.visualize_img.getHeight(), Assets.visualize_img, Assets.visualize_img);

        clear = new Button(main.getWindow().getCanvas().getWidth() - 400 + 400/2 - Assets.clear_img.getWidth()/2,
                clear_height, Assets.clear_img.getWidth(), Assets.clear_img.getHeight(), Assets.clear_img, Assets.clear_img);

        clearVis = new Button(main.getWindow().getCanvas().getWidth() - 400 + 400/2 - Assets.clearVis_img.getWidth()/2,
                clearVis_height, Assets.clearVis_img.getWidth(), Assets.clearVis_img.getHeight(), Assets.clearVis_img, Assets.clearVis_img);

        pathfindingAlgorithm_buttons.add(aStar);
        pathfindingAlgorithm_buttons.add(dijkstra);
        pathfindingAlgorithm_buttons.add(DFS);
        pathfindingAlgorithm_buttons.add(BFS);

    }

    public void tick(){

        //here we handle the buttons

        if(!isPaintModeOn){

            if(generateMaze.isClicked(main.getMouse())){

                maze.generate();
                turnOffAlgorithmButtons();
            }

            if(paintMaze.isClicked(main.getMouse())){
                clearVisualization();
                fillPaintModeResources();
                isPaintModeOn = true;
            }

        }else{

            if(backArrow.isClicked(main.getMouse())){
                clearMaze();
                isPaintModeOn = false;
            }

            if(origin.isClicked(main.getMouse())){
               destiny.setOff();
               walls.setOff();
            }

            if(destiny.isClicked(main.getMouse())){
                origin.setOff();
                walls.setOff();
            }

            if(walls.isClicked(main.getMouse())){
                origin.setOff();
                destiny.setOff();
            }

            if(!isVisualizing){

                for(Node n : maze.getNodes()){

                    if(new Rectangle(n.getX(), n.getY(), Node.NODE_SIZE, Node.NODE_SIZE).
                            contains(main.getMouse().getMouseX(), main.getMouse().getMouseY()) && main.getMouse().getLeftClick()){

                        if(origin.isOn() && originCount > 0) {      //as we only have one origin and one destination, we don't have to create the isOrigin()
                            n.setColor(Node.ORIGIN_COLOR);          //or isDestination() boolean methods (as we have more than 1 wall, with them we use the
                            maze.setOrigin(n);                      //isWall() method), we solely check if we have more than 0 origins. if we didn't check this,
                            originCount--;                          //we would have a negative number of origins (or walls or destinations) because it will use
                        }                                           //the method multiple times
                        else if(destiny.isOn() && destinyCount > 0) {
                            n.setColor(Node.DESTINY_COLOR);
                            maze.setDestiny(n);
                            destinyCount--;
                        }
                        else if(walls.isOn() && !n.isWall() && wallCount > 0) {
                            n.setAsWall();
                            wallCount--;
                        }

                    }else if(new Rectangle(n.getX(), n.getY(), Node.NODE_SIZE, Node.NODE_SIZE).
                            contains(main.getMouse().getMouseX(), main.getMouse().getMouseY()) && main.getMouse().getRightClick()){

                        if(n == maze.getOrigin() && n.isDefault()){
                            maze.setOrigin(null);
                            originCount++;
                        }else if(n == maze.getDestiny() && n.isDefault()){
                            maze.setDestiny(null);
                            destinyCount++;
                        }else if(n.isWall()){
                            wallCount++;
                        }
                        n.setToDefault();

                    }
                }
            }

        }

        if(BFS.isClicked(main.getMouse())){
            turnOffOtherButtons(BFS);
            System.out.println(maze.getOrigin().getRow() + ", " + maze.getOrigin().getCol() + " ; " + maze.getDestiny().getRow() + " " + maze.getDestiny().getCol());
            algorithm = new BFS(maze, maze.getOrigin(), maze.getDestiny());
        }


        if(DFS.isClicked(main.getMouse())){
            turnOffOtherButtons(DFS);
           algorithm = new DFS(maze, maze.getOrigin(), maze.getDestiny());
        }

        if(aStar.isClicked(main.getMouse())){
            turnOffOtherButtons(aStar);
            algorithm = new AStar(maze, maze.getOrigin(), maze.getDestiny());
        }

        if(dijkstra.isClicked(main.getMouse())){
            turnOffOtherButtons(dijkstra);
            algorithm = new Dijkstra(maze, maze.getOrigin(), maze.getDestiny());
        }

        if(visualize.isClicked(main.getMouse())){
            if(algorithm != null && maze.getDestiny() != null && maze.getOrigin() != null){
                isVisualizing = true;
                visualizeAlgorithm(algorithm);
            }
            turnOffAlgorithmButtons();
        }

        if(clear.isClicked(main.getMouse())){
            clearMaze();
        }

        if(clearVis.isClicked(main.getMouse())){
            clearVisualization();
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
        g.drawImage(Assets.title_img, main.getWindow().getCanvas().getWidth() - 350, 30, null);

        if(!isPaintModeOn){
            g.drawImage(generateMaze.getButtonImage(), generateMaze.getX(), generateMaze.getY(), null);
            g.drawImage(paintMaze.getButtonImage(), paintMaze.getX(), paintMaze.getY(), null);
        }else{
            g.drawImage(backArrow.getButtonImage(), backArrow.getX(), backArrow.getY(), null);

            g.drawImage(origin.getButtonImage(), origin.getX(), origin.getY(), null);
            g.setColor(Node.ORIGIN_COLOR);
            g.fillRect(origin.getX() + origin.getWidth() + 10,
                    origin.getY() + origin.getHeight()/2 - Node.NODE_SIZE/2,
                    Node.NODE_SIZE, Node.NODE_SIZE);
            g.drawString(String.valueOf(originCount), origin.getX() + origin.getWidth() + 10 + Node.NODE_SIZE + 2,
                    origin.getY() + origin.getHeight() + 2);

            g.drawImage(destiny.getButtonImage(), destiny.getX(), destiny.getY(), null);
            g.setColor(Node.DESTINY_COLOR);
            g.fillRect(destiny.getX() + destiny.getWidth() + 10,
                    destiny.getY() + destiny.getHeight()/2 - Node.NODE_SIZE/2,
                    Node.NODE_SIZE, Node.NODE_SIZE);
            g.drawString(String.valueOf(destinyCount), destiny.getX() + destiny.getWidth() + 10 + Node.NODE_SIZE + 2,
                    destiny.getY() + destiny.getHeight() + 2);

            g.drawImage(walls.getButtonImage(), walls.getX(), walls.getY(), null);
            g.setColor(Node.WALL_COLOR);
            g.fillRect(walls.getX() + walls.getWidth() + 10,
                    walls.getY() + walls.getHeight()/2 - Node.NODE_SIZE/2,
                    Node.NODE_SIZE, Node.NODE_SIZE);
            g.drawString(String.valueOf(wallCount), walls.getX() + walls.getWidth() + 10 + Node.NODE_SIZE + 2,
                    walls.getY() + walls.getHeight() + 2);

        }

        g.drawImage(BFS.getButtonImage(), BFS.getX(), BFS.getY(), null);
        g.drawImage(DFS.getButtonImage(), DFS.getX(), DFS.getY(), null);
        g.drawImage(aStar.getButtonImage(), aStar.getX(), aStar.getY(), null);
        g.drawImage(dijkstra.getButtonImage(), dijkstra.getX(), dijkstra.getY(), null);
        g.drawImage(visualize.getButtonImage(), visualize.getX(), visualize.getY(), null);
        g.drawImage(clear.getButtonImage(), clear.getX(), clear.getY(), null);
        g.drawImage(clearVis.getButtonImage(), clearVis.getX(), clearVis.getY(), null);

        //we draw every node:

        for (Node n: maze.getNodes()) {
            n.paint(g);
        }

        if(isPaintModeOn) drawGrid(g);



    }

    public void fillPaintModeResources(){
        originCount = 1;
        destinyCount = 1;
        wallCount = (rowNumber - 2) * (colNumber - 2);
    }

    public void drawGrid(Graphics g){

        // We draw the grid on top:

        g.setColor(new Color(160, 160, 160));

        int startX = maze.getStartX();
        int startY = maze.getStartY();

        // X axis

        for(int x = startX; x <= startX + gridWidth; x += 25){
            g.drawLine(x, startY, x, startY + gridHeight);
        }

        // Y axis

        for(int y = startY; y <= startY + gridHeight; y+=25){
            g.drawLine(startX, y, startX + gridWidth, y);
        }

    }

    public void visualizeAlgorithm(PathfindingAlgorithm algorithm){

        Thread t = new Thread( new Runnable(){

            public void run(){
                algorithm.visualizeAlgorithm();
                isVisualizing = false;
            }

        }
        );

        t.start();

    }

    public void visualizeSearch(PathfindingAlgorithm algorithm){

        Thread t = new Thread( new Runnable(){

            public void run(){
                algorithm.visualizeSearch();
            }

        }
        );

        t.start();

    }

    public void visualizeSortestPath(PathfindingAlgorithm algorithm){

        Thread t = new Thread( new Runnable(){

            public void run(){
                algorithm.visualizeShortestPath();
            }

        }
        );

        t.start();

    }

    public void clearMaze(){
        turnOffAlgorithmButtons();
        maze.setOrigin(null);
        maze.setDestiny(null);
        algorithm = null;
        fillPaintModeResources();
        this.maze.clear();
    }

    public void clearVisualization(){
        turnOffAlgorithmButtons();
        this.maze.clearVisualization();
    }

    public void turnOffOtherButtons(Button b){
        for(Button button : pathfindingAlgorithm_buttons){
            if(button == b) continue;
            button.setOff();
        }
    }

    public void turnOffAlgorithmButtons(){
        for(Button button : pathfindingAlgorithm_buttons){
            button.setOff();
        }
        algorithm = null;
    }

}