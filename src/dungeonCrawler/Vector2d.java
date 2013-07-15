package dungeonCrawler;

public class Vector2d {

	private int x, y;
	
	public Vector2d(String str){
		String[] strs = str.split(",");
		if(strs.length==2){
			if(strs[0].startsWith("(") && strs[1].endsWith(")")){
				this.x = Integer.parseInt(strs[0].substring(1));
				this.y = Integer.parseInt(strs[1].substring(0, strs[1].length()-1));
			}
		}
	}
	
	public Vector2d(int x, int y) {
		this.x = x;
		this.y = y;
	}
	public Vector2d() {
		// TODO Auto-generated constructor stub
		x = 0;
		y = 0;
	}
	public Vector2d(Vector2d vector) {
		x = vector.x;
		y = vector.y;
	}
	public boolean isNull(){
		if(this.x == 0 && this.y == 0)
			return true;
		return false;
	}
	public Vector2d addX(int x){
		return new Vector2d(this.x + x, this.y);
	}
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
		// TODO Auto-generated method stub
		return "(" + this.x + "," + this.y + ")";
	}

}
