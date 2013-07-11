package dungeonCrawler;

public class ShopItem {
private String x;
private int y;
	
	public ShopItem(String x, int y) {
		this.x = x;
		this.y = y;
	}
	public ShopItem() {
		// TODO Auto-generated constructor stub
		x = "";
		y = 0;
	}
	public ShopItem(ShopItem item) {
		x = item.x;
		y = item.y;
	}

	
	public String getItemName() {
		return x;
	}
	public void setItemName(String x) {
		this.x = x;
	}
	public int getItemPrice() {
		return y;
	}
	public void setItemPrice(int y) {
		this.y = y;
	}

}
