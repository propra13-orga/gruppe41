package dungeonCrawler.GameObjects;

import dungeonCrawler.GameElement;
import dungeonCrawler.GameObject;

public class HealthPotion extends GameObject {
	private int health;

	public HealthPotion(int h) {
		this.health = h;
	}
	
	@Override
	public void performOn(GameElement element) {
		// TODO Auto-generated method stub

	}

}