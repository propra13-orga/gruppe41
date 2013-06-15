/**
 * 
 */
package dungeonCrawler;

import java.awt.Graphics;
import java.util.ListIterator;

import javax.swing.JPanel;

/**
 * Shows level content
 * 
 * @author Mattes, Tissen
 *
 */
public class Camera extends JPanel {
	GameContent level;
	Vector2d position = new Vector2d(0,0);

	private static final long serialVersionUID = 1L;

	/**
	 * Constructor
	 * @param level Current level
	 */
	public Camera(GameContent level) {
		this.level = level;
	}
	
	public GameContent getLvl() {
		return level;
	}

	public void setLvl(GameContent lvl) {
		this.level = lvl;
	}

	public void initialize(GameLogic gl){
		this.setFocusTraversalKeysEnabled(false);
		this.addKeyListener(gl);
	}

	// paints all components
	@Override
	protected void paintComponent(Graphics g) {
		// TODO activate clipping (default is OK)
		// TODO consider if shapes are in drawing area
		// TODO draw those components
		super.paintComponent(g);
	/*	for (GameElement e: dungeon.getContent()) {
			e.draw(g);
		}*/
		
/*		for (int i=0;i<dungeon.getHeight();i++) {
			for (int j=0;j<dungeon.getWidth();j++) {
				switch (dungeon.getContent(j, i).getContent()) {
				case LevelContent.SPACE: g.setColor(Color.WHITE); break;
				case LevelContent.PLAYER: g.setColor(Color.BLUE); break;
				case LevelContent.COMPUTER: g.setColor(Color.RED); break;
				case LevelContent.WALL: g.setColor(Color.DARK_GRAY); break;
				case LevelContent.EXIT: g.setColor(Color.LIGHT_GRAY); break;
				default: g.setColor(Color.LIGHT_GRAY);
				}
				g.fillRect(j*50+1, i*50+1, 48, 48);
			}
		}
		
		Vector2d position = new Vector2d(0,0);
		ListIterator<GameElement> it = lvl.getIterator();
		GameElement tmp;
		while(it.hasNext()){
			tmp = it.next();
			tmp.draw(g, position.add(tmp.getPosition()));
		}
		
		
*/	
		if (level != null) {
			ListIterator<GameElement> it = level.getIterator();
			GameElement tmp;
			Graphics gr;
			while (it.hasNext()) {
				tmp = it.next();
				gr = g.create(tmp.position.getX(), tmp.position.getY(), tmp
						.getSize().getX(), tmp.getSize().getY());
				tmp.draw(gr);
				gr.dispose();
			}
		}
	}
}
