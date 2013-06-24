package dungeonCrawler.GameElements;

import java.awt.Color;
import java.awt.Graphics;
import java.util.EnumSet;

import dungeonCrawler.ElementType;
import dungeonCrawler.GameElement;
import dungeonCrawler.GameEvent;
import dungeonCrawler.Vector2d;

/**
 * @author Mattes
 *
 */
public class Wall extends GameElement {

	/**
	 * @param position
	 * @param size
	 */
	public Wall(Vector2d position, Vector2d size) {
		super(position, size, "WALL", EnumSet.of(ElementType.IMMOVABLE));
	}

	/* (non-Javadoc)
	 * @see dungeonCrawler.Drawable#draw()
	 */
	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(Color.DARK_GRAY);
		g.fillRect(0, 0, size.getX(), size.getY());
	}

	@Override
	public void GameEventPerformed(GameEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public static Wall createWall(Vector2d pos, Vector2d s) {
		return (new Wall(pos, s));
	}

}
