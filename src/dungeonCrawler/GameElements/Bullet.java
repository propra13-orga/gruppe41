package dungeonCrawler.GameElements;

import java.awt.Color;
import java.awt.Graphics;
import java.util.EnumSet;

import dungeonCrawler.DamageType;
import dungeonCrawler.ElementType;
import dungeonCrawler.EventType;
import dungeonCrawler.GameElement;
import dungeonCrawler.GameElementImage;
import dungeonCrawler.GameEvent;
import dungeonCrawler.LevelLoader;
import dungeonCrawler.Vector2d;

public class Bullet extends GameElement {
	static Bullet element;
	GameElementImage gei = new GameElementImage();
	private int life = 300;
	private Vector2d direction = new Vector2d(0,0);

	public Bullet(Vector2d position, Vector2d size) {
		super(position, size, -1);
		this.type = EnumSet.of(ElementType.MOVABLE, ElementType.WALKABLE);
		// TODO Auto-generated constructor stub
	}

	public Bullet(Vector2d position, Vector2d size, int id) {
		super(position, size, id);
		this.type = EnumSet.of(ElementType.MOVABLE, ElementType.WALKABLE);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(Color.BLACK);
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
				element.reduceHealth(10, DamageType.CONVENTIONAL, e.gameLogic);
			}
			if(e.element instanceof Enemy){
				Enemy element = (Enemy) e.element;
				element.reduceHealth(10, DamageType.CONVENTIONAL, e.gameLogic);
			}
			if(e.element instanceof EndBoss){
				EndBoss element = (EndBoss) e.element;
				element.reduceHealth(20, DamageType.CONVENTIONAL, e.gameLogic);
			}
			if(!(e.element instanceof Bullet)){
				this.size = new Vector2d(0,0);
			}
		}
	}

	public Vector2d getDirection() {
		return direction;
	}

	public void setDirection(Vector2d direction) {
		this.direction = direction;
	}

	public int getLife() {
		return life;
	}

	public void setLife(int life) {
		this.life = life;
	}

	/**Creates new instance of this class.
	 * @param param parameters of this GameElement as {@link String[]}
	 * @param id as {@link int}
	 * @return a {@link Bullet} instance
	 */
	public static Bullet createElement(String[] param, int id) {
		if (param.length > 7) {
			element = new Bullet(new Vector2d(), new Vector2d(), Integer.parseInt(param[1]));
		}
		else {
			element = new Bullet(new Vector2d(), new Vector2d(), id);
		}
		element.modify(param);
		return element;
	}

	/**Modifies parameters.
	 * @param param as {@link String[]}
	 */
	public void modify(String[] param) {
		Vector2d position = new Vector2d();
		Vector2d size = new Vector2d();
		Vector2d direction = new Vector2d();
		try {
			int i = (param.length > 7 ? 1 : 0);
			position.setX(Integer.parseInt(param[i+1]));
			position.setY(Integer.parseInt(param[i+2]));
			size.setX(Integer.parseInt(param[i+3]));
			size.setY(Integer.parseInt(param[i+4]));
			direction.setX(Integer.parseInt(param[i+5]));
			direction.setY(Integer.parseInt(param[i+6]));
			element.setPosition(position);
			element.setSize(size);
			element.setDirection(direction);
			element.gei.setSize(size);
		} catch (NumberFormatException e) {
			System.out.println("Kann BULLET-Parameter nicht interpretieren.");
			element = null;
		}
	}

	/**Gets a parameter string.
	 * @see dungeonCrawler.GameElement#getString()
	 */
	@Override
	public String getString() {
		String sep = LevelLoader.getSplitChar();
		return (getName() + sep + id + sep +
				position.getX() + sep + position.getY() + sep +
				size.getX() + sep + size.getY() + sep +
				direction.getX() + sep + direction.getY());
	}

	@Override
	public String getName() {
		return "BULLET";
	}

}
