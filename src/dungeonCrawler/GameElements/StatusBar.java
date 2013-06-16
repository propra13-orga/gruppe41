package dungeonCrawler.GameElements;

import java.awt.Color;
import java.awt.Graphics;

import dungeonCrawler.GameElement;
import dungeonCrawler.GameEvent;
import dungeonCrawler.Vector2d;

public class StatusBar extends GameElement {
	private int value;

	public StatusBar(Vector2d position, Vector2d size) {
		super(position, size);
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(0, 0, size.getX()*value/100, size.getY());
		g.setColor(Color.BLACK);
		g.drawRect(0, 0, size.getX()-1, size.getY()-1);
	}

	@Override
	public void GameEventPerformed(GameEvent e) {
	}
	
	public void setValue(int val) {
		this.value = val;
	}

}
