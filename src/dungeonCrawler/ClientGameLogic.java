/**
 * 
 */
package dungeonCrawler;

import dungeonCrawler.GameElements.Player;
import dungeonCrawler.Network.Chat.Client;

/**
 * @author Mattes
 *
 */
public class ClientGameLogic extends GameLogic {

	private Client client;

	/**
	 * @param app
	 * @param client 
	 */
	public ClientGameLogic(App app, Client client) {
		super(app);
		this.client = client;
		for(GameElement e:level.getGameElements()){
			System.out.println("gf			" + e.getString());
			if(e instanceof Player){
				System.out.println("fdjaskdöfljaskldösfjkasöf");
			}
		}
	}

}
