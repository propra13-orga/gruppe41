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
public class NPC extends GameElement {

	/**
	 * @param position
	 * @param size
	 */
	public NPC(Vector2d position, Vector2d size) {
		super(position, size, "NPC", EnumSet.of(ElementType.MOVABLE, ElementType.WALKABLE));
	}

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(Color.GREEN);
		g.fillRect(position.getX(), position.getY(), size.getX(), size.getY());
	}

}
