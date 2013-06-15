package dungeonCrawler;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Listener that monitors the game
 * 
 * @author Tissen
 *
 */
public class Listener implements KeyListener {
	App app;

	public Listener(App app) {
		this.app = app;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		// cheat ;)
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			app.currentLevel++;
			app.startGame();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {}

	@Override
	public void keyTyped(KeyEvent e) {
		switch (e.getKeyChar()) {
		case 'w': app.dungeon[app.currentLevel].move(app.dungeon[app.currentLevel].player, Dungeon.UP); break;
		case 'd': app.dungeon[app.currentLevel].move(app.dungeon[app.currentLevel].player, Dungeon.RIGHT); break;
		case 's': app.dungeon[app.currentLevel].move(app.dungeon[app.currentLevel].player, Dungeon.DOWN); break;
		case 'a': app.dungeon[app.currentLevel].move(app.dungeon[app.currentLevel].player, Dungeon.LEFT); break;
		}
		app.camera.repaint();
		if (app.dungeon[app.currentLevel].complete) {
			app.currentLevel++;
			app.startGame(); // start next level
		}
		else	if (app.dungeon[app.currentLevel].dead) {
			app.dungeon[app.currentLevel].dead = false; // cast voodoo spell
			app.startGame(); // restart level
		}
	}
}
