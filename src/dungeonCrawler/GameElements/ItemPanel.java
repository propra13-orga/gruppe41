package dungeonCrawler.GameElements;

import java.awt.Color;
import java.awt.Graphics;

import dungeonCrawler.GameElement;
import dungeonCrawler.GameEvent;
import dungeonCrawler.Vector2d;

public class ItemPanel extends GameElement {
	private Player player;

	public ItemPanel(Vector2d position, Vector2d size, GameElement player) {
		super(position, size);
		this.player = (Player) player;
	}

	@Override
	public void draw(Graphics g) {
		// draw items
		for (int i=0;i<player.getInventar().size();i++) {
			g.setColor(Color.GRAY);
			g.fillRect(10, 20*i+10, 10, 10);
		}
		// draw bounds
		for (int i=0;i<player.getInventar().size();i++) {
			g.setColor(Color.BLACK);
			g.drawRect(10, 20*i+10, 10, 10);
		}

	}

	@Override
	public void GameEventPerformed(GameEvent e) {
		// TODO Auto-generated method stub

	}

}
