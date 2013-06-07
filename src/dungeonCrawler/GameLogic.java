package dungeonCrawler;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.BitSet;

public class GameLogic implements KeyListener {
	
	private GameContent level;
	private BitSet keys;

	public GameLogic() {
		// TODO Auto-generated constructor stub
		keys.clear();
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

}
