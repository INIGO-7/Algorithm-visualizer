package main.utilities;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.Buffer;

public class Assets {

    //images
    public static BufferedImage title_img, backArrow_img, mainTitle_img, goButton_img, maze1_img, maze2_img,
            generateMaze_img, paintMaze_img, visualize_img, clear_img, clearVis_img;

    public static BufferedImage origin_img1, destiny_img1, walls_img1, BFS_img1,
            DFS_img1, aStar_img1, dijkstra_img1;

    public static BufferedImage origin_img2, destiny_img2, walls_img2, BFS_img2,
            DFS_img2, aStar_img2, dijkstra_img2;

    public Assets(){

        //image load

        //menuState

        try {
            mainTitle_img = ImageIO.read(new File("resources/menuTitle.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            goButton_img = ImageIO.read(new File("resources/goProgramState.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            maze1_img = ImageIO.read(new File("resources/mainMenu1.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            maze2_img = ImageIO.read(new File("resources/mainMenu2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        //programState

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
            backArrow_img = ImageIO.read(new File("resources/backArrow.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            origin_img1 = ImageIO.read(new File("resources/origin1.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            origin_img2 = ImageIO.read(new File("resources/origin2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            destiny_img1 = ImageIO.read(new File("resources/destiny1.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            destiny_img2 = ImageIO.read(new File("resources/destiny2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            walls_img1 = ImageIO.read(new File("resources/walls1.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            walls_img2 = ImageIO.read(new File("resources/walls2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            BFS_img1 = ImageIO.read(new File("resources/BFS1.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            BFS_img2 = ImageIO.read(new File("resources/BFS2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            DFS_img1 = ImageIO.read(new File("resources/DFS1.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            DFS_img2 = ImageIO.read(new File("resources/DFS2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            aStar_img1 = ImageIO.read(new File("resources/aStar1.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            aStar_img2 = ImageIO.read(new File("resources/aStar2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            dijkstra_img1 = ImageIO.read(new File("resources/dijkstra1.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            dijkstra_img2 = ImageIO.read(new File("resources/dijkstra2.png"));
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

        try {
            clearVis_img = ImageIO.read(new File("resources/clearVis.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
