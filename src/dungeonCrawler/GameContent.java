/**
 * 
 */
package dungeonCrawler;

import java.util.LinkedList;
import java.util.ListIterator;

import dungeonCrawler.GameElements.Player;

/**
 * @author Mattes
 *
 */
public class GameContent {

	private LinkedList<GameElement> gameElements = new LinkedList<GameElement>();
	private LinkedList<GameElement> movables = new LinkedList<GameElement>();
	private GameElement player;
	/**
	 * 
	 */
	public GameContent() {
		// TODO Auto-generated constructor stub
	}

	public boolean addGameElement(GameElement e){
		boolean ret = true;
		GameElement collisionElement = collisionWith(e);
		if (collisionElement != null) {
			Error err = new Error("Kann '" + e.getName() +
					"', Position: " + e.position.getX() + "," + e.position.getY() +
					" Größe: " + e.size.getX() + "," + e.size.getY() +
					" nicht setzen. (" + collisionElement.getName() +
					": " + collisionElement.position.getX() + "," + collisionElement.position.getY() +
					" Größe: " + collisionElement.size.getX() + "," + collisionElement.size.getY() + ")");
			err.showMe();
			return false;
		}
		if (e.getName().equalsIgnoreCase("PLAYER"))
			player = e;
		if(e.type.contains(ElementType.MOVABLE))
			ret &= this.movables.add(e);
		if(e instanceof Player){
			this.player = e;
		}
		System.out.println(gameElements.size());
		return this.gameElements.add(e) && ret;
	}

	public ListIterator<GameElement> getIterator(){
		return this.gameElements.listIterator();
	}

	public ListIterator<GameElement> getMovablesIterator(){
		return this.movables.listIterator();
	}

	private GameElement collisionWith(GameElement newElement) {
		for (GameElement e: gameElements) {
			if (e.collision(newElement) && !e.type.contains(ElementType.WALKABLE) && !newElement.type.contains(ElementType.WALKABLE))
				return e;
		}
		return null;
	}
	
	public LinkedList<GameElement> getGameElements() {
		return gameElements;
	}
	
	public boolean removeElement(GameElement element){
		boolean ret = true;
		ret &= movables.remove(element);
		return gameElements.remove(element) && ret;
	}

	public GameElement getPlayer() {
		return player;
	}

	public void setPlayer(GameElement player) {
		this.player = player;
	}

}
