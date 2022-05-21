package main.states;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import main.Main;
import main.utilities.Button;

public class MenuState extends State{

    private BufferedImage goButton, title;
    private Button go;
    private Main main;

    public MenuState(Main main){

        this.main = main;

        try {
            title = ImageIO.read(new File("resources/menuTitle.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            goButton = ImageIO.read(new File("resources/goProgramState.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        go = new Button(main.getWindow().getCanvas().getWidth()/2 - goButton.getWidth()/2,
                main.getWindow().getCanvas().getHeight()/2 - goButton.getHeight()/2 + 100,
                goButton.getWidth(), goButton.getHeight(), goButton);
    }

    public void tick(){

        if(go.isClicked(main.getMouse())){
            State.setState(new ProgramState(main));
        }

    }

    public void render(Graphics g){

        g.drawImage(title, main.getWindow().getCanvas().getWidth()/2 - title.getWidth()/2, 50, null);

        g.drawImage(go.getButtonImage(), go.getX(), go.getY(), null);
    }

}
