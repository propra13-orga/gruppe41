package dungeonCrawler.GameElements;

import java.awt.Graphics;

import dungeonCrawler.GameElement;
import dungeonCrawler.GameEvent;
import dungeonCrawler.Vector2d;

/**Item panel
 * @author Tissen
 *
 */
public class ItemPanel extends GameElement {
	private GameElement player;

	public ItemPanel(Vector2d position, Vector2d size, GameElement player) {
		super(position, size);
		this.player = player;
	}

	@Override
	public void draw(Graphics g) {
		// draw items
		Graphics gr;
		for (int i=0;i<((Player) player).getInventar().size();i++) {
			gr = g.create(10, 20*i+10, 10, 10);
			((Player) player).getInventar().get(i).draw(gr);
			gr.dispose();
			//g.setColor(Color.GRAY);
		/*	if (((Player) player).getInventar().get(i) instanceof HealthPotion)
				g.setColor(Color.RED);
			if (((Player) player).getInventar().get(i) instanceof ManaPotion)
				g.setColor(Color.BLUE);
			g.fillRect(10, 20*i+10, 10, 10);*/
		}
		// draw bounds
//		for (int i=0;i<((Player) player).getInventar().size();i++) {
//			g.setColor(Color.BLACK);
//			g.drawRect(10, 20*i+10, 10, 10);
//		}

	}

	@Override
	public void GameEventPerformed(GameEvent e) {
		// TODO Auto-generated method stub

	}

}
