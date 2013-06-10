package dungeonCrawler;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;

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
	Listener listener = new Listener(this); // listener that monitors the game

	// constructor
	public App(int level, int width, int height) {
		// set main window parameters
		window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setTitle("Dungeon Crawler");
		window.setLocation(50, 50);
		window.setFocusable(true);
		window.addKeyListener(listener);
		//window.setSize(width*50, height*50);
		window.setResizable(false);
		// set dungeon parameters
		this.level = level;
		dungeon = new Dungeon[level];
		for (int i=0;i<level;i++) {
			dungeon[i] = new Dungeon(width, height);
		}
		cp = window.getContentPane();
		cp.setPreferredSize(new Dimension(width*50, height*50)); // size of the game elements is now 50x50  pixels
		window.pack();
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
		cp.repaint();
	}

	// starts a new level
	public void startGame(int n) {
		if (n<level) {
			this.currentLevel = n;
			dungeon[n].complete = false;
			if (loadLevel(dungeon[n], "level" + n + ".lvl")) {
				cp.removeAll();
				Camera camera = new Camera(dungeon[n]);
				this.camera = camera;
				//perhaps instead of camera a JPanel containing menu bar and camera
				cp.add(camera);
				cp.validate();
			}
		}
		else {
			n = 0;
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

	// loads a level from a file
	// look for a *.lvl file to create your own level
	public boolean loadLevel(Dungeon d, String s) {
		try {
			File f = new File(s);
			FileReader reader;
			reader = new FileReader(f);
			for (int i=0;i<d.getHeight();i++) {
				for (int j=0;j<d.getWidth();j++) {
					LevelContent c = new LevelContent(reader.read()-48);
					d.setContent(j, i, c);
					if (c.getContent() == LevelContent.PLAYER) d.setPlayerPosition(j, i);
					if (c.getContent() == LevelContent.EXIT) d.setExitPosition(j, i);
				}
				reader.read(); // reads CR
				reader.read(); // reads LF
			}
			reader.close();
			return true;
		}
		// show an error message
		catch (IOException e) {
			final JDialog dialog = new JDialog(window, "Warning", true);
			dialog.setLayout(new FlowLayout(FlowLayout.CENTER));
			dialog.setSize(250, 90);
			dialog.setLocation(150, 100);
			dialog.setResizable(false);
			dialog.add(new JLabel("Dungeon konnte nicht geladen werden."));
			JButton button = new JButton("OK");
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					dialog.dispose();
				}
			});
			dialog.add(button);
			dialog.setVisible(true);
			return false;
		}
	}

}
