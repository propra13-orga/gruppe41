/**
 * 
 */
package dungeonCrawler;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;

/**loads a level from a file.
 * look for a *.lvl file to create your own level.
 * 
 * @author Tissen
 *
 */
public class LevelLoader {
	Dungeon level;
	App app;
	boolean loaded;

	// constructor
	public LevelLoader(Dungeon lvl, App a) {
		this.level = lvl;
		this.app = a;
		this.loaded = false;
	}

	public Dungeon getNextLevel() {
		if (app.currentLevel<app.level-1) app.currentLevel++;
		return getLevel();
	}

	public Dungeon getPreviousLevel() {
		if (app.currentLevel > 0) app.currentLevel--;
		return getLevel();
	}

	public Dungeon getLevel() {
		try {
			File f = new File("level" + app.currentLevel + ".lvl");
			FileReader reader;
			reader = new FileReader(f);
			for (int i=0;i<level.getHeight();i++) {
				for (int j=0;j<level.getWidth();j++) {
					LevelContent c = new LevelContent(reader.read()-48);
					level.setContent(j, i, c);
					if (c.getContent() == LevelContent.PLAYER) level.setPlayerPosition(j, i);
					if (c.getContent() == LevelContent.EXIT) level.setExitPosition(j, i);
				}
				reader.read(); // reads CR
				reader.read(); // reads LF
			}
			reader.close();
			loaded = true;
		}
		// show an error message
		catch (IOException e) {
			final JDialog dialog = new JDialog(app.window, "Warning", true);
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
			loaded = false;
		}
		return level;
	}
	
}
