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

/**
 * @author Tissen
 *
 */
public class ConvArmor extends GameElement {
	static ConvArmor element;
	GameElementImage gei = new GameElementImage();

	/**
	 * @param position
	 * @param size
	 */
	@Deprecated
	public ConvArmor(Vector2d position, Vector2d size) {
		super(position, size, -1);
		this.type = EnumSet.of(ElementType.IMMOVABLE, ElementType.WALKABLE);
	}

	/**
	 * @param position
	 * @param size
	 */
	public ConvArmor(Vector2d position, Vector2d size, int id) {
		super(position, size, id);
		this.type = EnumSet.of(ElementType.IMMOVABLE, ElementType.WALKABLE);
	}

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(Color.GRAY);
		g.fillRect(0, 0, size.getX(), size.getY());
	}

	@Override
	public void GameEventPerformed(GameEvent e) {
		// TODO Auto-generated method stub
		if(e.element instanceof Player && e.type == EventType.COLLISION){
			System.out.println("Rüstung aufgenommen");
			Player elementPlayer = (Player) e.element;
			elementPlayer.addItem(new dungeonCrawler.GameObjects.ConvArmor(5));
			this.size.setX(0);
			this.size.setY(0);
		}
	}

	/**Creates new instance of this class.
	 * @param param parameters of this GameElement as {@link String[]}
	 * @param id as {@link int}
	 * @return a {@link ConvArmor} instance
	 */
	public static ConvArmor createElement(String[] param, int id) {
			if (param.length > 5) {
				element = new ConvArmor(new Vector2d(), new Vector2d(), Integer.parseInt(param[1]));
			}
			else {
				element = new ConvArmor(new Vector2d(), new Vector2d(), id);
			}
		modify(param);
		return element;
	}

	/**Modifies parameters.
	 * @param param as {@link String[]}
	 */
	private static void modify(String[] param) {
		Vector2d position = new Vector2d();
		Vector2d size = new Vector2d();
		try {
			int i = (param.length > 5 ? 1 : 0);
			position.setX(Integer.parseInt(param[i+1]));
			position.setY(Integer.parseInt(param[i+2]));
			size.setX(Integer.parseInt(param[i+3]));
			size.setY(Integer.parseInt(param[i+4]));
			element.setPosition(position);
			element.setSize(size);
			element.gei.setSize(size);
		} catch (NumberFormatException e) {
			System.out.println("Kann CONVARMOR-Parameter nicht interpretieren.");
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
				size.getX() + sep + size.getY());
	}

	@Override
	public String getName() {
		return "CONVARMOR";
	}

}
