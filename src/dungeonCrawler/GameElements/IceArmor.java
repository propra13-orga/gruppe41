package dungeonCrawler.GameElements;

import java.awt.Graphics;
import java.io.File;
import java.io.IOException;
import java.util.EnumSet;

import javax.imageio.ImageIO;

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
public class IceArmor extends GameElement {
	static IceArmor element;
	GameElementImage gei = new GameElementImage();

	/**
	 * @param position
	 * @param size
	 */
	@Deprecated
	public IceArmor(Vector2d position, Vector2d size) {
		super(position, size, -1);
		this.type = EnumSet.of(ElementType.IMMOVABLE, ElementType.WALKABLE);
		gei.setSize(getSize());
		try {
			gei.setImage(ImageIO.read(new File("Graphics" + File.separator + "IceArmor.png")));
		} catch (IOException e) {
			gei.setImage(null);
			e.printStackTrace();
		}
	}

	/**
	 * @param position
	 * @param size
	 */
	public IceArmor(Vector2d position, Vector2d size, int id) {
		super(position, size, id);
		this.type = EnumSet.of(ElementType.IMMOVABLE, ElementType.WALKABLE);
		gei.setSize(getSize());
		try {
			gei.setImage(ImageIO.read(new File("Graphics" + File.separator + "IceArmor.png")));
		} catch (IOException e) {
			gei.setImage(null);
			e.printStackTrace();
		}
	}

	@Override
	public void draw(Graphics g) {
		gei.paintComponent(g);
	}

	@Override
	public void GameEventPerformed(GameEvent e) {
		if(e.element instanceof Player && e.type == EventType.COLLISION){
			System.out.println("Eisrüstung aufgenommen");
			Player elementPlayer = (Player) e.element;
			elementPlayer.addItem(new dungeonCrawler.GameObjects.IceArmor(5));
			this.size.setX(0);
			this.size.setY(0);
		}
	}

	/**Creates new instance of this class.
	 * @param param parameters of this GameElement as {@link String[]}
	 * @param id as {@link int}
	 * @return a {@link IceArmor} instance
	 */
	public static IceArmor createElement(String[] param, int id) {
		if (param.length > 5) {
			element = new IceArmor(new Vector2d(), new Vector2d(), Integer.parseInt(param[1]));
		}
		else {
			element = new IceArmor(new Vector2d(), new Vector2d(), id);
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
			System.out.println("Kann ICEARMOR-Parameter nicht interpretieren.");
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
		return "ICEARMOR";
	}

}
