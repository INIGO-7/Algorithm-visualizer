package main.states;

import java.awt.*;

public class ProgramState extends State{

    public ProgramState(){}

    public void tick(){}

    public void render(Graphics g) {

        g.setColor(Color.BLACK);
        g.fillRect(0, 0, 800, 600);

        //drawing the grid:

        g.setColor(new Color(150, 150, 150));

        // X axis

        for(int x = 0; x < 800; x += 25){
            g.drawLine(x, 0, x, 600);
        }

        // Y axis

        for(int y = 0; y < 600; y+=25){
            g.drawLine(0, y, 800, y);
        }

        //boundaries

        g.setColor(new Color(150, 150, 150));
        g.drawLine(0, 0, 800, 0);
        g.drawLine(0, 599, 800, 599);
        g.drawLine(0, 0, 0, 800);
        g.drawLine(799, 0, 799, 599);

    }

}
