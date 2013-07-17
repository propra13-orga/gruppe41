package dungeonCrawler.GameElements;

import java.awt.Graphics;
import java.io.File;
import java.io.IOException;
import java.util.EnumSet;

import javax.imageio.ImageIO;

import dungeonCrawler.DamageType;
import dungeonCrawler.ElementType;
import dungeonCrawler.EventType;
import dungeonCrawler.GameElement;
import dungeonCrawler.GameElementImage;
import dungeonCrawler.GameEvent;
import dungeonCrawler.GameLogic;
import dungeonCrawler.LevelLoader;
//import dungeonCrawler.GameLogic;
import dungeonCrawler.Vector2d;

/**
 * @author Dominik
 *
 */
public class IceWeasel extends GameElement {
	static IceWeasel element;
	GameElementImage gei = new GameElementImage();
	private int health=100;
	private int lives=0;

	/**
	 * @param position
	 * @param size
	 */
	@Deprecated
	public IceWeasel(Vector2d position, Vector2d size) {
		super(position, size, -1);
		this.type = EnumSet.of(ElementType.MOVABLE);
		gei.setSize(getSize());
		try {
			gei.setImage(ImageIO.read(new File("Graphics" + File.separator + "IceWeasel.png")));
		} catch (IOException e) {
			gei.setImage(null);
			e.printStackTrace();
		}
	}

	/**
	 * @param position
	 * @param size
	 */
	public IceWeasel(Vector2d position, Vector2d size, int id) {
		super(position, size, id);
		this.type = EnumSet.of(ElementType.MOVABLE);
		gei.setSize(getSize());
		try {
			gei.setImage(ImageIO.read(new File("Graphics" + File.separator + "IceWeasel.png")));
		} catch (IOException e) {
			gei.setImage(null);
			e.printStackTrace();
		}
	}

	@Override
	public void draw(Graphics g) {
		gei.paintComponent(g);
	}

	public void setPosition(Vector2d pos) {
		this.position = pos;
	}

	@Override
	public void GameEventPerformed(GameEvent e) {
		if(e.element instanceof Player && e.type == EventType.COLLISION){
			System.out.println("autsch!");
			Player elementPlayer = (Player) e.element;
			elementPlayer.reduceHealth(30, DamageType.FIRE, e.gameLogic);
		}
		if(e.type == EventType.TIMER){
			e.gameLogic.moveElement(this, new Vector2d((int)(Math.random()*4-2),(int)(Math.random()*4-2)));
		}
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public void setLives(int lives) {
		this.lives = lives;
	}

	public void increaseHealth(int health) {
		this.health += health;
	}

	public void reduceHealth(int health, DamageType damage, GameLogic logic) {
		if (damage == DamageType.CONVENTIONAL) {
			health /= 5;
		}
		else if (damage == DamageType.ICE) {
			health = 0;
		}
		if (health > 0) {
			if (this.health-health > 0){
				this.health = this.health-health;
				System.out.println("IceWeasel lost " + health + " and has now " + this.health + " Health");

			}
			else {
				lives--;
				if(lives<0){
					this.health -= health;
					this.size = new Vector2d(0,0);
					System.out.println("IceWeasel dead");
					//Quest.killedEnemys(Quest.getLevel());
				} else {
					this.health -= health;
					System.out.println("IceWeasel lost " + health + " and has now " + this.health + " Health");
					this.health = 100;
				}
			}
		}
	}

	public int getHealth() {
		return this.health;
	}

	/**Creates new instance of this class.
	 * @param param parameters of this GameElement as {@link String[]}
	 * @param id as {@link int}
	 * @return a {@link IceWeasel} instance
	 */
	public static IceWeasel createElement(String[] param, int id) {
		if (param.length > 5) {
			element = new IceWeasel(new Vector2d(), new Vector2d(), Integer.parseInt(param[1]));
		}
		else {
			element = new IceWeasel(new Vector2d(), new Vector2d(), id);
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
			if (param.length > 6) {
				element.setLives(Integer.parseInt(param[i+5]));
				element.setHealth(Integer.parseInt(param[i+6]));
			}
			element.gei.setSize(size);
		} catch (NumberFormatException e) {
			System.out.println("Kann ICEWEASEL-Parameter nicht interpretieren.");
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
		return "ICEWEASEL";
	}	

}
