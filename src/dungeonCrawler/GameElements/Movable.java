/**
 * 
 */
package dungeonCrawler.GameElements;

import java.awt.Graphics;

import dungeonCrawler.ElementType;
import dungeonCrawler.GameElement;
import dungeonCrawler.Vector2d;

/**
 * @author Mattes
 *
 */
public class Movable extends GameElement {

	/**
	 * @param position
	 * @param size
	 */
	public Movable(Vector2d position, Vector2d size) {
		super(position, size);
		this.type.clear();
		this.type.add(ElementType.MOVABLE);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see dungeonCrawler.Drawable#draw(java.awt.Graphics)
	 */
	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub

	}

}
