package dungeonCrawler.GameElements;

import java.awt.Color;
import java.awt.Graphics;
import java.util.EnumSet;

import dungeonCrawler.ElementType;
import dungeonCrawler.EventType;
import dungeonCrawler.GameElement;
import dungeonCrawler.GameEvent;
import dungeonCrawler.Vector2d;

public class WarpPoint extends GameElement {
	
	private Vector2d target = new Vector2d(0,0);

	public WarpPoint(Vector2d position, Vector2d size, Vector2d target) {
		super(position, size);
		this.target = target;
		this.type = EnumSet.of(ElementType.MOVABLE, ElementType.WALKABLE);
	}

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(Color.CYAN);
		g.fillRect(0, 0, size.getX(), size.getY());

	}

	@Override
	public void GameEventPerformed(GameEvent e) {
		// TODO Auto-generated method stub
		if(e.element instanceof Player && e.type == EventType.COLLISION){
			e.gameLogic.teleportElement(e.element, target);
		}
	}

	public Vector2d getTarget() {
		return target;
	}

	public void setTarget(Vector2d target) {
		this.target = target;
	}

	public static WarpPoint createElement(String[] param) {
		Vector2d position = new Vector2d();
		Vector2d size = new Vector2d();
		Vector2d target = new Vector2d();
		try {
			position.setX(Integer.parseInt(param[1]));
			position.setY(Integer.parseInt(param[2]));
			size.setX(Integer.parseInt(param[3]));
			size.setY(Integer.parseInt(param[4]));
			target.setX(Integer.parseInt(param[5]));
			target.setY(Integer.parseInt(param[6]));
		} catch (NumberFormatException e) {
			System.out.println("Kann WARPPOINT-Parameter nicht interpretieren.");
		}
		return (new WarpPoint(position, size, target));
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "WARPPOINT";
	}

}
