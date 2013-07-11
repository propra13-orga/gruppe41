/**
 * 
 */
package dungeonCrawler;

import java.awt.Graphics;
import java.util.ListIterator;

import javax.swing.JPanel;

import dungeonCrawler.GameElements.ItemPanel;
import dungeonCrawler.GameElements.StatusBar;

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
		
/*		Vector2d position = new Vector2d(0,0);
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
			
			position = level.getPlayer().position.add(new Vector2d(-this.getWidth()/2, -this.getHeight()/2));
			while (it.hasNext()) {
				tmp = it.next();
				gr = g.create(tmp.position.getX()-position.getX(), tmp.position.getY()-position.getY(), tmp
						.getSize().getX(), tmp.getSize().getY());
				tmp.draw(gr);
				gr.dispose();
			}
			// show status bar
			tmp = level.getStatusBar();
			gr = g.create(((StatusBar)tmp).position.getX(), ((StatusBar)tmp).position.getY(),
					((StatusBar)tmp).getSize().getX(), ((StatusBar)tmp).getSize().getY());
			tmp.draw(gr);
			gr.dispose();
			// show item panel
			tmp = level.getItemPanel();
			gr = g.create(((ItemPanel)tmp).position.getX(), ((ItemPanel)tmp).position.getY(),
					((ItemPanel)tmp).getSize().getX(), ((ItemPanel)tmp).getSize().getY());
			tmp.draw(gr);
			gr.dispose();
		}
	}
}
