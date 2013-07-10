package dungeonCrawler.GameElements;

import java.awt.Color;
import java.awt.Graphics;
import java.util.EnumSet;

import dungeonCrawler.DamageType;
import dungeonCrawler.ElementType;
import dungeonCrawler.EventType;
import dungeonCrawler.GameElement;
import dungeonCrawler.GameEvent;
//import dungeonCrawler.GameLogic;
import dungeonCrawler.Vector2d;

/**
 * @author Dominik
 *
 */
public class FireFox extends GameElement {
	/**
	 * @param position
	 * @param size
	 */
	public FireFox(Vector2d position, Vector2d size) {
		super(position, size);
		this.type = EnumSet.of(ElementType.MOVABLE);
	}

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(Color.RED);
		g.fillRect(0, 0, size.getX(), size.getY());
	}
	
	public void setPosition(Vector2d pos) {
		this.position = pos;
	}

	@Override
	public void GameEventPerformed(GameEvent e) {
		// TODO Auto-generated method stub
		if(e.element instanceof Player && e.type == EventType.COLLISION){
			System.out.println("autsch!");
			Player elementPlayer = (Player) e.element;
			elementPlayer.reduceHealth(5, DamageType.FIRE, e.gameLogic);
		}
		if(e.type == EventType.TIMER){
			e.gameLogic.moveElement(this, new Vector2d((int)(Math.random()*4-2),(int)(Math.random()*4-2)));
		}
	}

	public static FireFox createElement(String[] param) {
		Vector2d position = new Vector2d();
		Vector2d size = new Vector2d();
		try {
			position.setX(Integer.parseInt(param[1]));
			position.setY(Integer.parseInt(param[2]));
			size.setX(Integer.parseInt(param[3]));
			size.setY(Integer.parseInt(param[4]));
		} catch (NumberFormatException e) {
			System.out.println("Kann FIREFOX-Parameter nicht interpretieren.");
		}
		return (new FireFox(position, size));
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Fireball";
	}	
	
}
