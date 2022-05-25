package main.states;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import main.Main;
import main.utilities.Assets;
import main.utilities.Button;

public class MenuState extends State{

    private final BufferedImage goButton_img = Assets.goButton_img, mainTitle_img = Assets.mainTitle_img,
                    maze1_img = Assets.maze1_img, maze2_img = Assets.maze2_img;
    private Button go;
    private Main main;

    public MenuState(Main main){

        this.main = main;

        go = new Button(main.getWindow().getCanvas().getWidth()/2 - goButton_img.getWidth()/2,
                main.getWindow().getCanvas().getHeight()/2 - goButton_img.getHeight()/2 + 100,
                goButton_img.getWidth(), goButton_img.getHeight(), goButton_img, goButton_img);
    }

    public void tick(){

        if(go.isClicked(main.getMouse())){
            State.setState(new ProgramState(main));
        }

    }

    public void render(Graphics g){

        g.drawImage(mainTitle_img, main.getWindow().getCanvas().getWidth()/2 - mainTitle_img.getWidth()/2, 50, null);

        g.drawImage(go.getButtonImage(), go.getX(), go.getY(), null);

        g.drawImage(maze1_img, main.getWindow().getCanvas().getWidth()/2 - main.getWindow().getCanvas().getWidth()/4 - maze1_img.getWidth()/2 - 50,
                main.getWindow().getCanvas().getHeight()/2, null);

        g.drawImage(maze2_img, main.getWindow().getCanvas().getWidth()/2 + main.getWindow().getCanvas().getWidth()/4 - maze1_img.getWidth()/2 + 50,
                main.getWindow().getCanvas().getHeight()/2, null);
    }

}
