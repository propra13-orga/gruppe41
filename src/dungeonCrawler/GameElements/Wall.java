package dungeonCrawler.GameElements;

import java.awt.Graphics;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

//import dungeonCrawler.ElementType;
import dungeonCrawler.GameElement;
import dungeonCrawler.GameElementImage;
import dungeonCrawler.GameEvent;
import dungeonCrawler.LevelLoader;
import dungeonCrawler.Vector2d;

/**This class reperesents a wall.
 * @author Mattes, Tissen
 */
public class Wall extends GameElement {
	static Wall element;
	GameElementImage gei = new GameElementImage();

	/**Constructor.
	 * @param position of this GameElement as {@link Vector2d}
	 * @param size of this GameElement as {@link Vector2d}
	 */
	@Deprecated
	public Wall(Vector2d position, Vector2d size) {
		super(position, size, -1);
		gei.setSize(getSize());
		try {
			gei.setImage(ImageIO.read(new File("Graphics" + File.separator + "Wall.png")));
		} catch (IOException e) {
			gei.setImage(null);
			e.printStackTrace();
		}
	}

	/**Constructor.
	 * @param position of this GameElement as {@link Vector2d}
	 * @param size of this GameElement as {@link Vector2d}
	 */
	public Wall(Vector2d position, Vector2d size, int id) {
		super(position, size, id);
		gei.setSize(getSize());
		try {
			gei.setImage(ImageIO.read(new File("Graphics" + File.separator + "Wall.png")));
		} catch (IOException e) {
			gei.setImage(null);
			e.printStackTrace();
		}
	}

	/**
	 * @see dungeonCrawler.Drawable#draw()
	 * @param g as {@link Graphics}
	 */
	@Override
	public void draw(Graphics g) {
		gei.paintComponent(g);
	}

	@Override
	public void GameEventPerformed(GameEvent e) {
	}

	/**Creates new instance of this class.
	 * @param param parameters of this GameElement as {@link String[]}
	 * @param id as {@link int}
	 * @return a {@link Wall} instance
	 */
	public static Wall createElement(String[] param, int id) {
		if (param.length > 5) {
			element = new Wall(new Vector2d(), new Vector2d(), Integer.parseInt(param[1]));
		}
		else {
			element = new Wall(new Vector2d(), new Vector2d(), id);
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
			System.out.println("Kann WALL-Parameter nicht interpretieren.");
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

	/**Gets name of this GameElement.
	 * @see dungeonCrawler.GameElement#getName()
	 */
	@Override
	public String getName() {
		return "WALL";
	}

}
