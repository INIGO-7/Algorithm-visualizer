package main.states;

import java.awt.*;

public class ProgramState extends State{

    public ProgramState(){}

    public void tick(){}

    public void render(Graphics g) {

        g.setColor(Color.RED);
        g.fillRect(50, 50, 200, 200);

    }

}
