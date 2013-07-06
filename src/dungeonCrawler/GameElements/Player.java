package dungeonCrawler.GameElements;

import java.awt.Color;
import java.awt.Graphics;
import java.util.BitSet;
//import java.util.EnumSet;
import java.util.LinkedList;

//import dungeonCrawler.ElementType;
//import dungeonCrawler.GameElement;
import dungeonCrawler.GameEvent;
import dungeonCrawler.GameLogic;
import dungeonCrawler.GameObject;
import dungeonCrawler.SettingSet;
import dungeonCrawler.Vector2d;
import dungeonCrawler.GameObjects.Protection;

/**
 * @author Tissen
 *
 */
public class Player extends Active {
	public String name = "PLAYER";
	public final int maxHealth = 1000;
	public final int maxMana = 100;
	private int Health=maxHealth;
	private int mana = maxMana;
	public final int maxShield = 1000;
	private int shield = 0;
	private boolean bow;
	private int lives=3;
	//	private int money; TODO
	private GameEvent e;
	private Protection protection = null;

	/**
	 * @param position
	 * @param size
	 */
	public Player(Vector2d position, Vector2d size) {
		super(position, size);
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
		e.gameLogic.Inventar.add(object);
	}

	@Override
	public void GameEventPerformed(GameEvent e) {
		// TODO Auto-generated method stub
		this.e=e;
	}

	public void setHealth(int Health) {
		this.Health = Health;
	}

	public void increaseHealth(int Health) {
		this.Health += Health;
		if(this.Health > this.maxHealth) this.Health = this.maxHealth;
	}

	public void reduceHealth(int Health, GameLogic logic) {
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
		if (protection != null) { // Player hat R�stung
			Health -= protection.getConvProtection();
			if (Health > 0) {
				System.out.println("R�stung absorbiert " + protection.getConvProtection() + " Schaden (" + (Health + protection.getConvProtection()) + ")");
			}
			else {
				System.out.println("R�stung absorbiert vollen Schaden (" + (Health + protection.getConvProtection()) + ")");
				Health = 0;
			}
		}
		this.Health -= Health;
		if (this.Health > 0) {
			System.out.println("Leben verloren! Health: " + this.Health);
		}
		else {
			System.out.println("!TOT! (x.x) Leben: " + lives);
			lives--;
			if(lives < 0){
				e.gameLogic.app.currentLevel = 0;
			} else {
				this.Health = maxHealth;
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
		return this.Health;
	}

	public int getMana() {
		return mana;
	}

	public void setMana(int m) {
		this.mana = m;
	}

	public LinkedList<GameObject> getInventar() {
		return e.gameLogic.getinventory();
	}

	public int getMoney() {
		return e.gameLogic.getmoney();
	}

	public void setMoney(int money) {
		e.gameLogic.setmoney(money);				
	}


	public void addItem(GameObject item) {
		e.gameLogic.Inventar.add(item);
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

	public Protection getProtection() {
		return this.protection;
	}
	public void setProtection(Protection prot) {
		this.protection = prot;
	}

	@Override
	public void interaction(GameLogic logic, SettingSet settings, BitSet keys) {
		// TODO Auto-generated method stub
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
		logic.moveElement(this, direction);
	}

}
