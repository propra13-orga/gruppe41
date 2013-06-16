package dungeonCrawler.GameElements;

import java.awt.Color;
import java.awt.Graphics;
import java.util.EnumSet;

import dungeonCrawler.ElementType;
import dungeonCrawler.GameElement;
import dungeonCrawler.Vector2d;

/**
 * @author Tissen
 *
 */
public class Player extends GameElement {

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

}
