package dungeonCrawler;

import java.awt.Container;
import java.awt.Dimension;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

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
	public Container cp;
	public MainMenu mainmenu;
	Camera camera;
	int level;
	int n = 0;

	// constructor
	public App(int level, int width, int height) {
		// set main window parameters
		window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setTitle("Dungeon Crawler");
		window.setLocation(50, 50);
		//window.setSize(width*50, height*50);
		//window.setResizable(false);
		// set dungeon parameters
		dungeon = new Dungeon[level];
		for (int i=0;i<level;i++) {
			dungeon[i] = new Dungeon(width, height);
		}
		cp = window.getContentPane();
		cp.setPreferredSize(new Dimension(width*50, height*50));
		window.pack();
		mainmenu = new MainMenu(this);
		cp.add(mainmenu);

		this.level = level;
		window.addKeyListener(new Listener(this, n));
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

	public void startGame(int n) {
		this.n = n;
		cp.removeAll();
		loadLevel(dungeon[n], "level" + n + ".lvl");
		Camera camera = new Camera(dungeon[n]);
		//perhaps instead of camera a JPanel containing menu bar and camera
		cp.add(camera);
		cp.validate();

		/*	JPanel tmp = new JPanel(); // test for clipping
		tmp.setLayout(null);
		tmp.setSize(150, 130);
		camera.setSize(70, 70);
		tmp.add(camera);

		tmp.validate();
		cp.add(tmp);*/

	}

	public void loadLevel(Dungeon d, String s) {
		try {
			File f = new File(s);
			FileReader reader;
			reader = new FileReader(f);
			for (int i=0;i<d.getHeight();i++) {
				for (int j=0;j<d.getWidth();j++) {
					LevelContent c = new LevelContent(reader.read()-48);
					d.setContent(j, i, c);
				}
				reader.read(); // reads CR
				reader.read(); // reads LF
			}
			reader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
