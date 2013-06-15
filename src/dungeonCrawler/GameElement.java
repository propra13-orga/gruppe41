/**
 * 
 */
package dungeonCrawler;

import java.util.EnumSet;

/**
 * @author Mattes, Tissen
 *
 */
public abstract class GameElement implements Drawable {
	
	public EnumSet<ElementType> type;
	private String name;
	protected Vector2d position;
	protected Vector2d size;
	private Vector2d topRight;
	private Vector2d bottomRight;
	private Vector2d bottomLeft;

	/**
	 * 
	 */
	public GameElement(Vector2d position, Vector2d size) {
		this.type = EnumSet.of(ElementType.IMMOVABLE);
		this.position = position;
		this.size = size;
		myAngles();
		this.name = "";
	}

	public GameElement(Vector2d position, Vector2d size, String name, EnumSet<ElementType> type) {
		this.type = type;
		this.position = position;
		this.size = size;
		myAngles();
		this.name = name;
	}

	public GameElement() {
		this.type = EnumSet.of(ElementType.IMMOVABLE);
		this.position = new Vector2d();
		this.size = new Vector2d();
		myAngles();
		this.name = "";
	}
	
	private void myAngles() {
		topRight = new Vector2d(position.getX()+size.getX()-1, position.getY());
		bottomRight = new Vector2d(position.getX()+size.getX()-1, position.getY()+size.getY()-1);
		bottomLeft = new Vector2d(position.getX(), position.getY()+size.getY()-1);
	}
	
	public Vector2d getPosition() {
		return position;
	}
	
	public Vector2d getSize() {
		return size;
	}
	
	private Vector2d getTopLeft() {
		return position;
	}

	private Vector2d getTopRight() {
		topRight.setX(position.getX()+size.getX()-1);
		return topRight;
	}

	private Vector2d getBottomRight() {
		bottomRight.setX(position.getX()+size.getX()-1);
		bottomRight.setY(position.getY()+size.getY()-1);
		return bottomRight;
	}

	private Vector2d getBottomLeft() {
		bottomLeft.setY(position.getY()+size.getY()-1);
		return bottomLeft;
	}
	
	public boolean collision(GameElement element) {
		if ((this.getTopLeft().getX()<=element.getTopLeft().getX()) && (this.getTopRight().getX()>=element.getTopLeft().getX())) {
			if ((this.getTopLeft().getY()<=element.getTopLeft().getY()) && (this.getBottomLeft().getY()>=element.getTopLeft().getY())) {
				return true;
			}
			else if ((this.getTopLeft().getY()<=element.getBottomLeft().getY()) && (this.getBottomLeft().getY()>=element.getBottomLeft().getY())) {
				return true;
			}
		}
		else if ((this.getTopLeft().getX()<=element.getTopRight().getX()) && (this.getTopRight().getX()>=element.getTopRight().getX())) {
			if ((this.getTopLeft().getY()<=element.getTopRight().getY()) && (this.getBottomLeft().getY()>=element.getTopRight().getY())) {
				return true;
			}
			else if ((this.getTopLeft().getY()<=element.getBottomRight().getY()) && (this.getBottomLeft().getY()>=element.getBottomRight().getY())) {
				return true;
			}
		}
		return false;
	}
	
	public String getName() {
		return name;
	}
	
	private void setPosition(Vector2d pos) {
		this.position = pos;
		myAngles();
	}
	
	public void move(Direction dir) {
		Vector2d newPosition = this.getPosition(); 
		switch (dir) {
		case LEFT:
			newPosition.setX(this.getPosition().getX()-1);
			break;
		case UP:
			newPosition.setY(this.getPosition().getY()-1);
			break;
		case RIGHT:
			newPosition.setX(this.getPosition().getX()+1);
			break;
		case DOWN:
			newPosition.setY(this.getPosition().getY()+1);
			break;
		}
		// TODO: abfragen nach Kollisionen!
		this.setPosition(newPosition);
	}


	/* (non-Javadoc)
	 * @see dungeonCrawler.Drawable#draw()
	 */
	/*@Override
	public void draw() {
		// TODO Auto-generated method stub

	}*/

}
