/**
 * 
 */
package dungeonCrawler;

import dungeonCrawler.GameElements.Bullet;
import dungeonCrawler.GameElements.NPC;
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
	
	public boolean teleportElement(int id, Vector2d position){
		GameElement tmp = level.find(id);
		if(tmp != null){
			tmp.position = position;
			return true;
		}
		return false;
	}

	@Override
	public boolean moveElement(GameElement e, Vector2d direction) {
		boolean ret = false;
		if(e instanceof Player)
			ret = super.moveElement(e, direction);
		if(ret && (e instanceof Player /*|| e instanceof Bullet || e instanceof dungeonCrawler.GameElements.Spell*/))
			this.client.send("/move " + e.id + ";" + direction);
		return ret;
	}

	@Override
	public boolean teleportElement(GameElement e, Vector2d position) {
		boolean ret = super.teleportElement(e, position);
		this.client.send("/teleport " + e.id + ";" + e.position);
		return ret;
	}

	@Override
	public void addGameElement(GameElement element) {
		super.addGameElement(element);
		System.out.println("/add " + element);
		client.send("/add " + element);
	}
}
