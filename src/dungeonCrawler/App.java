package dungeonCrawler;

import java.awt.Container;
import java.awt.Dimension;
import java.io.File;
import javax.swing.JFrame;



/**
 * Application that includes dungeons and main window
 * 
 * @author Tissen
 *
 */
public class App {
	JFrame window; // main window
	public Container cp; // content pane
	public MainMenu mainmenu; // main menu
	Camera camera; // camera that shows a current level
	int level; // number of level
	public int currentLevel = 0; // current level number
//	Listener listener = new Listener(this); // listener that monitors the game
	protected GameLogic gameLogic = new GameLogic(this);
	public LevelLoader loader;
	public GameContent gameContent;
	private Editormenu editormenu;
	private Quest quest = new Quest();
	public Boolean editmode = false;

	// constructor
	public App(int level, int width, int height) {
		// set main window parameters
		initialize();
		// set dungeon parameters
		this.level = level;
		cp = window.getContentPane();
		cp.setPreferredSize(new Dimension(width, height)); // size of the game elements is now 50x50  pixels

		mainmenu = new MainMenu(this);
		editormenu = new Editormenu(this);
		cp.add(mainmenu);
		gameContent = new GameContent(gameLogic);
		loader = new LevelLoader(gameContent, this);
		window.pack();
		window.setLocationRelativeTo(null);
	}
	
	private void initialize(){
		window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setTitle("Dungeon Crawler");
		window.setFocusable(true);
		window.addKeyListener(gameLogic);
		window.setResizable(false);
		currentLevel=0;
	}

	// view window
	public void start() {
		window.setVisible(true);
	}

	public void startMainMenu(){
		this.initialize();
		cp.removeAll();
		cp.add(mainmenu);
		cp.repaint();
	}
	
	public void starteditormenu(){
		cp.removeAll();	
		cp.add(editormenu);
		cp.validate();
		cp.repaint();
	}

	
	
	public void startEdit(File file) {
		editmode = true;
		if (currentLevel<level) {
//			dungeon[currentLevel].complete = false;
			gameContent = loader.getLevel();			
			if (loader.loaded) {
				cp.removeAll();
				Camera camera = new Camera(gameContent);
				gameLogic.setLevel(gameContent);
				this.camera = camera;
				//perhaps instead of camera a JPanel containing menu bar and camera
				cp.add(camera);
				cp.validate();
				GameLogic.timer.start();
				gameLogic.file=file;
			}
		}
		else {
			currentLevel = 0;
			startMainMenu();
			GameLogic.timer.stop();
		}

		/*	JPanel tmp = new JPanel(); // test for clipping
		tmp.setLayout(null);
		tmp.setSize(150, 130);
		camera.setSize(70, 70);
		tmp.add(camera);

		tmp.validate();
		cp.add(tmp);*/

	}
	
	
	
	// starts a new level
	public void startGame() {
		if (currentLevel<level) {
//			dungeon[currentLevel].complete = false;
			gameContent = loader.getLevel();
			
			if (loader.loaded) {
				cp.removeAll();
				Camera camera = new Camera(gameContent);
				gameLogic.setLevel(gameContent);
				this.camera = camera;
				//perhaps instead of camera a JPanel containing menu bar and camera
				cp.add(camera);
				cp.validate();
				GameLogic.timer.stop();
				quest.startLevel(currentLevel);
			}
		}
		else {
			currentLevel = 0;
			startMainMenu();
			GameLogic.timer.stop();
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
