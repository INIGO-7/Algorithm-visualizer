package main.states;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import main.Main;
import main.utilities.Button;

public class MenuState extends State{

    private BufferedImage goButton;
    private Button go;
    private Main main;

    public MenuState(Main main){

        this.main = main;

        try {
            goButton = ImageIO.read(new File("resources/go.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        go = new Button(400, 400, 40, 18, goButton);
    }

    public void tick(){

        if(go.isClicked(main.getMouse())){
            State.setState(new ProgramState(main));
        }

    }

    public void render(Graphics g){
        g.drawImage(go.getButtonImage(), go.getX(), go.getY(), null);
    }

}
