/**
 * 
 */
package dungeonCrawler.GameObjects;

import java.awt.Graphics;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

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

	/**Constructor
	 */
	public Bow() {
		gei.setSize(new Vector2d(10,10));
		try {
			gei.setImage(ImageIO.read(new File("Graphics" + File.separator + "Bow.png")));
		} catch (IOException e) {
			gei.setImage(null);
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see dungeonCrawler.GameObject#performOn(dungeonCrawler.GameElement)
	 */
	@Override
	public void performOn(GameElement element) {
		// TODO Auto-generated method stub

	}

	/**Shot.
	 * @see dungeonCrawler.GameObject#performOn(dungeonCrawler.GameElement, dungeonCrawler.GameLogic)
	 */
	@Override
	public void performOn(GameElement element, GameLogic logic) {
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

	@Override
	public void draw(Graphics g) {
		gei.paintComponent(g);
	}

}
