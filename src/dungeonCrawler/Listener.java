package dungeonCrawler;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Listener implements KeyListener {
	App app;

	public Listener(App app) {
		this.app = app;
	}
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			System.out.println(app.n + "->" + app.n+1);
			app.startGame(app.n+1);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		switch (e.getKeyChar()) {
		case 'w': app.dungeon[app.n].move(Dungeon.UP); break;
		case 'd': app.dungeon[app.n].move(Dungeon.RIGHT); break;
		case 's': app.dungeon[app.n].move(Dungeon.DOWN); break;
		case 'a': app.dungeon[app.n].move(Dungeon.LEFT); break;
		}
		System.out.println(e.getKeyChar());
		app.camera.repaint();
		if (app.dungeon[app.n].complete) {
			System.out.println("Comleted");
			app.startGame(app.n+1);
		}
		else	if (app.dungeon[app.n].dead) {
			System.out.println("Died");
			app.startGame(app.n);
		}
	}
}
