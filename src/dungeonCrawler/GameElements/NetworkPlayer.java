package dungeonCrawler.GameElements;

import java.awt.Color;
import java.awt.Graphics;

import dungeonCrawler.GameElement;
import dungeonCrawler.GameEvent;
import dungeonCrawler.Vector2d;

public class NetworkPlayer extends GameElement {

	public NetworkPlayer(Vector2d position, Vector2d size) {
		super(position, size);
		// TODO Auto-generated constructor stub
	}

	public NetworkPlayer(Vector2d position, Vector2d size, int id) {
		super(position, size, id);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect(0, 0, size.getX(), size.getY());

	}

	@Override
	public void GameEventPerformed(GameEvent e) {
		// TODO Auto-generated method stub

	}

}
