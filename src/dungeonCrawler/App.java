package dungeonCrawler;

import java.awt.Container;
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

	// constructor
	public App(int level, int width, int height) {
		// set main window parameters
		window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setTitle("Dungeon Crawler");
		window.setLocation(50, 50);
		window.setSize(width*100, height*100);
		//window.setResizable(false);
		// set dungeon parameters
		dungeon = new Dungeon[level];
		dungeon[0] = new Dungeon(width, height);
		camera = new Camera(dungeon[0]);

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
		loadLevel(dungeon[0], "level0.lvl");
		Camera camera = new Camera(dungeon[0]); //perhaps instead of camera a JPanel containing menu bar and camera
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

	public void loadLevel(Dungeon d, String s) {
		File f = new File(s);
		FileReader reader;
		try {
			reader = new FileReader(f);
			for (int i=0;i<d.getHeight();i++) {
				for (int j=0;j<d.getWidth();j++) {
					LevelContent c = new LevelContent(reader.read());
					d.setContent(j, i, c);
				}
				reader.read(); // reads CR
				reader.read(); // reads LF
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
