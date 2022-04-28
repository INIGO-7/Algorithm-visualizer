package main.states;

import java.awt.*;

public abstract class State {

    public static State currentState = null;

    public static void setState(State st) {
        currentState = st;
    }

    public static State getState() {
        return currentState;
    }

    public abstract void tick();

    public abstract void render(Graphics g);
}
