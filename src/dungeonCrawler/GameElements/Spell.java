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

public class Spell extends GameElement {
	static Spell element;
	GameElementImage gei = new GameElementImage();
	private int life = 600;
	private Vector2d direction = new Vector2d();
	private int damage = 50;

	public Spell(Vector2d position, Vector2d size) {
		super(position, size, -1);
		this.type = EnumSet.of(ElementType.MOVABLE, ElementType.WALKABLE);
	}

	public Spell(Vector2d position, Vector2d size, int id) {
		super(position, size, id);
		this.type = EnumSet.of(ElementType.MOVABLE, ElementType.WALKABLE);
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(Color.YELLOW);
		g.fillRect(0, 0, size.getX(), size.getY());

	}

	@Override
	public void GameEventPerformed(GameEvent e) {
		if(e.type == EventType.TIMER){
			life--;
			e.gameLogic.moveElement(this, direction);
			if(life<0)
				this.size = new Vector2d(0,0);
		}
		if(e.type == EventType.COLLISION && !e.element.type.contains(ElementType.WALKABLE)){
			if (e.element instanceof Player) {
				Player element = (Player) e.element;
				element.reduceHealth(damage, DamageType.CONVENTIONAL, e.gameLogic);
				this.size = new Vector2d(0,0);
			}
			if(e.element instanceof Enemy){
				Enemy element = (Enemy) e.element;
				element.reduceHealth(damage, DamageType.CONVENTIONAL, e.gameLogic);
				this.size = new Vector2d(0,0);
			}
			if(e.element instanceof FireFox){
				FireFox element = (FireFox) e.element;
				element.reduceHealth(damage, DamageType.CONVENTIONAL, e.gameLogic);
				this.size = new Vector2d(0,0);
			}
			if(e.element instanceof IceWeasel){
				IceWeasel element = (IceWeasel) e.element;
				element.reduceHealth(damage, DamageType.CONVENTIONAL, e.gameLogic);
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

	/**Creates new instance of this class.
	 * @param param parameters of this GameElement as {@link String[]}
	 * @param id as {@link int}
	 * @return a {@link Spell} instance
	 */
	public static Spell createElement(String[] param, int id) {
		if (param.length > 7) {
			element = new Spell(new Vector2d(), new Vector2d(), Integer.parseInt(param[1]));
		}
		else {
			element = new Spell(new Vector2d(), new Vector2d(), id);
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
			System.out.println("Kann SPELL-Parameter nicht interpretieren.");
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
		return "SPELL";
	}

}
