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
 * @author Dominik
 *
 */
public class Moneypot extends GameElement {

	/**
	 * @param position
	 * @param size
	 */
	public Moneypot(Vector2d position, Vector2d size) {
		super(position, size, "MONEYPOT", EnumSet.of(ElementType.IMMOVABLE, ElementType.WALKABLE));
	}

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(Color.YELLOW);
		g.fillRect(0, 0, size.getX(), size.getY());
	}

	@Override
	public void GameEventPerformed(GameEvent e) {
		// TODO Auto-generated method stub
		if(e.element instanceof Player && e.type == EventType.COLLISION){
			Player elementPlayer = (Player) e.element;
			elementPlayer.setMoney(elementPlayer.getMoney()+10);
			System.out.println("Mach mich reich, ich habe jetzt " + elementPlayer.getMoney() + " geld + 10");

			this.size.setX(0);this.size.setY(0);
		}
	}

}
