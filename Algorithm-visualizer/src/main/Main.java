package main;

public class Main {
	
	//Window data
	private Window window;
	private int width, height;
	private String title;
	
	public Main(int width, int height, String title) {
		
		this.width = width;
		this.height = height;
		this.title = title;
		
		window = new Window(width, height, title);
		
		
	}
	
}
