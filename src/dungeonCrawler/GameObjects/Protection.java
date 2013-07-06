package dungeonCrawler.GameObjects;

import dungeonCrawler.GameElement;
import dungeonCrawler.GameObject;
//import dungeonCrawler.GameElements.Enemy;
import dungeonCrawler.GameElements.Player;

/**
 * @author Tissen
 *
 */
public class Protection extends GameObject {
	private int convProtection;
	private int fireResist;
	private int iceResist;

	public Protection(int a, int fire, int ice) {
		this.convProtection = a;
		this.fireResist = fire;
		this.iceResist = ice;
	}

	@Override
	public void performOn(GameElement element) {
		String name = element.getName();
		switch (name) {
		case "PLAYER": ((Player)element).setProtection(this); break;
		}
	}
	
	public int getConvProtection() {
		return convProtection;
	}
	
	public int getFireResist() {
		return fireResist;
	}
	
	public int getIceResist() {
		return iceResist;
	}

}