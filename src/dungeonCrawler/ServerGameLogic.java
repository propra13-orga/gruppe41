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
		gs.broadcastMessage("/delete " + app.currentLevel);
		for(GameElement e:level.getGameElements()){
			if(!(e instanceof Player)){
				gs.broadcastMessage("/line " + app.currentLevel + ";" + e.getString());
			}
		}
		player = (Player) level.getPlayer();
		for(Entry<Socket, State> entry: gs.getSockets().entrySet()){
			entry.getValue().setPlayerID(app.gameContent.getNextFreeID());
			gs.send(entry.getKey(),"/line " + app.currentLevel + ";" + (new Player(player.position, player.size, entry.getValue().getPlayerID())).getString());
			NetworkPlayer tmp = new NetworkPlayer(player.position, player.size, entry.getValue().getPlayerID());
			gs.sendWithout(entry.getKey(), "/line " + app.currentLevel + ";" + tmp.getString());
			this.level.addGameElement(tmp);
		}
		
		
		gs.broadcastMessage("/start");
	}

}
