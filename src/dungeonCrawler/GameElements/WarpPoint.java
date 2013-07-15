package dungeonCrawler.GameElements;

import java.awt.Color;
import java.awt.Graphics;
import java.util.EnumSet;

import dungeonCrawler.ElementType;
import dungeonCrawler.EventType;
import dungeonCrawler.GameElement;
import dungeonCrawler.GameElementImage;
import dungeonCrawler.GameEvent;
import dungeonCrawler.LevelLoader;
import dungeonCrawler.Vector2d;

public class WarpPoint extends GameElement {
	static WarpPoint element;
	GameElementImage gei = new GameElementImage();
	private Vector2d target = new Vector2d();

	@Deprecated
	public WarpPoint(Vector2d position, Vector2d size, Vector2d target) {
		super(position, size, -1);
		this.target = target;
		this.type = EnumSet.of(ElementType.MOVABLE, ElementType.WALKABLE);
	}

	public WarpPoint(Vector2d position, Vector2d size, int id, Vector2d target) {
		super(position, size, id);
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

	/**Creates new instance of this class.
	 * @param param parameters of this GameElement as {@link String[]}
	 * @param id as {@link int}
	 * @return a {@link WarpPoint} instance
	 */
	public static WarpPoint createElement(String[] param, int id) {
		if (param.length > 7) {
			element = new WarpPoint(new Vector2d(), new Vector2d(), Integer.parseInt(param[1]), new Vector2d());
		}
		else {
			element = new WarpPoint(new Vector2d(), new Vector2d(), id, new Vector2d());
		}
		element.modify(param);
		return element;
	}

	/**Modifies parameters.
	 * @param param as {@link String[]}
	 */
	private void modify(String[] param) {
		Vector2d position = new Vector2d();
		Vector2d size = new Vector2d();
		Vector2d target = new Vector2d();
		try {
			int i = (param.length > 7 ? 1 : 0);
			position.setX(Integer.parseInt(param[i+1]));
			position.setY(Integer.parseInt(param[i+2]));
			size.setX(Integer.parseInt(param[i+3]));
			size.setY(Integer.parseInt(param[i+4]));
			target.setX(Integer.parseInt(param[i+5]));
			target.setY(Integer.parseInt(param[i+6]));
			element.setPosition(position);
			element.setSize(size);
			element.setTarget(target);
			element.gei.setSize(size);
		} catch (NumberFormatException e) {
			System.out.println("Kann WARPPOINT-Parameter nicht interpretieren.");
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
				target.getX() + sep + target.getY());
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "WARPPOINT";
	}

}
