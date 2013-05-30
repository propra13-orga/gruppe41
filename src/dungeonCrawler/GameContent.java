/**
 * 
 */
package dungeonCrawler;

import java.util.LinkedList;
import java.util.ListIterator;

/**
 * @author Mattes
 *
 */
public class GameContent {

	private LinkedList<GameElement> gameElements;
	private LinkedList<GameElement> movables;
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
		return this.gameElements.add(e) && ret;
	}
	
	public ListIterator<GameElement> getIterator(){
		return this.gameElements.listIterator();
	}
	
	public ListIterator<GameElement> getMovablesIterator(){
		return this.movables.listIterator();
	}

}
