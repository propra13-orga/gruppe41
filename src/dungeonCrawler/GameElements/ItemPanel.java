package dungeonCrawler.GameElements;

import java.awt.Color;
import java.awt.Graphics;

import dungeonCrawler.GameElement;
import dungeonCrawler.GameEvent;
import dungeonCrawler.GameLogic;
import dungeonCrawler.Vector2d;
import dungeonCrawler.GameObjects.HealthPotion;
import dungeonCrawler.GameObjects.ManaPotion;

public class ItemPanel extends GameElement {
	private GameLogic logic;

	public ItemPanel(Vector2d position, Vector2d size, GameLogic l) {
		super(position, size);
		this.logic = l;
	}

	@Override
	public void draw(Graphics g) {
		// draw items
		for (int i=0;i<logic.getinventory().size();i++) {
			g.setColor(Color.GRAY);
			if(logic.getinventory().get(i) instanceof HealthPotion)
				g.setColor(Color.RED);
			if(logic.getinventory().get(i) instanceof ManaPotion)
				g.setColor(Color.BLUE);
			g.fillRect(10, 20*i+10, 10, 10);
		}
		// draw bounds
		for (int i=0;i<logic.getinventory().size();i++) {
			g.setColor(Color.BLACK);
			g.drawRect(10, 20*i+10, 10, 10);
		}

	}

	@Override
	public void GameEventPerformed(GameEvent e) {
		// TODO Auto-generated method stub

	}

}
