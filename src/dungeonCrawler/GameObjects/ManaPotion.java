package dungeonCrawler.GameObjects;

import java.awt.Graphics;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import dungeonCrawler.GameElement;
import dungeonCrawler.GameObject;
import dungeonCrawler.Vector2d;
import dungeonCrawler.GameElements.Player;

/**Adds some mana
 * @author Tissen
 *
 */
public class ManaPotion extends GameObject {
	private int mana;

	public ManaPotion(int m) {
		this.mana = m;
		gei.setSize(new Vector2d(10,10));
		try {
			gei.setImage(ImageIO.read(new File("Graphics" + File.separator + "Manapot.png")));
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
		case "PLAYER": ((Player)element).increaseMana(mana); break;
		}

	}
	
	public int getValue() {
		return mana;
	}

	@Override
	public void draw(Graphics g) {
		gei.paintComponent(g);
	}

}
