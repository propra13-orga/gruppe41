package dungeonCrawler.GameElements;

import java.awt.Color;
import java.awt.Graphics;
import java.util.EnumSet;

import dungeonCrawler.ElementType;
import dungeonCrawler.EventType;
import dungeonCrawler.GameElement;
import dungeonCrawler.GameEvent;
import dungeonCrawler.Vector2d;

/**
 * @author Tissen
 *
 */
public class Player extends GameElement {
		private int Health=1000;
	/**
	 * @param position
	 * @param size
	 */
	public Player(Vector2d position, Vector2d size) {
		super(position, size, "PLAYER", EnumSet.of(ElementType.MOVABLE));
	}

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(Color.BLUE);
		g.fillRect(0, 0, size.getX(), size.getY());
	}
	
	public void setPosition(Vector2d pos) {
		this.position = pos;
	}

	@Override
	public void GameEventPerformed(GameEvent e) {
		if(e.element instanceof Trap && e.type == EventType.COLLISION){
		}
		// TODO Auto-generated method stub
		
	}
	
	public void setHealt(int Health) {
		this.Health = Health;
	}
	
	public void reduceHealth(int Health) {
		if (this.Health-Health >= 0){
			this.Health = this.Health-Health;
			System.out.println("Health verloren! Health: " + this.Health);
			}
		else  {
			System.out.println("!TOT! (x.x) Health: " + this.Health);
		}
	}
	
	public int getHealt() {
		return this.Health;
	}

}
