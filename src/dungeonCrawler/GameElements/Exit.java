package dungeonCrawler.GameElements;

import java.awt.Color;
import java.awt.Graphics;
import java.util.EnumSet;

import dungeonCrawler.ElementType;
import dungeonCrawler.EventType;
import dungeonCrawler.GameContent;
import dungeonCrawler.GameElement;
import dungeonCrawler.GameElementImage;
import dungeonCrawler.GameEvent;
import dungeonCrawler.LevelLoader;
import dungeonCrawler.Vector2d;
//import dungeonCrawler.App;

/**
 * @author Tissen
 *
 */
public class Exit extends GameElement {
	static Exit element;
	GameElementImage gei = new GameElementImage();

	/**
	 * @param position
	 * @param size
	 */
	@Deprecated
	public Exit(Vector2d position, Vector2d size) {
		super(position, size, -1);
		this.type = EnumSet.of(ElementType.IMMOVABLE, ElementType.WALKABLE);
	}

	/**
	 * @param position
	 * @param size
	 */
	public Exit(Vector2d position, Vector2d size, int id) {
		super(position, size, id);
		this.type = EnumSet.of(ElementType.IMMOVABLE, ElementType.WALKABLE);
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
			e.gameLogic.app.gameContent = new GameContent(e.gameLogic);
			e.gameLogic.app.loader = new LevelLoader(e.gameLogic.app.gameContent, e.gameLogic.app);
//			e.gameLogic.app.startGame();
			System.out.println("Ausgang");
			System.out.println("currentlevel = " + e.gameLogic.app.currentLevel);
//			this.position.setX(10000); // gamelogic muss noch gefixt werden, denn bei 2fachem Auslösen ist man in einer Endlosschleife
			// TODO: gamelogic wurde gefixt => ist der Kommentar noch von richtig?
			this.size.setX(0);this.size.setY(0);
			e.gameLogic.app.startGame();
			e.gameLogic.new_shop = null;
		}
		// TODO Auto-generated method stub
		
	}

	/**Creates new instance of this class.
	 * @param param parameters of this GameElement as {@link String[]}
	 * @param id as {@link int}
	 * @return a {@link Exit} instance
	 */
	public static Exit createElement(String[] param, int id) {
			if (param.length > 5) {
				element = new Exit(new Vector2d(), new Vector2d(), Integer.parseInt(param[1]));
			}
			else {
				element = new Exit(new Vector2d(), new Vector2d(), id);
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
			System.out.println("Kann EXIT-Parameter nicht interpretieren.");
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
		// TODO Auto-generated method stub
		return "EXIT";
	}

}
