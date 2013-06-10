/**
 * 
 */
package dungeonCrawler;

import java.awt.*; 
import javax.swing.*; 



/**
 * @author Mattes
 *
 */

;

public class Camera extends JPanel {
	Dungeon dungeon;


	private static final long serialVersionUID = 1L;


	/**
	 * 
	 */

	final Image wall = Toolkit.getDefaultToolkit().getImage("res/wall.png");
	final Image player = Toolkit.getDefaultToolkit().getImage("res/player.png");	
	final Image enemy = Toolkit.getDefaultToolkit().getImage("res/enemy.png");	
	public Camera(Dungeon d) {
		// TODO Auto-generated constructor stub
		dungeon = d;

//		try {url = getDocumentBase()};
	}

	/* (non-Javadoc)
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 */
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
				case LevelContent.SPACE: g.setColor(Color.WHITE);
				g.fillRect(j*50+1, i*50+1, 50, 50); break;
//				case LevelContent.PLAYER: g.setColor(Color.BLUE);
				case LevelContent.PLAYER: g.drawImage(player, j*50, i*50, 50, 50, this); break;				
//				case LevelContent.COMPUTER: g.setColor(Color.RED);
				case LevelContent.COMPUTER: g.drawImage(enemy, j*50, i*50, 50, 50, this); break;	
				case LevelContent.WALL: g.drawImage(wall, j*50, i*50, 50, 50, this); break;
//				case LevelContent.WALL: g.setColor(Color.DARK_GRAY); break;
				case LevelContent.EXIT: g.setColor(Color.LIGHT_GRAY);
				g.fillRect(j*50+1, i*50+1, 50, 50); break;
				default: g.setColor(Color.LIGHT_GRAY);
				g.fillRect(j*50+1, i*50+1, 50, 50);
				}
			}
			System.out.println();
		}
		// === Test Ende===
	}

}
