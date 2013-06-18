package dungeonCrawler.GameObjects;

import dungeonCrawler.GameElement;
import dungeonCrawler.GameObject;
import dungeonCrawler.GameElements.Enemy;
import dungeonCrawler.GameElements.Player;

public class Armor extends GameObject {
	private int armor;

	public Armor(int a) {
		this.armor = a;
	}
	
	@Override
	public void performOn(GameElement element) {
		String name = element.getName();
		switch (name) {
		case "PLAYER": ((Player)element).increaseArmor(armor); break;
	}

}
}