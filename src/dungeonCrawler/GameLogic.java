package dungeonCrawler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.BitSet;
import java.util.HashSet;
import java.util.LinkedList;

import javax.swing.Timer;

import dungeonCrawler.GameElements.Active;
import dungeonCrawler.GameElements.FireBolt;
import dungeonCrawler.GameElements.IceBolt;
import dungeonCrawler.GameElements.Player;
//import dungeonCrawler.GameElements.Spell;

public class GameLogic implements KeyListener, ActionListener {

//	private long time;
	private Vector2d direction = new Vector2d(0,0);
	private Vector2d lastDirection = new Vector2d(1,0);
	private Vector2d checkPoint = new Vector2d(0,0);
	
	private SettingSet settings = new SettingSet(); 
	private int[] delay = new int[1000];
	private int[] max_delay = new int[1000];
	private GameContent level;
	private BitSet keys;
	protected Timer timer;
	public App app;
	Player player;
	public DirtyShopSystem shop = null;
	public int Money;
	public LinkedList<GameObject> Inventar = new LinkedList<GameObject>();	


	public GameLogic(App app) {
		// TODO Auto-generated constructor stub
		keys = new BitSet();
		keys.clear();
		timer = new Timer(1, this);
		timer.setActionCommand("Timer");
		timer.stop();
		this.app = app;
		Money = 0;
		
		for(int i=0;i<1000;i++){
			max_delay[i] = 3; 
		}
		max_delay[settings.SHOOT] = 200;
		max_delay[settings.USE_HEALTHPOT] = 500;
		max_delay[settings.USE_MANAPOT] = 500;
	}
	
	private void reduceDelay(){
		for(int i=0;i<1000;i++){
			if(delay[i] > 0) delay[i]--;
		}
	}
	
	public boolean checkKey(int i){
		if(delay[i]<=0 && keys.get(i)){
			delay[i] = max_delay[i];
			return true;
		}
		return false;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		keys.set(e.getKeyCode());
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		keys.clear(e.getKeyCode());
		if(e.getKeyCode() == settings.USE_HEALTHPOT)
			delay[settings.USE_HEALTHPOT] = 0;
		if(e.getKeyCode() == settings.USE_MANAPOT)
			delay[settings.USE_MANAPOT] = 0;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		if (keys.get(27)) {
			if (timer.isRunning()) {
				timer.stop();
			} else {
				timer.start();
			}
		}

	}

	public GameContent getLevel() {
		return level;
	}

	public void setLevel(GameContent level) {
		this.level = level;
	}

	private void handleCollision(GameElement active, GameElement passive){
		//TODO generate GameEvents
		GameEvent e = new GameEvent(passive, EventType.COLLISION, this);
		active.GameEventPerformed(e);
		e = new GameEvent(active, EventType.COLLISION, this);
		passive.GameEventPerformed(e);
	}

	public boolean moveElement(GameElement e, Vector2d direction){
		if(e.type.contains(ElementType.MOVABLE)){ 
			//			System.out.println("test" + collisioncheck.type.toString());
			HashSet<GameElement> collides = new HashSet<GameElement>();
			e.setPosition(e.position.add(new Vector2d(direction.getX(), 0)));
			for(GameElement collisioncheck : level.getGameElements()){
				if(e.collision(collisioncheck)){
					if(!collisioncheck.type.contains(ElementType.WALKABLE)){
						e.setPosition(e.position.add(new Vector2d(-direction.getX(), 0)));
					}
					collides.add(collisioncheck);
				}
			}
			//if(level.getGameElements().contains(e)){
			e.setPosition(e.position.add(new Vector2d(0, direction.getY())));
			for(GameElement collisioncheck : level.getGameElements()){
				if(e.collision(collisioncheck)){
					if(!collisioncheck.type.contains(ElementType.WALKABLE)){
						e.setPosition(e.position.add(new Vector2d(0, -direction.getY())));
					}
					collides.add(collisioncheck);
				}
			}
			for(GameElement col : collides){
				handleCollision(e, col);
			}
			//}
			//e.setPosition(direction.add(e.position));
			return true;
		}
		else
			return false;
	}

	public boolean teleportElement(GameElement e, Vector2d position){
		e.setPosition(position);
		return false;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO: abfragen, welche Bits gesetzt sind und entsprechend handeln
//		System.out.println(System.currentTimeMillis()-time);
//		time = System.currentTimeMillis();
		reduceDelay();
		player = (Player)level.getPlayer();
		Vector2d position = player.getPosition();
		if(!(direction.getX() == 0 && direction.getY() == 0)){
			lastDirection = direction;
		}
		direction = new Vector2d(0,0);
		
		for(Active active : level.getActives()){
			active.interaction(this, settings, keys);
		}
		
		if (checkKey(100)) {// cheat left
			player.setPosition(position.addX(-10));
			System.out.println("CHEAT LEFT");
		}		
		if (checkKey(104)) {// cheat up
			player.setPosition(position.addY(-10));
			System.out.println("CHEAT UP");
		}
		if (checkKey(98)) {// cheat down
			player.setPosition(position.addY(10));
			System.out.println("CHEAT DOWN");
		}
		if (checkKey(102)) {// cheat right
			player.setPosition(position.addX(10));
			System.out.println("CHEAT RIGHT");
		}
		if (checkKey(101)) {// cheat Leben
			player.setHealth(player.getHealth()+1000);
			System.out.println("CHEAT Leben");
		}

		if (checkKey(103)) {// position output
			System.out.println("x= " + player.getPosition().getX() + "y= " + player.getPosition().getY());
		}

		if (checkKey(99)) {// Exit
			if (level.getExit() != null){
				level.getPlayer().setPosition(level.getExit().getPosition().addX(10).addY(60));}
			System.out.println("CHEAT EXIT");
		}
		if (checkKey(37)) {// left arrow
			direction = direction.addX(-1);
		}
		if (checkKey(38)) {// up arrow
			direction = direction.addY(-1);
		}
		if (checkKey(39)) {// right arrow
			direction = direction.addX(1);
		}
		if (checkKey(40)) {// down arrow
			direction = direction.addY(1);
		}


		if (checkKey(83)) { // s
			keys.clear();
			if (level.getPlayer() != null) {
				if (shop == null) {
					// initialize shop
					shop = new DirtyShopSystem((Player)level.getPlayer());
					setmoney(Money+1000);
					shop.gui(shop.getvermoegen());
				}
				System.out.println("Shop Visable you have " + player.getMoney() + " Geld");
				shop.startDirtyShop();
				player.setMoney(shop.getvermoegen());
			}
		}

		
		if (keys.get(KeyEvent.VK_Q)){// q (fire bolt)
			System.out.println(delay[KeyEvent.VK_Q]);
			if(delay[KeyEvent.VK_Q] <= 0 && player.reduceMana(8, this)){
				delay[KeyEvent.VK_Q] = 250;

				Vector2d pos = new Vector2d(position.add(player.size.mul(0.5)).add(new Vector2d(-5, -5)));
				if(lastDirection.getX() > 0)
					pos = pos.add(new Vector2d(player.size.getX()-2,0));
				if(lastDirection.getX() < 0)
					pos = pos.add(new Vector2d(-player.size.getX()+2,0));
				if(lastDirection.getY() > 0)
					pos = pos.add(new Vector2d(0,player.size.getX()-2));
				if(lastDirection.getY() < 0)
					pos = pos.add(new Vector2d(0,-player.size.getX()+2));
				FireBolt tmp = new FireBolt(pos, new Vector2d(10, 10));
				tmp.setDirection(lastDirection.mul(1));
				level.addGameElement(tmp);
			}
		}
		if (keys.get(KeyEvent.VK_W)){// w (ice bolt)
			System.out.println(delay[KeyEvent.VK_W]);
			if(delay[KeyEvent.VK_W] <= 0 && player.reduceMana(8, this)){
				delay[KeyEvent.VK_W] = 250;

				Vector2d pos = new Vector2d(position.add(player.size.mul(0.5)).add(new Vector2d(-5, -5)));
				if(lastDirection.getX() > 0)
					pos = pos.add(new Vector2d(player.size.getX()-2,0));
				if(lastDirection.getX() < 0)
					pos = pos.add(new Vector2d(-player.size.getX()+2,0));
				if(lastDirection.getY() > 0)
					pos = pos.add(new Vector2d(0,player.size.getX()-2));
				if(lastDirection.getY() < 0)
					pos = pos.add(new Vector2d(0,-player.size.getX()+2));
				IceBolt tmp = new IceBolt(pos, new Vector2d(10, 10));
				tmp.setDirection(lastDirection.mul(1));
				level.addGameElement(tmp);
			}
		}
		if (((Player) player).getHealth()<=0){
			startMainMenu();
		}
		if (e.getActionCommand() == "Timer"){
			GameElement tmpRem = null;
			for(GameElement element : level.getGameElements()){
				GameEvent event = new GameEvent(null, EventType.TIMER, this);
				element.GameEventPerformed(event);
				if(element.size.getX() == 0 || element.size.getY() == 0){
					tmpRem = element;
				}
			}
			if (tmpRem != null) {
				level.removeElement(tmpRem);
			}
			app.camera.repaint();
//			System.out.println("Timediff " + (System.currentTimeMillis() - time));
		}
	}
	
	public void addGameElement(GameElement element){
		level.addGameElement(element);
	}

	public Vector2d getCheckPoint() {
		return checkPoint;
	}

	public void setCheckPoint(Vector2d checkPoint) {
		this.checkPoint = checkPoint;
	}
	
	public void addmoney(int amount){
		shop.setvermoegen(shop.getvermoegen() + shop.getvermoegen());
	}
	
	public void setmoney(int amount){
		if (shop == null){
			this.Money=amount;
		}
		else {
			this.Money=amount;
			shop.setvermoegen(Money);
		}
	}
	public int getmoney(){
		if (shop != null){
			Money =shop.getvermoegen();
		}
		return Money;
	}

	public LinkedList<GameObject> getinventory() {
		return player.getInventar();	
	}

	@Deprecated
	public void setinventory(LinkedList<GameObject> Inventar) {
		  this.Inventar = Inventar;
	}
	
	public void startMainMenu(){
		app.cp.removeAll();
		app.cp.validate();
		app.gameContent = new GameContent(this);
		app.loader = new LevelLoader(app.gameContent, app);
		this.timer.stop();
		app.startMainMenu();
	}

	public void lost(Player player) {
		startMainMenu();
	}

}
