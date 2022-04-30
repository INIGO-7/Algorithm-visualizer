package main;

import main.states.ProgramState;
import main.states.MenuState;
import main.states.State;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class Main implements Runnable{
	
	//Window data
	private Window window;
	private int width, height;
	private String title;
	
	//Loop and threading data
	long past, current;
	float rate, updateDiff;
	boolean running;
	Thread t;

	//mouse data
	private MouseManager mouseManager;

	//attributes for rendering
	private Graphics g;
	private BufferStrategy bs;

	//states
	private MenuState menuState;
	private ProgramState programState;

	public Main(int width, int height, String title) {
		
		this.width = width;
		this.height = height;
		this.title = title;
		
		window = new Window(width, height, title);
		
		mouseManager = new MouseManager();

		window.getCanvas().addMouseListener(mouseManager);

		programState = new ProgramState();
		State.setState(programState);
	}

	@Override
	public void run() {
		past = System.nanoTime();
		rate = 1000000000/60;						//rate at which we want to update stuff, which is 1/60 of a second
													//this is 60 frames per second, such as most videogames nowadays.
		while(running) {

			current = System.nanoTime();
			updateDiff += (current-past)/rate;
			
			if(updateDiff >= 1) {				//if more than a 1/60 of a sec. has passed, then do the following...
				render();
				tick();
				updateDiff = 0;
			}
			past = current;
			
		}
		stop();
	}
	
	public void render() {

		//we create a buffer strategy for the canvas
		bs = window.getCanvas().getBufferStrategy();
		if(bs == null) {
			window.getCanvas().createBufferStrategy(3);
			return;
		}

		//then we initialise the graphics variable with the new buffers
		g = bs.getDrawGraphics();

		//everything drawn before is erased
		g.clearRect(0, 0, width, height);

		//here we can start drawing:

		State.getState().render(g);

		if(State.getState() instanceof ProgramState)  { ProgramState gs = (ProgramState) State.getState(); }

		//here we end drawing
		bs.show();
		g.dispose();

	}
	
	public void tick() {
		State.getState().tick();
	}
	
	public synchronized void start() {
		
		if(!running) running = true;
		else return;
		
		t = new Thread(this);
		t.start();
	}
	
	public synchronized void stop() {
		
		if(running) running = false;
		else return;
		
		try {t.join();} 
		catch (InterruptedException e) {e.printStackTrace();}
		
	}
	
}