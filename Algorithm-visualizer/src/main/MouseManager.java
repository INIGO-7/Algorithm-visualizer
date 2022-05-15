package main;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseManager implements MouseListener, MouseMotionListener {

    private int mouseX, mouseY;
    private boolean leftClick, rightClick;

    // MouseListener methods:

    @Override
    public void mousePressed(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON1) leftClick = true;
        if(e.getButton() == MouseEvent.BUTTON3) rightClick = true;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON1) leftClick = false;
        if(e.getButton() == MouseEvent.BUTTON3) rightClick = false;
    }

    public boolean getLeftClick() { return this.leftClick; }

    public boolean getRightClick() { return this.rightClick; }

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    // MouseMotionListener methods:

    @Override
    public void mouseDragged(MouseEvent e) {}

    @Override
    public void mouseMoved(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
    }

    public int getMouseX(){
        return mouseX;
    }

    public int getMouseY(){
        return mouseY;
    }
}
