package dungeonCrawler;

public abstract class GameObject {

	public GameObject() {
		// TODO Auto-generated constructor stub
	}
	
	public abstract void performOn(GameElement element);
	public void performOn(GameElement element, GameLogic logic){
		this.performOn(element);
	}

}
