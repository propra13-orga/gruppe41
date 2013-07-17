package dungeonCrawler.GameObjects;

import java.awt.Graphics;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import dungeonCrawler.GameElement;
import dungeonCrawler.GameObject;
import dungeonCrawler.Vector2d;
//import dungeonCrawler.GameElements.Enemy;
import dungeonCrawler.GameElements.Player;

/**Fire resist armor
 * @author Tissen
 *
 */
public class FireArmor extends GameObject {
	private int armor;

	public FireArmor(int a) {
		this.armor = a;
		gei.setSize(new Vector2d(10,10));
		try {
			gei.setImage(ImageIO.read(new File("Graphics" + File.separator + "FireArmor.png")));
		} catch (IOException e) {
			gei.setImage(null);
			e.printStackTrace();
		}
	}

	/**Change current armor.
	 * @see dungeonCrawler.GameObject#performOn(dungeonCrawler.GameElement)
	 */
	@Override
	public void performOn(GameElement element) {
		String name = element.getName();
		System.out.println("Feuerüstung gewählt.");
		switch (name) {
		case "PLAYER": ((Player)element).setArmor(this); break;
		}
	}
	
	public int getArmor() {
		return armor;
	}
	
	@Override
	public void draw(Graphics g) {
		gei.paintComponent(g);
	}

}
