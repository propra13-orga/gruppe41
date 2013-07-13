package dungeonCrawler;

/**
 * Kind definition of the Items which will available in the shop
 * A Item is defined by a name and a price
 * 
 * @author Hucke
 *
 */
public class ShopItem {
private String x;
private int y;
	
	public ShopItem(String x, int y) {
		this.x = x;
		this.y = y;
	}
	public ShopItem() {
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
