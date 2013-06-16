package dungeonCrawler.GameElements;

import java.awt.Color;
import java.awt.Graphics;
import java.util.EnumSet;

import dungeonCrawler.ElementType;
import dungeonCrawler.GameElement;
import dungeonCrawler.GameEvent;
import dungeonCrawler.Vector2d;

/**
 * @author Tissen
 *
 */
public class Item extends GameElement {
	private String name;
	private int health;
	private int mana;
	private int damage;
	private int armor;

	/**
	 * @param position
	 * @param size
	 */
	public Item(Vector2d position, Vector2d size, String n, int h, int m, int d, int a) {
		super(position, size, "ITEM", EnumSet.of(ElementType.IMMOVABLE, ElementType.WALKABLE));
		this.name = n;
		this.health = h;
		this.mana = m;
		this.damage = d;
		this.armor = a;
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
		
	}

}
