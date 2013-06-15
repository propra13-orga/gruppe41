package dungeonCrawler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.BitSet;

import javax.swing.Timer;

public class GameLogic implements KeyListener, ActionListener {
	
	private GameContent level;
	private BitSet keys;
	protected Timer timer;
	private App app;
	GameElement player;

	public GameLogic(App app) {
		// TODO Auto-generated constructor stub
		keys = new BitSet();
		keys.clear();
		timer = new Timer(50, this);
		timer.setActionCommand("Timer");
		timer.stop();
		this.app = app;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		keys.set(e.getKeyCode());
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		keys.clear(e.getKeyCode());
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	public GameContent getLevel() {
		return level;
	}

	public void setLevel(GameContent level) {
		this.level = level;
	}
	
	public boolean moveElement(GameElement e, Vector2d direction){
		if(e.type.contains(ElementType.MOVABLE)){
			return true;
		}
		else
			return false;
	}
	
	public boolean teleportElement(GameElement e, Vector2d position){
		return false;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO: abfragen, welche Bits gesetzt sind und ensprechend handeln
		player = app.dungeon[app.currentLevel].player;
		if (keys.get(37)) {// left arrow
			app.dungeon[app.currentLevel].move(player, Dungeon.LEFT);
			System.out.println("LEFT");
		}
		if (keys.get(38)) {// up arrow
			app.dungeon[app.currentLevel].move(player, Dungeon.UP);
			System.out.println("UP");
		}
		if (keys.get(39)) {// right arrow
			app.dungeon[app.currentLevel].move(player, Dungeon.RIGHT);
			System.out.println("RIGHT");
		}
		if (keys.get(40)) {// down arrow
			app.dungeon[app.currentLevel].move(player, Dungeon.DOWN);
			System.out.println("DOWN");
		}
		if (e.getActionCommand() == "Timer")
			app.camera.repaint();
	}

}
