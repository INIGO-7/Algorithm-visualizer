package main.utilities;

import main.MouseManager;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Button {

    private int x, y, width, height;
    private BufferedImage buttonImage;
    private Rectangle buttonRect;
    private boolean isOn;
    long current, past = System.currentTimeMillis();
    double diff = 500;                     //we want the user to be able to click the button 2 times per second

    public Button(int x, int y, int width, int height, BufferedImage img){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.isOn = false;          //by default the button is off
        this.buttonImage = img;
        this.buttonRect = new Rectangle(x , y, width, height);
    }

    public boolean isClicked(MouseManager mouse){

        current = System.currentTimeMillis();

        if(buttonRect.contains(mouse.getMouseX(), mouse.getMouseY()) && mouse.getLeftClick() && (current - past) / diff >= 1){

            isOn = !isOn;   //we invert the state of isOn
            past = current;
            return true;
        }else{
            return false;
        }


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

    public void setOn(){
        isOn = true;
    }

    public void setOff(){
        isOn = false;
    }

    public boolean isOn(){
        return isOn;
    }
}
