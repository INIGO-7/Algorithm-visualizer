package main;

public class Main {

	//Window data
	Window window;
	int width, height;
	String title;
	
	
	public Main(String title, int width, int height) {
		
		this.title = title;
		this.width = width;
		this.height = height;
		
		window = new Window(title, width, height);
		
	}
	
}
