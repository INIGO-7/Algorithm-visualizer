package main;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseManager implements MouseListener {

    private int mouseX, mouseY;
    private boolean leftClick, rightClick;

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
}
