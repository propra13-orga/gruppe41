package dungeonCrawler.GameElements;

import java.awt.Graphics;
import java.util.EnumSet;

import dungeonCrawler.ElementType;
import dungeonCrawler.GameElement;
import dungeonCrawler.Vector2d;

/**
 * @author Tissen
 *
 */
public class Exit extends GameElement {

	/**
	 * @param position
	 * @param size
	 */
	public Exit(Vector2d position, Vector2d size) {
		super(position, size, "EXIT", EnumSet.of(ElementType.IMMOVABLE));
	}

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub

	}

}
