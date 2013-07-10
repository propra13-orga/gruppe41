package dungeonCrawler.GameElements;

import java.awt.Color;
import java.awt.Graphics;
import java.util.EnumSet;

import dungeonCrawler.DamageType;
import dungeonCrawler.ElementType;
import dungeonCrawler.EventType;
import dungeonCrawler.GameElement;
import dungeonCrawler.GameEvent;
import dungeonCrawler.Vector2d;

public class IceBolt extends GameElement {
	private int life = 600;
	private Vector2d direction = new Vector2d(0,0);
	private int damage = 30;

	public IceBolt(Vector2d position, Vector2d size) {
		super(position, size);
		this.type = EnumSet.of(ElementType.MOVABLE, ElementType.WALKABLE);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(Color.BLUE);
		g.fillRect(0, 0, size.getX(), size.getY());

	}

	@Override
	public void GameEventPerformed(GameEvent e) {
		// TODO Auto-generated method stub
		if(e.type == EventType.TIMER){
			life--;
			e.gameLogic.moveElement(this, direction);
			if(life<0)
				this.size = new Vector2d(0,0);
		}
		if(e.type == EventType.COLLISION && !e.element.type.contains(ElementType.WALKABLE)){
			if (e.element instanceof Player) {
				Player element = (Player) e.element;
				element.reduceHealth(damage, DamageType.ICE, e.gameLogic);
				this.size = new Vector2d(0,0);
			}
			if(e.element instanceof Enemy){
				Enemy element = (Enemy) e.element;
				element.reduceHealth(damage, DamageType.ICE, e.gameLogic);
				this.size = new Vector2d(0,0);
			}
			this.direction = this.direction.mul(-1);
		}

	}

	public Vector2d getDirection() {
		return direction;
	}

	public void setDirection(Vector2d direction) {
		this.direction = direction;
	}

	public static IceBolt createElement(String[] param) {
		Vector2d position = new Vector2d();
		Vector2d size = new Vector2d();
		try {
			position.setX(Integer.parseInt(param[1]));
			position.setY(Integer.parseInt(param[2]));
			size.setX(Integer.parseInt(param[3]));
			size.setY(Integer.parseInt(param[4]));
		} catch (NumberFormatException e) {
			System.out.println("Kann ICEBOLT-Parameter nicht interpretieren.");
		}
		return (new IceBolt(position, size));
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Spell";
	}

}
