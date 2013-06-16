package dungeonCrawler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.BitSet;

import javax.swing.Timer;

public class GameLogic implements KeyListener, ActionListener {

	private GameContent level;
	private BitSet keys;
	protected Timer timer;
	private App app;
	GameElement player;

	public GameLogic(App app) {
		// TODO Auto-generated constructor stub
		keys = new BitSet();
		keys.clear();
		timer = new Timer(50, this);
		timer.setActionCommand("Timer");
		timer.stop();
		this.app = app;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		keys.set(e.getKeyCode());
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		keys.clear(e.getKeyCode());
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	public GameContent getLevel() {
		return level;
	}

	public void setLevel(GameContent level) {
		this.level = level;
	}
	
	private void handleCollision(GameElement active, GameElement passive){
		//TODO generate GameEvents
	}
	
	public boolean moveElement(GameElement e, Vector2d direction){
		if(e.type.contains(ElementType.MOVABLE)){
			e.setPosition(e.position.add(new Vector2d(direction.getX(), 0)));
			for(GameElement collisioncheck : level.getGameElements()){
				System.out.println("test" + collisioncheck.type.toString());
				if(e.collision(collisioncheck)){
//					System.out.println("test" + collisioncheck.type.toString());
						
					if(!collisioncheck.type.contains(ElementType.WALKABLE)){
						e.setPosition(e.position.add(new Vector2d(-direction.getX(), 0)));
					}
					handleCollision(e, collisioncheck); //handle collision (e.g. traps, exit ...)
				}
			}
			e.setPosition(e.position.add(new Vector2d(0, direction.getY())));
			for(GameElement collisioncheck : level.getGameElements()){
				if(e.collision(collisioncheck)){
					if(!collisioncheck.type.contains(ElementType.WALKABLE)){
						e.setPosition(e.position.add(new Vector2d(-direction.getX(), 0)));
					}
					handleCollision(e, collisioncheck); //handle collision (e.g. traps, exit ...)
				}
			}
			//e.setPosition(direction.add(e.position));
			return true;
		}
		else
			return false;
	}

	public boolean teleportElement(GameElement e, Vector2d position){
		return false;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO: abfragen, welche Bits gesetzt sind und ensprechend handeln
		player = level.getPlayer();
		Vector2d direction = player.getPosition();
		direction = new Vector2d(0,0);
		if (keys.get(37)) {// left arrow
			direction = direction.addX(-2);
			System.out.println("LEFT");
		}
		if (keys.get(38)) {// up arrow
			direction = direction.addY(-2);
			System.out.println("UP");
		}
		if (keys.get(39)) {// right arrow
			direction = direction.addX(2);
			System.out.println("RIGHT");
		}
		if (keys.get(40)) {// down arrow
			direction = direction.addY(2);
			System.out.println("DOWN");
		}
		if(!keys.isEmpty()) moveElement(player, direction);

		if (e.getActionCommand() == "Timer")
			app.camera.repaint();
	}

}
