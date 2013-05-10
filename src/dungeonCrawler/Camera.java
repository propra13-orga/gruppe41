/**
 * 
 */
package dungeonCrawler;

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
		g.drawLine(0, 0, 50, 50);
		// === Test ===
		System.out.println(dungeon.getHeight() + " " + dungeon.getWidth());
		for (int i=0;i<dungeon.getHeight();i++) {
			for (int j=0;j<dungeon.getWidth();j++) {
				g.drawRect(j*10, i*10, 9, 9);
			}
		}
		// === Test Ende===
	}

}
