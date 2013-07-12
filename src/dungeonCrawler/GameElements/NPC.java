package dungeonCrawler.GameElements;

import java.awt.Color;
import java.awt.Graphics;
import java.util.EnumSet;

import dungeonCrawler.ElementType;
import dungeonCrawler.EventType;
import dungeonCrawler.GameElement;
import dungeonCrawler.GameEvent;
import dungeonCrawler.Vector2d;

/**
 * @author Tissen
 *
 */
public class NPC extends GameElement {

	/**
	 * @param position
	 * @param size
	 */
	public NPC(Vector2d position, Vector2d size) {
		super(position, size);
		this.type = EnumSet.of(ElementType.MOVABLE);
	}

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(Color.GREEN);
		g.fillRect(0, 0, size.getX(), size.getY());
	}

	@Override
	public void GameEventPerformed(GameEvent e) {
		// TODO Auto-generated method stub
		if (e.type == EventType.TIMER) {
			Vector2d target = e.gameLogic.getLevel().getPlayer().getPosition();
			if (Math.abs(target.getX() - this.position.getX()) <= 150 && Math.abs(target.getY() - this.position.getY()) <= 150) {
				int x = (int) (Math.random() * 4)-2;
				int y = (int) (Math.random() * 4)-2;
				if (target.getX() < this.position.getX())
					x -= 1;
				if (target.getX() > this.position.getX())
					x += 1;
				if (target.getY() < this.position.getY())
					y += -1;
				if (target.getY() > this.position.getY())
					y += 1;
				if (x < -1)
					x = -1;
				if (x > 1)
					x = 1;
				if (y < -1)
					y = -1;
				if (y > 1)
					y = 1;
				if(Math.random()<0.5){
					x=0;y=0;
				}
//				System.out.println(x + ":" + y);
				Vector2d direction = new Vector2d(x, y);
				e.gameLogic.moveElement(this, direction);
			}
		}
	}

	public static NPC createElement(String[] param) {
		Vector2d position = new Vector2d();
		Vector2d size = new Vector2d();
		try {
			position.setX(Integer.parseInt(param[1]));
			position.setY(Integer.parseInt(param[2]));
			size.setX(Integer.parseInt(param[3]));
			size.setY(Integer.parseInt(param[4]));
		} catch (NumberFormatException e) {
			System.out.println("Kann NPC-Parameter nicht interpretieren.");
		}
		return (new NPC(position, size));
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "NPC";
	}

}
