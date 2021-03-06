package dungeonCrawler;

import java.util.LinkedList;
import java.util.ListIterator;

import dungeonCrawler.GameElements.Active;
import dungeonCrawler.GameElements.ItemPanel;
import dungeonCrawler.GameElements.Player;
import dungeonCrawler.GameElements.StatusBar;
import dungeonCrawler.GameElements.Exit;

/**The entire contents of a game level
 * @author Mattes
 */
public class GameContent {

	private LinkedList<GameElement> gameElements = new LinkedList<GameElement>();
	private LinkedList<GameElement> movables = new LinkedList<GameElement>();
	private LinkedList<Active> actives = new LinkedList<Active>();
	private GameElement player;
	private GameElement Exit;
	private GameElement statusBar;
	private GameElement itemPanel;
	private GameLogic logic;
	private int nextFreeID = 20000;
	
	/**Constructor
	 * @param gl the {@link GameLogic}
	 */
	public GameContent(GameLogic gl) {
		this.logic = gl;
		// TODO Auto-generated constructor stub
	}

	public int getNextFreeID() {
		return nextFreeID++;
	}
	
	/**Search for a {@link GameElement}
	 * @param id as {@link int}
	 * @return the {@link GameElement} that has this id or {@link null}
	 */
	public GameElement find(int id){
		try{
		for(GameElement e: gameElements){
			if(e.id == id)
				return e;
		}}
		catch(java.util.ConcurrentModificationException e){
			
		}
		return null;
	}

	/**Add a {@link GameElement} to the list
	 * @param e element to be added
	 * @return {@link true} if successfully added, else {@link false}
	 */
	public boolean addGameElement(GameElement e){
		boolean ret = true;
/*		GameElement collisionElement = collisionWith(e);
		if (collisionElement != null) {	
			Error err = new Error("Kann '" + e.getName() +
					"', Position: " + e.position.getX() + "," + e.position.getY() +
					" Größe: " + e.size.getX() + "," + e.size.getY() +
					" nicht setzen. (" + collisionElement.getName() +
					": " + collisionElement.position.getX() + "," + collisionElement.position.getY() +
					" Größe: " + collisionElement.size.getX() + "," + collisionElement.size.getY() + ")");
			err.showMe();
			return false;
		}*/
		if (e.getName().equalsIgnoreCase("PLAYER")) {
			player = e;
			statusBar = new StatusBar(new Vector2d(0, 200), new Vector2d(500, 50), player);
			itemPanel = new ItemPanel(new Vector2d(0, 0), new Vector2d(30, 250), player);
		}
		if(e.type.contains(ElementType.MOVABLE))
			ret &= this.movables.add(e);
		if(e instanceof Player){
			this.player = e;
		}
		if(e instanceof Exit){
			this.Exit=e;
		}
		if(e instanceof Active){
			ret &= this.actives.add((Active)e);
		}
		if(e.id > this.nextFreeID)
			this.nextFreeID = e.id + 1;
		return this.gameElements.add(e) && ret;
	}

	public ListIterator<GameElement> getIterator(){
		return this.gameElements.listIterator();
	}

	public ListIterator<GameElement> getMovablesIterator(){
		return this.movables.listIterator();
	}

/*	private GameElement collisionWith(GameElement newElement) {
		for (GameElement e: gameElements) {
			if (e.collision(newElement) && !e.type.contains(ElementType.WALKABLE) && !newElement.type.contains(ElementType.WALKABLE))
				return e;
		}
		return null;
	}
*/	
	public LinkedList<GameElement> getGameElements() {
		return gameElements;
	}
	
	public boolean removeElement(GameElement element){
		boolean ret = true;
		ret &= movables.remove(element);
		ret &= actives.remove(element);
		return gameElements.remove(element) && ret;
	}

	public LinkedList<Active> getActives() {
		return actives;
	}

	public GameElement getPlayer() {
		return player;
	}
	
	public GameElement getExit(){
		return this.Exit;
	}

	public void setPlayer(GameElement player) {
		this.player = player;
	}
	
	public GameElement getStatusBar() {
		return statusBar;
	}
	
	public GameElement getItemPanel() {
		return itemPanel;
	}
	
	public GameLogic getGameLogic() {
		return this.logic;
	}

}
