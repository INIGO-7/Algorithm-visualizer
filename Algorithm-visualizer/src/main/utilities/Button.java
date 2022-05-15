package main.utilities;

import main.MouseManager;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Button {

    private int x, y, width, height;
    private BufferedImage buttonImage;
    private Rectangle buttonRect;

    public Button(int x, int y, int width, int height, BufferedImage img){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.buttonImage = img;
        this.buttonRect = new Rectangle(x , y, width, height);
    }

    public boolean isClicked(MouseManager mouse){
        return buttonRect.contains(mouse.getMouseX(), mouse.getMouseY()) && mouse.getLeftClick();
    }

    public BufferedImage getButtonImage(){
        return buttonImage;
    }

    public Rectangle getRectangle(){
        return buttonRect;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }
}
