package dungeonCrawler.GameObjects;

import java.awt.Color;
import java.awt.Graphics;

import dungeonCrawler.GameElement;
import dungeonCrawler.GameObject;
import dungeonCrawler.GameElements.Enemy;
import dungeonCrawler.GameElements.Player;

/**
 * @author Tissen
 *
 */
public class HealthPotion extends GameObject {
	private int health;

	public HealthPotion(int h) {
		this.health = h;
	}
	
	@Override
	public void performOn(GameElement element) {
		// TODO Auto-generated method stub
		String name = element.getName();
		switch (name) {
		case "PLAYER": ((Player)element).increaseHealth(health); break;
		case "ENEMY": ((Enemy)element).increaseHealth(health); break;
		}
	}
	
	public int getHealth() {
		return health;
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(Color.RED);
		super.draw(g);
	}

}
