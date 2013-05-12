package dungeonCrawler;

public class Vector2d {

	private int x, y;
	
	public Vector2d(int x, int y) {
		this.x = x;
		this.y = y;
	}
	public Vector2d() {
		// TODO Auto-generated constructor stub
		x = 0;
		y = 0;
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

}
