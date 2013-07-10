/**
 * 
 */
package dungeonCrawler.GameObjects;

import dungeonCrawler.GameElement;
import dungeonCrawler.GameLogic;
import dungeonCrawler.GameObject;
import dungeonCrawler.Vector2d;
import dungeonCrawler.GameElements.Bullet;
import dungeonCrawler.GameElements.Player;

/**
 * @author Mattes
 *
 */
public class Bow extends GameObject {

	/**
	 * 
	 */
	public Bow() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see dungeonCrawler.GameObject#performOn(dungeonCrawler.GameElement)
	 */
	@Override
	public void performOn(GameElement element) {
		// TODO Auto-generated method stub

	}

	@Override
	public void performOn(GameElement element, GameLogic logic) {
		// TODO Auto-generated method stub
		Player player = (Player)element;
		Vector2d lastDirection = player.last_direction;
		Vector2d position = player.getPosition();
		Vector2d pos = new Vector2d(position.add(player.getSize().mul(0.5)).add(new Vector2d(-5, -5)));
		if(lastDirection.getX() > 0)
			pos = pos.add(new Vector2d(player.getSize().getX()-2,0));
		if(lastDirection.getX() < 0)
			pos = pos.add(new Vector2d(-player.getSize().getX()+2,0));
		if(lastDirection.getY() > 0)
			pos = pos.add(new Vector2d(0,player.getSize().getX()-2));
		if(lastDirection.getY() < 0)
			pos = pos.add(new Vector2d(0,-player.getSize().getX()+2));
		Bullet tmp = new Bullet(pos, new Vector2d(10, 10));
		tmp.setDirection(lastDirection.mul(3));
		logic.addGameElement(tmp);
	}

}
