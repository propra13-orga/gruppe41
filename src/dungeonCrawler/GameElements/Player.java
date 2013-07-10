package dungeonCrawler.GameElements;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.BitSet;
//import java.util.EnumSet;
import java.util.LinkedList;

import dungeonCrawler.DamageType;
import dungeonCrawler.EventType;
//import dungeonCrawler.ElementType;
//import dungeonCrawler.GameElement;
import dungeonCrawler.GameEvent;
import dungeonCrawler.GameLogic;
import dungeonCrawler.GameObject;
import dungeonCrawler.SettingSet;
import dungeonCrawler.Vector2d;
import dungeonCrawler.GameObjects.HealthPotion;
import dungeonCrawler.GameObjects.ManaPotion;
import dungeonCrawler.GameObjects.Armor;

/**
 * @author Tissen
 *
 */
public class Player extends Active {
	public Vector2d last_direction = new Vector2d(0,1);
	public String name = "PLAYER";
	public final int maxHealth = 1000;
	public final int maxMana = 100;
	private int health=maxHealth;
	private int mana = maxMana;
	public final int maxShield = 1000;
	private int shield = 0;
	private boolean bow;
	private int lives=3;
	private int money = 0;
	private Vector2d checkpoint;
	private LinkedList<GameObject> inventar = new LinkedList<GameObject>();
	
	private int movementdelay = 0;
	private Armor protection = null;

	/**
	 * @param position
	 * @param size
	 */
	public Player(Vector2d position, Vector2d size) {
		super(position, size);
		checkpoint = position;
	}

	public String getName(){
		return "PLAYER";
	}

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub

		g.setColor(Color.BLUE);
		g.fillRect(0, 0, size.getX(), size.getY());
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
		if (protection != null) { // Player hat Rüstung
			switch (damageTyp) {
			case CONVENTIONAL: Health -= protection.getConvProtection();
			case FIRE: Health -= protection.getFireResist();
			case ICE: Health -= protection.getIceResist();
			}
			if (Health > 0) {
				System.out.println("Rüstung absorbiert " + protection.getConvProtection() + " Schaden (" + (Health + protection.getConvProtection()) + ")");
			}
			else {
				System.out.println("Rüstung absorbiert vollen Schaden (" + (Health + protection.getConvProtection()) + ")");
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
	public boolean hasBow() {
		return bow;
	}

	public void setBow(boolean b) {
		this.bow = b;
	}

	public Armor getProtection() {
		return this.protection;
	}
	public void setProtection(Armor prot) {
		this.protection = prot;
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
		
		if (logic.checkKey(settings.CHANGE_ARMOR)){
			ArrayList<GameObject> protectionList = new ArrayList<GameObject>();
			int current = -1;
			int n = -1;
			for(GameObject o : inventar){
				if(o instanceof Armor){
					n++;
					protectionList.add(o);
					if (o == protection) {
						current = n;
					}
				}
			}
			if (current > -1) {
				protectionList.get((current+1)%protectionList.size()).performOn(this);
			}
		}
		
		this.last_direction = direction;
	}

	public static Player createElement(String[] param) {
		Vector2d position = new Vector2d();
		Vector2d size = new Vector2d();
		try {
			position.setX(Integer.parseInt(param[1]));
			position.setY(Integer.parseInt(param[2]));
			size.setX(Integer.parseInt(param[3]));
			size.setY(Integer.parseInt(param[4]));
		} catch (NumberFormatException e) {
			System.out.println("Kann PLAYER-Parameter nicht interpretieren.");
		}
		return (new Player(position, size));
	}

}
