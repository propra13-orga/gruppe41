package dungeonCrawler;

/**Point 2D
 */
public class Vector2d {
	private int x, y;
	
	/**Constructor
	 * @param str parameter {@link String}
	 */
	public Vector2d(String str){
		String[] strs = str.split(LevelLoader.getSplitChar());
		if(strs.length==2){
			if(strs[0].startsWith("(") && strs[1].endsWith(")")){
				this.x = Integer.parseInt(strs[0].substring(1));
				this.y = Integer.parseInt(strs[1].substring(0, strs[1].length()-1));
			}
		}
	}
	
	/**Constructor
	 * @param x as {@link int}
	 * @param y as {@link int}
	 */
	public Vector2d(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	/**Constructor
	 */
	public Vector2d() {
		x = 0;
		y = 0;
	}
	
	/**Constructor
	 * @param vector another {@link Vector2d}
	 */
	public Vector2d(Vector2d vector) {
		x = vector.x;
		y = vector.y;
	}
	
	public boolean isNull(){
		if(this.x == 0 && this.y == 0)
			return true;
		return false;
	}
	
	/**Changes x value
	 * @param x value as {@link int}
	 * @return new {@link Vector2d}
	 */
	public Vector2d addX(int x){
		return new Vector2d(this.x + x, this.y);
	}
	
	/**Changes y value
	 * @param y value as {@link int}
	 * @return new {@link Vector2d}
	 */
	public Vector2d addY(int y){
		return new Vector2d(this.x, this.y + y);
	}
	
	public Vector2d mul(double d){
		return new Vector2d((int)(this.x*d), (int)(this.y*d));
	}
	
	public Vector2d add(Vector2d a){
		return new Vector2d(a.getX() + this.x, a.getY() + this.y);
	}
	
	public static Vector2d add(Vector2d a, Vector2d b){
		return new Vector2d(a.getX() + b.getX(), a.getY() + b.getY());
	}
	
	public int getX() {
		return x;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	@Override
	public String toString() {
		return "(" + this.x + "," + this.y + ")";
	}

}
