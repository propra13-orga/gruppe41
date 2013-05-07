package dungeonCrawler;

import java.awt.Component;
import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Application that includes dungeons and main window
 * 
 * @author Tissen/Gruppe41
 *
 */
public class App {
	JFrame window; // main window
	Dungeon[] dungeon; // levels of dungeon
	public Container cp;
	public MainMenu mainmenu;
	
	// constructor
	public App(int level, int width, int height) {
		// set main window parameters
		window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setTitle("Dungeon Crawler");
		window.setLocation(50, 50);
		window.setSize(width*100, height*100);
	//	window.setResizable(false);
		//set dungeon parameters
		dungeon = new Dungeon[level];
		dungeon[0] = new Dungeon(width, height);
		dungeon[1] = new Dungeon(width, height);
		
		cp = window.getContentPane();
		mainmenu = new MainMenu(this);
		cp.add(mainmenu);
		
	}
	
	// view window
	public void start() {
		window.setVisible(true);
	}
	
	public void startMainMenu(){
		cp.removeAll();
		cp.add(mainmenu);
		cp.validate();
	}

	public void startGame() {
		cp.removeAll();
		Camera camera = new Camera(); //perhaps instead of camera a JPanel containing menu bar and camera
		cp.add(camera);
		
	/*	JPanel tmp = new JPanel(); // test for clipping
		tmp.setLayout(null);
		tmp.setSize(150, 130);
		camera.setSize(70, 70);
		tmp.add(camera);
		
		tmp.validate();
		cp.add(tmp);*/
		
		cp.validate();
	}
}
