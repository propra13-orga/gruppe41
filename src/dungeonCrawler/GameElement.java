/**
 * 
 */
package dungeonCrawler;

import java.util.EnumSet;

/**
 * @author Mattes
 *
 */
public abstract class GameElement implements Drawable {
	
	public final EnumSet<ElementType> type;
	private Vector2d position;
	private Vector2d size;
	/**
	 * 
	 */
	public GameElement(Vector2d position, Vector2d size) {
		// TODO Auto-generated constructor stub
		this.type = EnumSet.of(ElementType.IMMOVABLE);
		this.position = position;
		this.size = size;
	}

	/* (non-Javadoc)
	 * @see dungeonCrawler.Drawable#draw()
	 */
	/*@Override
	public void draw() {
		// TODO Auto-generated method stub

	}*/

}
