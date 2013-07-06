package dungeonCrawler.GameObjects;

import dungeonCrawler.GameElement;
import dungeonCrawler.GameObject;
import dungeonCrawler.GameElements.Player;

/**
 * 
 *
 */
public class ManaPotion extends GameObject {
	private int mana;

	public ManaPotion(int m) {
		this.mana = m;
	}
	
	@Override
	public void performOn(GameElement element) {
		// TODO Auto-generated method stub
		String name = element.getName();
		switch (name) {
		case "PLAYER": ((Player)element).increaseMana(mana); break;
	}

}}
