package dungeonCrawler.GameElements;

import java.awt.Color;
import java.awt.Graphics;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.BitSet;
//import java.util.EnumSet;
import java.util.LinkedList;

import javax.imageio.ImageIO;

import dungeonCrawler.DamageType;
import dungeonCrawler.EventType;
import dungeonCrawler.GameElementImage;
//import dungeonCrawler.ElementType;
//import dungeonCrawler.GameElement;
import dungeonCrawler.GameEvent;
import dungeonCrawler.GameLogic;
import dungeonCrawler.GameObject;
import dungeonCrawler.LevelLoader;
import dungeonCrawler.SettingSet;
import dungeonCrawler.Vector2d;
import dungeonCrawler.GameObjects.FireArmor;
import dungeonCrawler.GameObjects.HealthPotion;
import dungeonCrawler.GameObjects.IceArmor;
import dungeonCrawler.GameObjects.ManaPotion;
import dungeonCrawler.GameObjects.ConvArmor;

/**
 * @author Tissen
 *
 */
public class Player extends Active {
	static Player element;
	GameElementImage gei = new GameElementImage();
	public Vector2d last_direction = new Vector2d(0,1);
	public String name = "PLAYER";
	public final int maxHealth = 1000;
	public final int maxMana = 100;
	private int health=maxHealth;
	private int mana = maxMana;
	public final int maxShield = 1000;
	private int shield = 0;
	private int lives=3;
	private int money = 0;
	private Vector2d checkpoint;
	private LinkedList<GameObject> inventar = new LinkedList<GameObject>();
	
	private int movementdelay = 0;
	private GameObject armor = null;

	/**
	 * @param position
	 * @param size
	 */
	@Deprecated
	public Player(Vector2d position, Vector2d size) {
		super(position, size, -1);
		checkpoint = position;
		GameElementImage gei = new GameElementImage();
	}

	public Player(Vector2d position, Vector2d size, int id) {
		super(position, size, id);
		checkpoint = position;		
		gei.setSize(getSize());
		try {
			gei.setImage(ImageIO.read(new File("Graphics" + File.separator + "player.png")));
		} catch (IOException e) {
			gei.setImage(null);
			e.printStackTrace();
		}
	}

	public String getName(){
		return "PLAYER";
	}

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		gei.paintComponent(g);
	}

	public void add(GameObject object){
		this.inventar.add(object);
	}

	@Override
	public void GameEventPerformed(GameEvent e) {
		// TODO Auto-generated method stub
		if(e.type == EventType.COLLISION){
			if(e.element instanceof CheckPoint){
				this.checkpoint = this.position;
			}
		}
	}

	public void setHealth(int Health) {
		this.health = Health;
	}

	public void increaseHealth(int Health) {
		this.health += Health;
		if(this.health > this.maxHealth) this.health = this.maxHealth;
	}
	
	public void die(GameLogic logic){
		if(this.lives>=0){
			this.lives--;
			this.health = this.maxHealth;
			this.position = this.checkpoint;
		} else {
			logic.lost(this);
		}
	}

	public void reduceHealth(int Health, DamageType damageTyp, GameLogic logic) {
		this.shield -= Health;
		if (this.shield <= 0) { // Player hat magischen Schild
			System.out.println("Magischer Schutz absorbiert " + (Health + this.shield) + " Schaden (" + Health + ")");
			Health = this.shield*(-1);
			this.shield = 0;
		}
		else {
			System.out.println("Magischer Schutz absorbiert vollen Schaden (" + Health + ")");
			Health = 0;
		}
		if (armor != null) { // Player hat Rüstung
			switch (damageTyp) {
			case CONVENTIONAL: if (armor instanceof ConvArmor) Health -= ((ConvArmor)armor).getArmor(); break;
			case FIRE: if (armor instanceof FireArmor) Health -= ((FireArmor)armor).getArmor(); break;
			case ICE: if (armor instanceof IceArmor) Health -= ((IceArmor)armor).getArmor(); break;
			}
			if (Health < 0) {
				System.out.println("Rüstung absorbiert Schaden.");
				Health = 0;
			}
		}
		this.health -= Health;
		if (this.health > 0) {
			System.out.println("Leben verloren! Health: " + this.health);
		}
		else {
			System.out.println("!TOT! (x.x) Leben: " + lives);
			lives--;
			if(lives < 0){
				logic.app.currentLevel = 0;
			} else {
				this.health = maxHealth;
				logic.teleportElement(this, logic.getCheckPoint());
			}
		}
	}

	public boolean reduceMana(int mana, GameLogic logic) {
		if (this.mana-mana >= 0){
			this.mana = this.mana-mana;
			return true;
		}
		return false;
	}
	public void increaseMana(int mana) {
		this.mana += mana;
		if(this.mana > this.maxMana) this.mana = this.maxMana;
	}	

	public int getHealth() {
		return this.health;
	}

	public int getMana() {
		return mana;
	}

	public void setMana(int m) {
		this.mana = m;
	}

	public LinkedList<GameObject> getInventar() {
		return this.inventar;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}


	public void addItem(GameObject item) {
		this.inventar.add(item);
	}

	public void increaseShield(int s) {
		this.shield += s;
		if(this.shield > this.maxShield) this.shield = this.maxShield;
	}

	public int getShield() {
		return this.shield;
	}

	public void setShield(int s) {
		this.shield = s;
	}


	public GameObject getArmor() {
		return this.armor;
	}
	public void setArmor(GameObject a) {
		this.armor = a;
	}

	@Override
	public void interaction(GameLogic logic, SettingSet settings, BitSet keys) {
		
		if (movementdelay >= 0) movementdelay -=1;
		Vector2d direction = new Vector2d(0,0);
		if (keys.get(settings.MOVE_LEFT)) {// left arrow
			direction = direction.addX(-1);
		}
		if (keys.get(settings.MOVE_UP)) {// up arrow
			direction = direction.addY(-1);
		}
		if (keys.get(settings.MOVE_RIGHT)) {// right arrow
			direction = direction.addX(1);
		}
		if (keys.get(settings.MOVE_DOWN)) {// down arrow
			direction = direction.addY(1);
		}
		if(movementdelay <= 0){
			logic.moveElement(this, direction);
			movementdelay = 3;
		}
		
		
		if (logic.checkKey(settings.USE_HEALTHPOT)){
			for(GameObject o : inventar){
				if(o instanceof HealthPotion){
					o.performOn(this);
					inventar.remove(o);
					break;
				}
			}
		}
		
		if (logic.checkKey(settings.USE_MANAPOT)){
			for(GameObject o : inventar){
				if(o instanceof ManaPotion){
					o.performOn(this);
					inventar.remove(o);
					break;
				}
			}
		}
		
		if (logic.checkKey(settings.SHOOT)){
			dungeonCrawler.GameObjects.Bow bow = null;
			for(GameObject o : inventar){
				if(o instanceof dungeonCrawler.GameObjects.Bow){
					bow = (dungeonCrawler.GameObjects.Bow) o;
					break;
				}
			}
			if(bow != null){
				bow.performOn(this,logic);
			} else {
				Vector2d pos = new Vector2d(position.add(this.getSize().mul(0.5)).add(new Vector2d(-5, -5)));
				if(last_direction.getX() > 0)
					pos = pos.add(new Vector2d(this.getSize().getX()-2,0));
				if(last_direction.getX() < 0)
					pos = pos.add(new Vector2d(-this.getSize().getX()+2,0));
				if(last_direction.getY() > 0)
					pos = pos.add(new Vector2d(0,this.getSize().getX()-2));
				if(last_direction.getY() < 0)
					pos = pos.add(new Vector2d(0,-this.getSize().getX()+2));
				Bullet tmp = new Bullet(pos, new Vector2d(10, 10));
				tmp.setLife(20);
				logic.addGameElement(tmp);
			}
		}
		
		if (logic.checkKey(settings.CHANGE_ARMOR)){
			ArrayList<GameObject> armorList = new ArrayList<GameObject>();
			int current = -1;
			int n = -1;
			for (GameObject o : inventar){
				if (o instanceof ConvArmor) {
					n++;
					armorList.add((ConvArmor) o);
					if ((ConvArmor) o == armor) {
						current = n;
					}
				}
				if (o instanceof FireArmor) {
					n++;
					armorList.add((FireArmor) o);
					if ((FireArmor) o == armor) {
						current = n;
					}
				}
				if (o instanceof IceArmor) {
					n++;
					armorList.add((IceArmor) o);
					if ((IceArmor) o == armor) {
						current = n;
					}
				}
			}
			if (n >= 0) {
				if (current > 0) {
					armorList.get((current+1)%armorList.size()).performOn(this);
				}
				else {
					armorList.get(0).performOn(this);
				}
			}
			else {
				System.out.println("Keine Rüstungen vorhanden.");
			}
		}
		if(!direction.isNull())
			this.last_direction = direction;
	}

	/**Creates new instance of this class.
	 * @param param parameters of this GameElement as {@link String[]}
	 * @param id as {@link int}
	 * @return a {@link Player} instance
	 */
	public static Player createElement(String[] param, int id) {
			if (param.length > 5) {
				element = new Player(new Vector2d(), new Vector2d(), Integer.parseInt(param[1]));
			}
			else {
				element = new Player(new Vector2d(), new Vector2d(), id);
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
			System.out.println("Kann PLAYER-Parameter nicht interpretieren.");
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
