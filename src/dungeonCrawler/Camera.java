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

	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public Camera() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void paintComponent(Graphics g) {
		// TODO activate clipping (default is OK)
		// TODO consider if shapes are in drawing area
		// TODO draw those components
		super.paintComponent(g);
		g.drawLine(0, 0, 50, 50);
	}
	
	

}
