package dungeonCrawler.GameElements;

import java.awt.Color;
import java.awt.Graphics;

import dungeonCrawler.GameElement;
import dungeonCrawler.GameEvent;
import dungeonCrawler.Vector2d;

public class StatusBar extends GameElement {
	private int healthValue;
	private int manaValue;

	public StatusBar(Vector2d position, Vector2d size) {
		super(position, size);
	}

	@Override
	public void draw(Graphics g) {
		// health bar
		g.setColor(Color.RED);
		g.fillRect(30, 30, 200*healthValue/100, 15);
		g.setColor(Color.BLACK);
		g.drawRect(30, 30, 200, 15);
		// mana bar
		g.setColor(Color.BLUE);
		g.fillRect(270, 30, 200*manaValue/100, 15);
		g.setColor(Color.BLACK);
		g.drawRect(270, 30, 200, 15);
	}

	@Override
	public void GameEventPerformed(GameEvent e) {
	}
	
	public void setHealthValue(int val) {
		this.healthValue = val;
	}
	
	public void setManaValue(int val) {
		this.manaValue = val;
	}

}
