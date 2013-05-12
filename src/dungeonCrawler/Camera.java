/**
 * 
 */
package dungeonCrawler;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

/**
 * @author Mattes
 *
 */
public class Camera extends JPanel {
	Dungeon dungeon;


	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public Camera(Dungeon d) {
		// TODO Auto-generated constructor stub
		dungeon = d;
	}

	@Override
	protected void paintComponent(Graphics g) {
		// TODO activate clipping (default is OK)
		// TODO consider if shapes are in drawing area
		// TODO draw those components
		super.paintComponent(g);
		// === Test ===
		//System.out.println(dungeon.getHeight() + " " + dungeon.getWidth());
		for (int i=0;i<dungeon.getHeight();i++) {
			for (int j=0;j<dungeon.getWidth();j++) {
				System.out.print(dungeon.getContent(j, i).getContent());
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
			System.out.println();
		}
		// === Test Ende===
	}

}
