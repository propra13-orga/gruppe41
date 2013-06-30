package dungeonCrawler.GameElements;

import java.awt.Graphics;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

//import dungeonCrawler.ElementType;
import dungeonCrawler.GameElement;
import dungeonCrawler.GameElementImage;
import dungeonCrawler.GameEvent;
import dungeonCrawler.Vector2d;

/**
 * @author Mattes
 *
 */
public class Wall extends GameElement {
	GameElementImage gei = new GameElementImage();

	/**
	 * @param position
	 * @param size
	 */
	public Wall(Vector2d position, Vector2d size) {
		super(position, size);
		gei.setSize(getSize());
		try {
			gei.setImage(ImageIO.read(new File("Graphics" + File.separator + "Wall.png")));
		} catch (IOException e) {
			gei.setImage(null);
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see dungeonCrawler.Drawable#draw()
	 */
	@Override
	public void draw(Graphics g) {
		gei.paintComponent(g);
	}

	@Override
	public void GameEventPerformed(GameEvent e) {
		// TODO Auto-generated method stub

	}

	public static Wall createElement(String[] param) {
		Vector2d position = new Vector2d();
		Vector2d size = new Vector2d();
		try {
			position.setX(Integer.parseInt(param[1]));
			position.setY(Integer.parseInt(param[2]));
			size.setX(Integer.parseInt(param[3]));
			size.setY(Integer.parseInt(param[4]));
		} catch (NumberFormatException e) {
			System.out.println("Kann WALL-Parameter nicht interpretieren.");
			e.printStackTrace();
		}
		return (new Wall(position, size));
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Wall";
	}

}
