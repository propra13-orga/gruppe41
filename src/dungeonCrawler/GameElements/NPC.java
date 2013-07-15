package dungeonCrawler.GameElements;

import java.awt.Color;
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
public class NPC extends GameElement {
	static NPC element;
	GameElementImage gei = new GameElementImage();

	/**
	 * @param position
	 * @param size
	 */
	@Deprecated
	public NPC(Vector2d position, Vector2d size) {
		super(position, size, -1);
		this.type = EnumSet.of(ElementType.MOVABLE);
		gei.setSize(getSize());
		try {
			gei.setImage(ImageIO.read(new File("Graphics" + File.separator + "NPC.png")));
		} catch (IOException e) {
			gei.setImage(null);
			e.printStackTrace();
		}
	}

	/**
	 * @param position
	 * @param size
	 */
	public NPC(Vector2d position, Vector2d size, int id) {
		super(position, size, id);
		this.type = EnumSet.of(ElementType.MOVABLE);
		gei.setSize(getSize());
		try {
			gei.setImage(ImageIO.read(new File("Graphics" + File.separator + "NPC.png")));
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
		// TODO Auto-generated method stub
		if (e.type == EventType.TIMER) {
			Vector2d target = e.gameLogic.getLevel().getPlayer().getPosition();
			if (Math.abs(target.getX() - this.position.getX()) <= 150 && Math.abs(target.getY() - this.position.getY()) <= 150) {
				int x = (int) (Math.random() * 4)-2;
				int y = (int) (Math.random() * 4)-2;
				if (target.getX() < this.position.getX())
					x -= 1;
				if (target.getX() > this.position.getX())
					x += 1;
				if (target.getY() < this.position.getY())
					y += -1;
				if (target.getY() > this.position.getY())
					y += 1;
				if (x < -1)
					x = -1;
				if (x > 1)
					x = 1;
				if (y < -1)
					y = -1;
				if (y > 1)
					y = 1;
				if(Math.random()<0.5){
					x=0;y=0;
				}
				//				System.out.println(x + ":" + y);
				Vector2d direction = new Vector2d(x, y);
				e.gameLogic.moveElement(this, direction);
			}
		}
	}

	/**Creates new instance of this class.
	 * @param param parameters of this GameElement as {@link String[]}
	 * @param id as {@link int}
	 * @return a {@link NPC} instance
	 */
	public static NPC createElement(String[] param, int id) {
		if (param.length > 5) {
			element = new NPC(new Vector2d(), new Vector2d(), Integer.parseInt(param[1]));
		}
		else {
			element = new NPC(new Vector2d(), new Vector2d(), id);
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
			System.out.println("Kann NPC-Parameter nicht interpretieren.");
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
		// TODO Auto-generated method stub
		return "NPC";
	}

}
