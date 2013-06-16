package dungeonCrawler.GameElements;

import java.awt.Color;
import java.awt.Graphics;
import java.util.EnumSet;

import dungeonCrawler.ElementType;
import dungeonCrawler.EventType;
import dungeonCrawler.GameElement;
import dungeonCrawler.GameEvent;
import dungeonCrawler.Vector2d;

public class Bullet extends GameElement {
	private Vector2d direction = new Vector2d(0,0);
	public Bullet(Vector2d position, Vector2d size) {
		super(position, size, "Bullet", EnumSet.of(ElementType.MOVABLE, ElementType.WALKABLE));
		// TODO Auto-generated constructor stub
	}

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, size.getX(), size.getY());
	}

	@Override
	public void GameEventPerformed(GameEvent e) {
		// TODO Auto-generated method stub
		if(e.type == EventType.TIMER){
			e.gameLogic.moveElement(this, direction);
		}
		if(e.type == EventType.COLLISION){
			if (e.element instanceof Player) {
				Player elementPlayer = (Player) e.element;
				elementPlayer.reduceHealth(10, e.gameLogic);
			}
			this.size = new Vector2d(0,0);
		}
	}

	public Vector2d getDirection() {
		return direction;
	}

	public void setDirection(Vector2d direction) {
		this.direction = direction;
	}

}
