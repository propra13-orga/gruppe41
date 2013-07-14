/**
 * 
 */
package dungeonCrawler;

import java.net.Socket;
import java.util.Map.Entry;

import dungeonCrawler.GameElements.NetworkPlayer;
import dungeonCrawler.GameElements.Player;
import dungeonCrawler.Network.Lounge.GameServer;
import dungeonCrawler.Network.Lounge.State;

/**
 * @author Mattes
 *
 */
public class ServerGameLogic extends GameLogic {

	private GameServer gs;

	/**
	 * @param app
	 */
	public ServerGameLogic(App app, GameServer gs) {
		super(app);
		this.gs= gs;
		player = (Player) level.getPlayer();
		for(Entry<Socket, State> entry: gs.getSockets().entrySet()){
			
		}
		this.level.addGameElement(new NetworkPlayer(player.position, player.size, player.id));
	}

}
