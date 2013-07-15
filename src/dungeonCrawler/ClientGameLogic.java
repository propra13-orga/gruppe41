/**
 * 
 */
package dungeonCrawler;

import dungeonCrawler.GameElements.Player;

/**
 * @author Mattes
 *
 */
public class ClientGameLogic extends GameLogic {

	/**
	 * @param app
	 */
	public ClientGameLogic(App app) {
		super(app);
		for(GameElement e:level.getGameElements()){
			System.out.println("gf			" + e.getString());
			if(e instanceof Player){
				System.out.println("fdjaskdöfljaskldösfjkasöf");
			}
		}
	}

}
