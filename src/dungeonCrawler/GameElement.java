package dungeonCrawler;

import java.util.EnumSet;

/**Elements of a dungeon like wall, trap, player etc.
 * @author Mattes, Tissen
 */
public abstract class GameElement implements Drawable, GameListener {
	public EnumSet<ElementType> type;
	protected Vector2d position;
	protected Vector2d size;
	protected final int id;

	/**Constructor
	 * @param position as {@link Vector2d}
	 * @param size as {@link Vector2d}
	 */
	public GameElement(Vector2d position, Vector2d size){
		this.type = EnumSet.of(ElementType.IMMOVABLE);
		this.position = position;
		this.size = size;
		this.id = -1;
	}

	/**Constructor
	 * @param position as {@link Vector2d}
	 * @param size as {@link Vector2d}
	 * @param id as {@link int}
	 */
	public GameElement(Vector2d position, Vector2d size, int id) {
		this.type = EnumSet.of(ElementType.IMMOVABLE);
		this.position = position;
		this.size = size;
		this.id = id;
	}
	
	@Deprecated
	public GameElement(Vector2d position, Vector2d size, String name, EnumSet<ElementType> type) {
		this.type = type;
		this.position = position;
		this.size = size;
		this.id = 0;
	}

	/**Gets a string with Element parameters
	 * @return parameter as {@link String}
	 */
	public String getString() {
		String sep = LevelLoader.getSplitChar();
		return (this.getName() + sep + id + sep +
				position.getX() + sep + position.getY() + sep +
				size.getX() + sep + size.getY());
	}
	
	public Vector2d getPosition() {
		return position;
	}

	public Vector2d getSize() {
		return size;
	}

	private Vector2d getTopLeft() {
		return new Vector2d(position);
	}

	private Vector2d getTopRight() {
		return position.addX(size.getX());
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
	
	/**Check whether this point is in the element.
	 * @param point to be checked
	 * @return {@link true} if this point is in the element, else {@link false}
	 */
	public boolean isInnerPoint(Vector2d point){
		if(this.getLeft() < point.getX() && this.getRight() > point.getX() &&
				this.getTop() < point.getY() && this.getBottom() > point.getY())
			return true;
		return false;
	}

	/**Ckeck for collision
	 * @param element an another element
	 * @return {@link true} if collides, else {@link false}
	 */
	public boolean collision(GameElement element) {
		if(element != this) {
			int xl 	= 	this	.getTopLeft().getX();
			int xr 	= 	this	.getTopRight().getX();
			int yt 	= 	this	.getTopLeft().getY();
			int yb 	= 	this	.getBottomLeft().getY();
			int xel = 	element	.getTopLeft().getX();
			int xer = 	element	.getTopRight().getX();
			int yet = 	element	.getTopLeft().getY();
			int yeb = 	element	.getBottomLeft().getY();	
			
			if (xl<xer && xr>xer && yt<yeb && (yb>yet)){					//Kollision 	rechts 	am element 
				return true;
			}
			
			else if (xr>xel && xl<xel && yt<yeb && (yb>yet)){			//Kollision 	links 	am element 
				return true;
			}
		
			else if (yt<yeb && yb>yeb && xr>xel && xl<xer){				//Kollision 	unten 	am element 
				return true;
			}	
			
			else if (yb>yet && yt<yet && xr>xel && xl<xer){				//Kollision 	oben 	am element 
				return true;
			}
			else if ((xl>xel || xl==xel) && (xr<xer || xr==xer) && (yt>yet || yt==yet) && (yb<yeb || yb==yeb)){
				return true;
			}
		}
		return false;
	}

	public String getName() {
		return "GameElement";
	}

	public void setPosition(Vector2d pos) {
		this.position = pos;
	}
	
	public void setSize(Vector2d s) {
		this.size = s;
	}
	
	public static void setAddPostion(Vector2d pos, int x, int y){
		pos.addX(x);
		pos.addY(y);
	}
	
}
