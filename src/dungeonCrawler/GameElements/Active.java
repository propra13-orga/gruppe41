/**
 * 
 */
package dungeonCrawler.GameElements;

//import java.awt.Graphics;

import java.util.BitSet;

import dungeonCrawler.ElementType;
import dungeonCrawler.GameElement;
import dungeonCrawler.GameLogic;
import dungeonCrawler.SettingSet;
//import dungeonCrawler.GameEvent;
import dungeonCrawler.Vector2d;

/**
 * @author Mattes
 *
 */
public abstract class Active extends GameElement {

	/**
	 * @param position
	 * @param size
	 */
	public Active(Vector2d position, Vector2d size) {
		super(position, size);
		this.type.clear();
		this.type.add(ElementType.MOVABLE);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see dungeonCrawler.Drawable#draw(java.awt.Graphics)
	 */
	public abstract void interaction(GameLogic logic, SettingSet settings, BitSet keys);

}
