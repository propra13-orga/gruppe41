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
public class Trap extends GameElement {

	/**
	 * @param position
	 * @param size
	 */
	public Trap(Vector2d position, Vector2d size) {
		super(position, size);
		this.type = EnumSet.of(ElementType.IMMOVABLE, ElementType.WALKABLE);
	}

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(Color.RED);
		g.fillRect(0, 0, size.getX(), size.getY());
	}

	@Override
	public void GameEventPerformed(GameEvent e) {
		// TODO Auto-generated method stub
		if(e.element instanceof Player && e.type == EventType.COLLISION){
			System.out.println("autsch!");
			Player elementPlayer = (Player) e.element;
			elementPlayer.reduceHealth(10, e.gameLogic);
		}
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Trap";
	}

}
