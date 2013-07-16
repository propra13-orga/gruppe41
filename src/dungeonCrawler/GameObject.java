package dungeonCrawler;

import java.awt.Graphics;
import java.awt.Rectangle;

/**Game objects in the player's inventory
 */
public abstract class GameObject implements Drawable {
	protected GameElementImage gei = new GameElementImage();

	/**Constructor
	 */
	public GameObject() {
	}
	
	public abstract void performOn(GameElement element);
	
	/**Performs on the elements
	 * @param element
	 * @param logic
	 */
	public void performOn(GameElement element, GameLogic logic){
		this.performOn(element);
	}

	@Override
	public void draw(Graphics g) {
		Rectangle rect = g.getClipBounds();
		g.fillRect(rect.x, rect.y, rect.width, rect.height);
		
	}

}
