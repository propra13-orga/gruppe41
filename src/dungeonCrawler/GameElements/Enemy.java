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
import dungeonCrawler.GameLogic;
import dungeonCrawler.LevelLoader;
import dungeonCrawler.Vector2d;

/**
 * @author Dominik
 *
 */
public class Enemy extends GameElement {
	static Enemy element;
	GameElementImage gei = new GameElementImage();
	private int Health=100;
	private int lives=0;

	/**
	 * @param position
	 * @param size
	 */
	@Deprecated
	public Enemy(Vector2d position, Vector2d size) {
		super(position, size, -1);
		this.type = EnumSet.of(ElementType.MOVABLE);
	}
	
	/**
	 * @param position
	 * @param size
	 */
	public Enemy(Vector2d position, Vector2d size, int id) {
		super(position, size, id);
		this.type = EnumSet.of(ElementType.MOVABLE);
	}
	
	@Override
	public String getName(){
		return "ENEMY";
	}

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(Color.PINK);
		g.fillRect(0, 0, size.getX(), size.getY());
	}
	
	public void setPosition(Vector2d pos) {
		this.position = pos;
	}

	@Override
	public void GameEventPerformed(GameEvent e) {
		// TODO Auto-generated method stub
		if(e.element instanceof Player && e.type == EventType.COLLISION){
			System.out.println("autsch!");
			Player elementPlayer = (Player) e.element;
			elementPlayer.reduceHealth(10, DamageType.CONVENTIONAL, e.gameLogic);
		}
		if(e.type == EventType.TIMER){
			e.gameLogic.moveElement(this, new Vector2d((int)(Math.random()*4-2),(int)(Math.random()*4-2)));
		}
	}	
	
	public void setHealth(int Health) {
		this.Health = Health;
	}
	
	public void increaseHealth(int Health) {
		this.Health += Health;
	}
	
	public void reduceHealth(int Health, DamageType damage, GameLogic logic) {
		if (this.Health-Health > 0){
			this.Health = this.Health-Health;
			System.out.println("Enemy lost " + Health + " and has now " + this.Health + " Health");
		}
		else {
			lives--;
			if(lives<0){
				this.Health -= Health;
				this.size = new Vector2d(0,0);
				System.out.println("Enemy dead");
			} else {
				this.Health -= Health;
				System.out.println("Enemy lost " + Health + " and has now " + this.Health + " Health");
				this.Health = 100;
			}
		}
	}
	
	public int getHealth() {
		return this.Health;
	}

	/**Creates new instance of this class.
	 * @param param parameters of this GameElement as {@link String[]}
	 * @param id as {@link int}
	 * @return a {@link Enemy} instance
	 */
	public static Enemy createElement(String[] param, int id) {
			if (param.length > 5) {
				element = new Enemy(new Vector2d(), new Vector2d(), Integer.parseInt(param[1]));
			}
			else {
				element = new Enemy(new Vector2d(), new Vector2d(), id);
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
			System.out.println("Kann ENEMY-Parameter nicht interpretieren.");
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

}
