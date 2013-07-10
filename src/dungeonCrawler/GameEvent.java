/**
 * 
 */
package dungeonCrawler;

/**
 * @author Mattes
 *
 */
public class GameEvent {

	public final GameLogic gameLogic;
	public final GameElement element;
	public final EventType type;

	/**
	 * 
	 */
	public GameEvent(GameElement element, EventType type, GameLogic logic) {
		this.element = element;
		this.type = type;
		this.gameLogic = logic;
	}


/*	public GameElement getElement() {
		return element;
	}*/


}
