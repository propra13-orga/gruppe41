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
		NetworkPlayer tmp1 = new NetworkPlayer(player.position, player.size, player.id);
		gs.broadcastMessage("/line " + app.currentLevel + ";" + tmp1.getString());
		for(Entry<Socket, State> entry: gs.getSockets().entrySet()){
			entry.getValue().setPlayerID(app.gameContent.getNextFreeID());
			gs.send(entry.getKey(),"/line " + app.currentLevel + ";" + (new Player(player.position, player.size, entry.getValue().getPlayerID())).getString());
			NetworkPlayer tmp = new NetworkPlayer(player.position, player.size, entry.getValue().getPlayerID());
			gs.sendWithout(entry.getKey(), "/line " + app.currentLevel + ";" + tmp.getString());
			this.level.addGameElement(tmp);
		}
		
		
		gs.broadcastMessage("/start");
	}
	
	public boolean moveElement(int id, Vector2d direction){
		GameElement tmp = level.find(id);
		if(tmp != null)
			return moveElement(tmp, direction);
		return false;
	}
	public boolean teleportElement(int id, Vector2d position){
		GameElement tmp = level.find(id);
		if(tmp != null)
			return teleportElement(tmp, position);
		return false;
	}

	@Override
	public boolean moveElement(GameElement e, Vector2d direction) {
		boolean ret = super.moveElement(e, direction);
		if(ret)
			this.gs.sendAll("/teleport " + e.id + ";" + e.position);
		return ret;
	}

	@Override
	public boolean teleportElement(GameElement e, Vector2d position) {
		boolean ret = super.teleportElement(e, position);
		if(ret)
			this.gs.sendAll("/teleport " + e.id + ";" + e.position);
		return ret;
	}

	@Override
	public void addGameElement(GameElement element) {
		super.addGameElement(element);
		System.out.println("/add " + element);
		gs.sendAll("/add " + element);
	}

}
