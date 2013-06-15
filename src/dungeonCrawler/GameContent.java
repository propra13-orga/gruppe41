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

	public GameElement getPlayer() {
		return player;
	}

	public void setPlayer(GameElement player) {
		this.player = player;
	}

}
