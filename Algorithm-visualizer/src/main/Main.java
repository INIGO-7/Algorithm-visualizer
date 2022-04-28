package main;

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

	public Main(int width, int height, String title) {
		
		this.width = width;
		this.height = height;
		this.title = title;
		
		window = new Window(width, height, title);
		
		mouseManager = new MouseManager();

		window.getCanvas().addMouseListener(mouseManager);
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
		
	}
	
	public void tick() {

		if(mouseManager.getLeftClick()){
			System.out.println("left click");
		}
		if(mouseManager.getRightClick()){
			System.out.println("right click");
		}
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
