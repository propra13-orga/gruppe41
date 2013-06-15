package dungeonCrawler;

import java.util.ArrayList;

import dungeonCrawler.GameElements.Player;

/**
 * A level of dungeon
 * 
 * @author Tissen
 *
 */
public class Dungeon {
	private int width;
	private int height;
	private LevelContent[][] grid; // array of game elements
	protected GameElement player; // player element
	private LevelContent exit = new LevelContent(4, "Exit"); // dungeon exit
	// final static direction values
	public static final int UP = 0;
	public static final int RIGHT = 1;
	public static final int DOWN = 2;
	public static final int LEFT = 3;
	// player and dungeon attributes
	public boolean dead = false;
	public boolean complete = false;
	private ArrayList<GameElement> elements;

	// constructor
	public Dungeon(int width, int height) {
		this.elements = new ArrayList<GameElement>();
		this.width = width;
		this.height = height;
		grid = new LevelContent[width][height];
		for (int i=0;i<width;i++) {
			for (int j=0;j<height;j++) {
				grid[i][j] = new LevelContent(LevelContent.WALL);
			}
		}
	}

	public int getWidth() {
		return this.width;
	}

	public int getHeight() {
		return this.height;
	}

	public void setWidth(int w) {
		this.width = w;
	}

	public void setHeight(int h) {
		this.height = h;
	}

//	public LevelContent getContent(int w, int h) {
//		return this.grid[w][h];
//	}
//
//	public void setContent(int w, int h, LevelContent c) {
//		this.grid[w][h] = c;
//	}
	
	public ArrayList<GameElement> getContent() {
		return elements;
	}
	
	public void setContent(GameElement element) {
		GameElement e = collisionWith(element);
		if (e == null) {
			this.elements.add(element);
			if (element.getName().equalsIgnoreCase("PLAYER")) {
				player = new Player(element.getPosition(), element.getSize());
				setPlayerPosition(element.position);
			}
			if (element.getName().equalsIgnoreCase("EXIT"))
				setExitPosition(element.position.getX(), element.position.getY());
		}
		else {
			Error err = new Error("Kann '" + element.getName() +
					"', Position: " + element.position.getX() + "," + element.position.getY() +
					" Größe: " + element.size.getX() + "," + element.size.getY() +
					" nicht setzen. (" + e.getName() + ")");
			err.showMe();
		}
	}

	private void setPlayerPosition(Vector2d pos) {
		this.player.position = pos;
	}

	public void setExitPosition(int x, int y) {
		this.exit.x = x;
		this.exit.y = y;
		grid[x][y].setContent(LevelContent.EXIT);
	}

	// move a player if possible
	public void move(GameElement element, int direction) {
		Vector2d newPosition = element.getPosition(); 
		switch (direction) {
		case UP:
			newPosition.setY(element.getPosition().getY()-1);
			break;
		case RIGHT:
			newPosition.setX(element.getPosition().getX()+1);
			break;
		case DOWN:
			newPosition.setY(element.getPosition().getY()+1);
			break;
		case LEFT:
			newPosition.setX(element.getPosition().getX()-1);
			break;
		}
		element.setPosition(newPosition);
//		try {
//			GameElement e = collisionWith(element);
//
//			if (grid[x][y].getContent() == LevelContent.SPACE) {
//				grid[player.x][player.y].setContent(LevelContent.SPACE);
//				setPlayerPosition(x, y);
//			}
//			else	if (grid[x][y].getContent() == LevelContent.EXIT) {
//				grid[player.x][player.y].setContent(LevelContent.SPACE);
//				setPlayerPosition(x, y);
//				this.complete = true;
//			}
//			else	if (grid[x][y].getContent() == LevelContent.COMPUTER) {
//				dead = true; // player dies, restart level
//			}
//		}
//		catch (ArrayIndexOutOfBoundsException e) {
//			// do nothing
//		}
	}
	
	private GameElement collisionWith(GameElement newElement) {
		for (GameElement e: elements) {
			if (e.collision(newElement))
				return e;
		}
		return null;
	}
	
}
