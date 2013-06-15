package dungeonCrawler;

import java.awt.Container;
import java.awt.Dimension;

import javax.swing.JFrame;

/**
 * Application that includes dungeons and main window
 * 
 * @author Tissen
 *
 */
public class App {
	JFrame window; // main window
	Dungeon[] dungeon; // levels of dungeon
	public Container cp; // content pane
	public MainMenu mainmenu; // main menu
	Camera camera; // camera that shows a current level
	int level; // number of level
	int currentLevel = 0; // current level number
//	Listener listener = new Listener(this); // listener that monitors the game
	GameLogic gameLogic = new GameLogic(this);
	LevelLoader loader;

	// constructor
	public App(int level, int width, int height) {
		// set main window parameters
		window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setTitle("Dungeon Crawler");
		window.setFocusable(true);
//		window.addKeyListener(listener);
		window.addKeyListener(gameLogic);
		window.setResizable(false);
		// set dungeon parameters
		this.level = level;
		dungeon = new Dungeon[level];
		for (int i=0;i<level;i++) {
			dungeon[i] = new Dungeon(width, height);
		}
		cp = window.getContentPane();
		cp.setPreferredSize(new Dimension(width, height)); // size of the game elements is now 50x50  pixels
		window.pack();
		window.setLocationRelativeTo(null);
		mainmenu = new MainMenu(this);
		cp.add(mainmenu);
		loader = new LevelLoader(dungeon[0], this);
	}

	// view window
	public void start() {
		window.setVisible(true);
	}

	public void startMainMenu(){
		cp.removeAll();
		cp.add(mainmenu);
		cp.repaint();
	}

	// starts a new level
	public void startGame() {
		if (currentLevel<level) {
			dungeon[currentLevel].complete = false;
			dungeon[currentLevel] = loader.getLevel();
			if (loader.loaded) {
				cp.removeAll();
				Camera camera = new Camera(dungeon[currentLevel]);
				this.camera = camera;
				//perhaps instead of camera a JPanel containing menu bar and camera
				cp.add(camera);
				cp.validate();
				gameLogic.timer.start();
			}
		}
		else {
			currentLevel = 0;
			startMainMenu();
		}

		/*	JPanel tmp = new JPanel(); // test for clipping
		tmp.setLayout(null);
		tmp.setSize(150, 130);
		camera.setSize(70, 70);
		tmp.add(camera);

		tmp.validate();
		cp.add(tmp);*/

	}

}
