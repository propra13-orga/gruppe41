package dungeonCrawler.GameElements;

import java.awt.Color;
import java.awt.Graphics;
import java.util.EnumSet;

import dungeonCrawler.ElementType;
import dungeonCrawler.EventType;
import dungeonCrawler.GameElement;
import dungeonCrawler.GameEvent;
import dungeonCrawler.Vector2d;

public class WarpPoint extends GameElement {
	
	private Vector2d target = new Vector2d(0,0);

	public WarpPoint(Vector2d position, Vector2d size) {
		super(position, size, "WarpPoint", EnumSet.of(ElementType.MOVABLE, ElementType.WALKABLE));
		// TODO Auto-generated constructor stub
	}

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(Color.CYAN);
		g.fillRect(0, 0, size.getX(), size.getY());

	}

	@Override
	public void GameEventPerformed(GameEvent e) {
		// TODO Auto-generated method stub
		if(e.element instanceof Player && e.type == EventType.COLLISION){
			e.gameLogic.teleportElement(e.element, target);
		}
	}

	public Vector2d getTarget() {
		return target;
	}

	public void setTarget(Vector2d target) {
		this.target = target;
	}

}
