/**
 * 
 */
package dungeonCrawler;

/**
 * @author Mattes
 *
 */
public class GameEvent {
	
	public final GameElement element;
	public final EventType type;

	/**
	 * 
	 */
	public GameEvent(GameElement element, EventType type) {
		this.element = element;
		this.type = type;
	}

	public GameElement getElement() {
		return element;
	}

	public EventType getType() {
		return type;
	}

}
