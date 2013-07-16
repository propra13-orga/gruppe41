package dungeonCrawler;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

/**Image of element
 * @author Tissen
 */
public class GameElementImage extends JPanel {
	private BufferedImage image;
	private Vector2d size;

	private static final long serialVersionUID = 1L;
	
	/**Paints the image
	 * @param g graphics object
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (int i=0;i<size.getX();i+=image.getWidth()) {
			for (int j=0;j<size.getY();j+=image.getHeight()) {
				g.drawImage(image, i, j, this);
			}
		}
	}
	
	public void setImage(BufferedImage img) {
		image = img;
	}
	
	public void setSize(Vector2d s) {
		size = s;
	}

}
