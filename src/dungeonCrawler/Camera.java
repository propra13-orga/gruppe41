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
		// TODO Auto-generated method stub
		super.paintComponent(g);
		g.drawLine(0, 0, 50, 50);
	}
	
	

}
