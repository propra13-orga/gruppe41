package dungeonCrawler.GameElements;

import java.awt.Color;
import java.awt.Graphics;
import java.util.EnumSet;
import java.util.LinkedList;

import dungeonCrawler.ElementType;
import dungeonCrawler.GameElement;
import dungeonCrawler.GameEvent;
import dungeonCrawler.GameLogic;
import dungeonCrawler.GameObject;
import dungeonCrawler.Vector2d;

/**
 * @author Tissen
 *
 */
public class Player extends GameElement {
	public String name = "PLAYER";
	public final int maxHealth = 1000;
	public final int maxMana = 100;
	private int Health=maxHealth;
	private int mana = maxMana;
	public final int maxArmor = 1000;
	private int armor = 0;
	private boolean bow;
	private int lives=3;
	private int money;
	private GameEvent e;
	/**
	 * @param position
	 * @param size
	 */
	public Player(Vector2d position, Vector2d size) {
		super(position, size, "PLAYER", EnumSet.of(ElementType.MOVABLE));
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
		this.armor -= Health;
		System.out.print("R�stung absorbiert ");
		if (this.armor<0) {
			System.out.println((Health+this.armor) + " Schaden");
			Health = this.armor*(-1);
			this.armor = 0;
		}
		else {
			System.out.println(Health + " Schaden");
			Health = 0;
		}
		if (this.Health-Health > 0){
			this.Health = this.Health-Health;
			System.out.println("Health verloren! Health: " + this.Health);
		}
		else {
			lives--;
			if(lives<0){
				this.Health -= Health;
				System.out.println("!TOT! (x.x) Health: " + this.Health);
				e.gameLogic.app.currentLevel = 0;
			} else {
				this.Health -= Health;
				System.out.println("!TOT! (x.x) Health: " + this.Health);
				this.Health = 1000;
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
	
	public void increaseArmor(int arm) {
		this.armor += arm;
		if(this.armor > this.maxArmor) this.armor = this.maxArmor;
	}
	
	public int getArmor() {
		return this.armor;
	}
	
	public boolean hasBow() {
		return bow;
	}
	public void setBow(boolean b) {
		this.bow = b;
	}

	public void setArmor(int armor) {
		this.armor=armor;
		// TODO Auto-generated method stub
		
	}

}
