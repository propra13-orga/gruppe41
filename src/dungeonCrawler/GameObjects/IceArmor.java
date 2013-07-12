package dungeonCrawler.GameObjects;

import dungeonCrawler.GameElement;
import dungeonCrawler.GameObject;
//import dungeonCrawler.GameElements.Enemy;
import dungeonCrawler.GameElements.Player;

/**
 * @author Tissen
 *
 */
public class IceArmor extends GameObject {
	private int armor;

	public IceArmor(int a) {
		this.armor = a;
	}

	@Override
	public void performOn(GameElement element) {
		String name = element.getName();
		System.out.println("Eisrüstung gewählt.");
		switch (name) {
		case "PLAYER": ((Player)element).setArmor(this); break;
		}
	}
	
	public int getArmor() {
		return armor;
	}
	
}