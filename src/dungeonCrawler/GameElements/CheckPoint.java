package dungeonCrawler.GameElements;

import java.awt.Graphics;
import java.util.EnumSet;

import dungeonCrawler.ElementType;
import dungeonCrawler.EventType;
import dungeonCrawler.GameElement;
import dungeonCrawler.GameEvent;
import dungeonCrawler.Vector2d;

public class CheckPoint extends GameElement {

	public CheckPoint(Vector2d position, Vector2d size) {
		super(position, size, "CHECKPOINT", EnumSet.of(ElementType.IMMOVABLE, ElementType.WALKABLE));
		// TODO Auto-generated constructor stub
	}

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub

	}

	@Override
	public void GameEventPerformed(GameEvent e) {
		// TODO Auto-generated method stub
		if(e.element instanceof Player && e.type == EventType.COLLISION){
			e.gameLogic.setCheckPoint(e.element.getPosition());
		}
	}

}
