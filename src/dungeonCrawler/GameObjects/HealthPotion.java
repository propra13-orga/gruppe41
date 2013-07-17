package dungeonCrawler.GameObjects;

import java.awt.Graphics;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import dungeonCrawler.GameElement;
import dungeonCrawler.GameObject;
import dungeonCrawler.Vector2d;
import dungeonCrawler.GameElements.Enemy;
import dungeonCrawler.GameElements.Player;

/**Adds some health
 * @author Tissen
 *
 */
public class HealthPotion extends GameObject {
	private int health;

	public HealthPotion(int h) {
		this.health = h;
		gei.setSize(new Vector2d(10,10));
		try {
			gei.setImage(ImageIO.read(new File("Graphics" + File.separator + "Healthpot.png")));
		} catch (IOException e) {
			gei.setImage(null);
			e.printStackTrace();
		}
	}
	
	/**Drink it
	 * @see dungeonCrawler.GameObject#performOn(dungeonCrawler.GameElement)
	 */
	@Override
	public void performOn(GameElement element) {
		String name = element.getName();
		switch (name) {
		case "PLAYER": ((Player)element).increaseHealth(health); break;
		case "ENEMY": ((Enemy)element).increaseHealth(health); break;
		}
	}
	
	public int getValue() {
		return health;
	}

	@Override
	public void draw(Graphics g) {
		gei.paintComponent(g);
	}

}
