package dungeonCrawler;

import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class GameObject implements Drawable {

	public GameObject() {
		// TODO Auto-generated constructor stub
	}
	
	public abstract void performOn(GameElement element);
	public void performOn(GameElement element, GameLogic logic){
		this.performOn(element);
	}

	@Override
	public void draw(Graphics g) {
		Rectangle rect = g.getClipBounds();
		g.fillRect(rect.x, rect.y, rect.width, rect.height);
		
	}

}
