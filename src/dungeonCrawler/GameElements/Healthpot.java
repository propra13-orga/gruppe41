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
public class Healthpot extends GameElement {

	/**
	 * @param position
	 * @param size
	 */
	public Healthpot(Vector2d position, Vector2d size) {
		super(position, size);
		this.type = EnumSet.of(ElementType.IMMOVABLE, ElementType.WALKABLE);
	}

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(Color.MAGENTA);
		g.fillRect(0, 0, size.getX(), size.getY());
	}

	@Override
	public void GameEventPerformed(GameEvent e) {
		// TODO Auto-generated method stub
		if(e.element instanceof Player && e.type == EventType.COLLISION){
			System.out.println("Heil mich");
			Player elementPlayer = (Player) e.element;
			elementPlayer.increaseHealth(100);
			this.size.setX(0);this.size.setY(0);
		}
	}

	public static Healthpot createElement(String[] param) {
		Vector2d position = new Vector2d();
		Vector2d size = new Vector2d();
		try {
			position.setX(Integer.parseInt(param[1]));
			position.setY(Integer.parseInt(param[2]));
			size.setX(Integer.parseInt(param[3]));
			size.setY(Integer.parseInt(param[4]));
		} catch (NumberFormatException e) {
			System.out.println("Kann HEALTHPOT-Parameter nicht interpretieren.");
		}
		return (new Healthpot(position, size));
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "HEALTHPOT";
	}

}
