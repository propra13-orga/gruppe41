package dungeonCrawler.GameObjects;

import dungeonCrawler.GameElement;
import dungeonCrawler.GameObject;
//import dungeonCrawler.GameElements.Enemy;
import dungeonCrawler.GameElements.Player;

/**
 * @author Tissen
 *
 */
public class ConvArmor extends GameObject {
	private int armor;

	public ConvArmor(int a) {
		this.armor = a;
	}

	@Override
	public void performOn(GameElement element) {
		String name = element.getName();
		System.out.println("Konventionelle Rüstung gewählt.");
		switch (name) {
		case "PLAYER": ((Player)element).setArmor(this); break;
		}
	}
	
	public int getArmor() {
		return armor;
	}
	
}