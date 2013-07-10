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
	private int life = 300;
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
			life--;
			e.gameLogic.moveElement(this, direction);
			if(life<0)
				this.size = new Vector2d(0,0);
		}
		if(e.type == EventType.COLLISION && !e.element.type.contains(ElementType.WALKABLE)){
			if (e.element instanceof Player) {
				Player element = (Player) e.element;
				element.reduceHealth(10, e.gameLogic);
			}
			if(e.element instanceof Enemy){
				Enemy element = (Enemy) e.element;
				element.reduceHealth(10, e.gameLogic);
			}
			if(!(e.element instanceof Bullet)){
				this.size = new Vector2d(0,0);
			}
		}
	}

	public Vector2d getDirection() {
		return direction;
	}

	public void setDirection(Vector2d direction) {
		this.direction = direction;
	}

}
