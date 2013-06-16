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

	/**
	 * 
	 */
	public GameElement(Vector2d position, Vector2d size) {
		this.type = EnumSet.of(ElementType.IMMOVABLE);
		this.position = position;
		this.size = size;
		this.name = "";
	}

	public GameElement(Vector2d position, Vector2d size, String name, EnumSet<ElementType> type) {
		this.type = type;
		this.position = position;
		this.size = size;
		this.name = name;
	}

	public GameElement() {
		this.type = EnumSet.of(ElementType.IMMOVABLE);
		this.position = new Vector2d();
		this.size = new Vector2d();
		this.name = "";
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
		
		return position.addX(size.getX());
	}

	private Vector2d getBottomRight() {
		return position.add(size);
	}

	private Vector2d getBottomLeft() {
		return position.addY(size.getY());
	}
	
	private int getLeft(){
		return this.position.getX();
	}
	
	private int getTop(){
		return this.position.getY();
	}
	
	private int getRight(){
		return this.position.getX() + this.size.getX();
	}
	
	private int getBottom(){
		return this.position.getY() + this.size.getY();
	}
	
	public boolean isInnerPoint(Vector2d point){
		if(this.getLeft() < point.getX() && this.getRight() > point.getX() &&
				this.getTop() < point.getY() && this.getBottom() > point.getY())
			return true;
		return false;
	}

	public boolean collision(GameElement element) {
		if(element != this){ //TODO Kollision nach oben/unten fehlerhaft
			
			if(element.isInnerPoint(this.getTopLeft()))
				return true;
			if(element.isInnerPoint(this.getBottomLeft()))
				return true;
			if(element.isInnerPoint(this.getTopRight()))
				return true;
			if(element.isInnerPoint(this.getBottomRight()))
				return true;
			

			if(this.isInnerPoint(element.getTopLeft()))
				return true;
			if(this.isInnerPoint(element.getBottomLeft()))
				return true;
			if(this.isInnerPoint(element.getTopRight()))
				return true;
			if(this.isInnerPoint(element.getBottomRight()))
				return true;
			
/*			if ((this.getTopLeft().getX()<=element.getTopLeft().getX()) && (this.getTopRight().getX()>=element.getTopLeft().getX())) {
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
			}*/
		}
		return false;
	}

	public String getName() {
		return name;
	}

	public void setPosition(Vector2d pos) {
		this.position = pos;
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

	public boolean moveElement(Vector2d direction){
		if(this.type.contains(ElementType.MOVABLE)){
			this.setPosition(direction);
			return true;
		}
		else
			return false;
	}


	/* (non-Javadoc)
	 * @see dungeonCrawler.Drawable#draw()
	 */
	/*@Override
	public void draw() {
		// TODO Auto-generated method stub

	}*/

}
