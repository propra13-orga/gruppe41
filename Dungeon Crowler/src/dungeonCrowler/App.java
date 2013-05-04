package dungeonCrowler;

import javax.swing.JFrame;

/**
 * Application that includes dungeons and main window
 * 
 * @author Tissen/Gruppe41
 *
 */
public class App {
	JFrame window; // main window
	Dungeon[] dungeon; // levels of dungeon
	
	// constructor
	public App(int level, int width, int height) {
		// set main window parameters
		window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setTitle("Dungeon Crawler");
		window.setLocation(50, 50);
		window.setSize(width*100, height*100);
		window.setResizable(false);
		//set dungeon parameters
		dungeon = new Dungeon[level];
		dungeon[0] = new Dungeon(width, height);
		dungeon[1] = new Dungeon(width, height);
	}
	
	// view window
	public void start() {
		window.setVisible(true);
	}
}
