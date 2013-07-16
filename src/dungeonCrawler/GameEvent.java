package dungeonCrawler;

/**A game event
 * @author Mattes
 */
public class GameEvent {

	public final GameLogic gameLogic;
	public final GameElement element;
	public final EventType type;

	/**Constructor
	 * @param element as {@link GameElement}
	 * @param type as {@link EventType}
	 * @param logic as {@link GameLogic}
	 */
	public GameEvent(GameElement element, EventType type, GameLogic logic) {
		this.element = element;
		this.type = type;
		this.gameLogic = logic;
	}

}
