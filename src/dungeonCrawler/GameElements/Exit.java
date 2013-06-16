package dungeonCrawler.GameElements;

import java.awt.Color;
import java.awt.Graphics;
import java.util.EnumSet;

import dungeonCrawler.ElementType;
import dungeonCrawler.EventType;
import dungeonCrawler.GameContent;
import dungeonCrawler.GameElement;
import dungeonCrawler.GameEvent;
import dungeonCrawler.LevelLoader;
import dungeonCrawler.Vector2d;
import dungeonCrawler.App;

/**
 * @author Tissen
 *
 */
public class Exit extends GameElement {

	/**
	 * @param position
	 * @param size
	 */
	public Exit(Vector2d position, Vector2d size) {
		super(position, size, "EXIT", EnumSet.of(ElementType.IMMOVABLE, ElementType.WALKABLE));
	}

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(0, 0, size.getX(), size.getY());
	}

	@Override
	public void GameEventPerformed(GameEvent e) {
		if(e.element instanceof Player && e.type == EventType.COLLISION){
			e.gameLogic.app.currentLevel += 1;
			e.gameLogic.app.cp.removeAll();
			e.gameLogic.app.cp.validate();
			e.gameLogic.app.gameContent = new GameContent();
			e.gameLogic.app.loader = new LevelLoader(e.gameLogic.app.gameContent, e.gameLogic.app);
//			e.gameLogic.app.startGame();
			System.out.println("Ausgang");
			e.gameLogic.app.startGame();
		}
		// TODO Auto-generated method stub
		
	}

}
