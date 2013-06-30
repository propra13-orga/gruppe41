package dungeonCrawler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.BitSet;
import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.Timer;

import dungeonCrawler.GameElements.Bow;
import dungeonCrawler.GameElements.Bullet;
import dungeonCrawler.GameElements.CheckPoint;
import dungeonCrawler.GameElements.Enemy;
import dungeonCrawler.GameElements.Exit;
import dungeonCrawler.GameElements.Healthpot;
import dungeonCrawler.GameElements.Manapot;
import dungeonCrawler.GameElements.Moneypot;
import dungeonCrawler.GameElements.NPC;
import dungeonCrawler.GameElements.Player;
import dungeonCrawler.GameElements.Spell;
import dungeonCrawler.GameElements.Trap;
import dungeonCrawler.GameElements.Wall;

public class GameLogic implements KeyListener, ActionListener {

	private Vector2d direction = new Vector2d(0,0);
	private Vector2d lastDirection = new Vector2d(0,1);
	private Vector2d checkPoint = new Vector2d(0,0);
	private int[] delay = new int[1000];
	private GameContent level;
	private BitSet keys;
	protected Timer timer;
	public App app;
	Player player;
	public DirtyShopSystem shop = null;
	public int Money;
	public LinkedList<GameObject> Inventar = new LinkedList<GameObject>();	
	private Vector2d startpos= new Vector2d(0,0);
	private boolean setze=false; // für editor, wenn variable gesetzt ist, dann nochmal drücken um das gameelement hinzu zu fügen
	public File file;

	public GameLogic(App app) {
		// TODO Auto-generated constructor stub
		keys = new BitSet();
		keys.clear();
		timer = new Timer(10, this);
		timer.setActionCommand("Timer");
		timer.stop();
		this.app = app;
		Money = 0;
	}
	
	
	
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
		
		
		
		
		
		if (app.editmode==true){
			 if(e.getKeyCode()==87){
				 int sx = startpos.getX();
				 int sy = startpos.getY();
				 int px = level.getPlayer().getPosition().getX();
				 int py = level.getPlayer().getPosition().getY();
				 System.out.println("w");
				 if (!setze) { startpos=level.getPlayer().getPosition(); setze=true;System.out.println("startpos gesetzt");}
				 else if (px> sx && py>sy){
					 level.removeElement(level.getPlayer());
					 level.addGameElement(new Wall(startpos,new Vector2d(px-sx,py-sy)));
				     setze = false;
				     level.addGameElement(player);
			 }
				 
				 else if (px< sx && py>sy){
					 level.removeElement(level.getPlayer());
					 level.addGameElement(new Wall(new Vector2d(px,sy),new Vector2d(sx-px,py-sy)));
				     setze = false;
				     level.addGameElement(player);
			 }
				 else if (px< sx && py<sy){
					 level.removeElement(level.getPlayer());
					 level.addGameElement(new Wall(new Vector2d(px,py),new Vector2d(sx-px,sy-py)));
				     setze = false;
				     level.addGameElement(player);
			 }
				 else if (px> sx && py<sy){
					 level.removeElement(level.getPlayer());
					 level.addGameElement(new Wall(new Vector2d(sx,py),new Vector2d(px-sx,sy-py)));
				     setze = false;
				     level.addGameElement(player);
			 }
			 }
			 
			 if(e.getKeyCode()==69){
				 int sx = startpos.getX();
				 int sy = startpos.getY();
				 int px = level.getPlayer().getPosition().getX();
				 int py = level.getPlayer().getPosition().getY();
				 System.out.println("w");
				 if (!setze) { startpos=level.getPlayer().getPosition(); setze=true;System.out.println("startpos gesetzt");}
				 else if (px> sx && py>sy){
					 level.removeElement(level.getPlayer());
					 level.addGameElement(new Exit(startpos,new Vector2d(px-sx,py-sy)));
				     setze = false;
				     level.addGameElement(player);
			 }
				 
				 else if (px< sx && py>sy){
					 level.removeElement(level.getPlayer());
					 level.addGameElement(new Exit(new Vector2d(px,sy),new Vector2d(sx-px,py-sy)));
				     setze = false;
				     level.addGameElement(player);
			 }
				 else if (px< sx && py<sy){
					 level.removeElement(level.getPlayer());
					 level.addGameElement(new Exit(new Vector2d(px,py),new Vector2d(sx-px,sy-py)));
				     setze = false;
				     level.addGameElement(player);
			 }
				 else if (px> sx && py<sy){
					 level.removeElement(level.getPlayer());
					 level.addGameElement(new Exit(new Vector2d(sx,py),new Vector2d(px-sx,sy-py)));
				     setze = false;
				     level.addGameElement(player);
			 }
			 }
			 
			 
			 if(e.getKeyCode()==84){
				 int sx = startpos.getX();
				 int sy = startpos.getY();
				 int px = level.getPlayer().getPosition().getX();
				 int py = level.getPlayer().getPosition().getY();
				 System.out.println("w");
				 if (!setze) { startpos=level.getPlayer().getPosition(); setze=true;System.out.println("startpos gesetzt");}
				 else if (px> sx && py>sy){
					 level.removeElement(level.getPlayer());
					 level.addGameElement(new Trap(startpos,new Vector2d(px-sx,py-sy)));
				     setze = false;
				     level.addGameElement(player);
			 }
				 
				 else if (px< sx && py>sy){
					 level.removeElement(level.getPlayer());
					 level.addGameElement(new Trap(new Vector2d(px,sy),new Vector2d(sx-px,py-sy)));
				     setze = false;
				     level.addGameElement(player);
			 }
				 else if (px< sx && py<sy){
					 level.removeElement(level.getPlayer());
					 level.addGameElement(new Trap(new Vector2d(px,py),new Vector2d(sx-px,sy-py)));
				     setze = false;
				     level.addGameElement(player);
			 }
				 else if (px> sx && py<sy){
					 level.removeElement(level.getPlayer());
					 level.addGameElement(new Trap(new Vector2d(sx,py),new Vector2d(px-sx,sy-py)));
				     setze = false;
				     level.addGameElement(player);
			 }
			 }
			 
			 		 
			 
			 
			 if(e.getKeyCode()==67){
				 int sx = startpos.getX();
				 int sy = startpos.getY();
				 int px = level.getPlayer().getPosition().getX();
				 int py = level.getPlayer().getPosition().getY();
				 System.out.println("w");
				 if (!setze) { startpos=level.getPlayer().getPosition(); setze=true;System.out.println("startpos gesetzt");}
				 else if (px> sx && py>sy){
					 level.removeElement(level.getPlayer());
					 level.addGameElement(new Enemy(startpos,new Vector2d(px-sx,py-sy)));
				     setze = false;
				     level.addGameElement(player);
			 }
				 
				 else if (px< sx && py>sy){
					 level.removeElement(level.getPlayer());
					 level.addGameElement(new Enemy(new Vector2d(px,sy),new Vector2d(sx-px,py-sy)));
				     setze = false;
				     level.addGameElement(player);
			 }
				 else if (px< sx && py<sy){
					 level.removeElement(level.getPlayer());
					 level.addGameElement(new Enemy(new Vector2d(px,py),new Vector2d(sx-px,sy-py)));
				     setze = false;
				     level.addGameElement(player);
			 }
				 else if (px> sx && py<sy){
					 level.removeElement(level.getPlayer());
					 level.addGameElement(new Enemy(new Vector2d(sx,py),new Vector2d(px-sx,sy-py)));
				     setze = false;
				     level.addGameElement(player);
			 }
			 }
			 
			 
			 if(e.getKeyCode()==71){
				 int sx = startpos.getX();
				 int sy = startpos.getY();
				 int px = level.getPlayer().getPosition().getX();
				 int py = level.getPlayer().getPosition().getY();
				 System.out.println("w");
				 if (!setze) { startpos=level.getPlayer().getPosition(); setze=true;System.out.println("startpos gesetzt");}
				 else if (px> sx && py>sy){
					 level.removeElement(level.getPlayer());
					 level.addGameElement(new Moneypot(startpos,new Vector2d(px-sx,py-sy)));
				     setze = false;
				     level.addGameElement(player);
			 }
				 
				 else if (px< sx && py>sy){
					 level.removeElement(level.getPlayer());
					 level.addGameElement(new Moneypot(new Vector2d(px,sy),new Vector2d(sx-px,py-sy)));
				     setze = false;
				     level.addGameElement(player);
			 }
				 else if (px< sx && py<sy){
					 level.removeElement(level.getPlayer());
					 level.addGameElement(new Moneypot(new Vector2d(px,py),new Vector2d(sx-px,sy-py)));
				     setze = false;
				     level.addGameElement(player);
			 }
				 else if (px> sx && py<sy){
					 level.removeElement(level.getPlayer());
					 level.addGameElement(new Moneypot(new Vector2d(sx,py),new Vector2d(px-sx,sy-py)));
				     setze = false;
				     level.addGameElement(player);
			 }
			 }
			 
			 if(e.getKeyCode()==83){
				 int sx = startpos.getX();
				 int sy = startpos.getY();
				 int px = level.getPlayer().getPosition().getX();
				 int py = level.getPlayer().getPosition().getY();
				 System.out.println("w");
					 level.removeElement(level.getPlayer());
					 level.addGameElement(new CheckPoint(new Vector2d(px,py),new Vector2d(30,30)));
				     level.addGameElement(player);

			 }
			 
			 if(e.getKeyCode()==66){
				 int sx = startpos.getX();
				 int sy = startpos.getY();
				 int px = level.getPlayer().getPosition().getX();
				 int py = level.getPlayer().getPosition().getY();
				 System.out.println("w");
	
					 level.removeElement(level.getPlayer());
					 level.addGameElement(new Bow(level.getPlayer().getPosition(),new Vector2d(5,5)));
				     level.addGameElement(player);
			 
			 }
			 
			 
			 if(e.getKeyCode()==81){
				 int sx = startpos.getX();
				 int sy = startpos.getY();
				 int px = level.getPlayer().getPosition().getX();
				 int py = level.getPlayer().getPosition().getY();
				 System.out.println("w");
	
					 level.removeElement(level.getPlayer());
					 level.addGameElement(new Wall(level.getPlayer().getPosition(),new Vector2d(5,5)));
				     level.addGameElement(player);
			 
			 }
			 
			 
			 if(e.getKeyCode()==78){
				 int sx = startpos.getX();
				 int sy = startpos.getY();
				 int px = level.getPlayer().getPosition().getX();
				 int py = level.getPlayer().getPosition().getY();
				 System.out.println("w");
				 if (!setze) { startpos=level.getPlayer().getPosition(); setze=true;System.out.println("startpos gesetzt");}
				 else if (px> sx && py>sy){
					 level.removeElement(level.getPlayer());
					 level.addGameElement(new NPC(startpos,new Vector2d(px-sx,py-sy)));
				     setze = false;
				     level.addGameElement(player);
			 }
				 
				 else if (px< sx && py>sy){
					 level.removeElement(level.getPlayer());
					 level.addGameElement(new NPC(new Vector2d(px,sy),new Vector2d(sx-px,py-sy)));
				     setze = false;
				     level.addGameElement(player);
			 }
				 else if (px< sx && py<sy){
					 level.removeElement(level.getPlayer());
					 level.addGameElement(new NPC(new Vector2d(px,py),new Vector2d(sx-px,sy-py)));
				     setze = false;
				     level.addGameElement(player);
			 }
				 else if (px> sx && py<sy){
					 level.removeElement(level.getPlayer());
					 level.addGameElement(new NPC(new Vector2d(sx,py),new Vector2d(px-sx,sy-py)));
				     setze = false;
				     level.addGameElement(player);
			 }
			 }
			 
			 
			 
			 if(e.getKeyCode()==72){
				 int sx = startpos.getX();
				 int sy = startpos.getY();
				 int px = level.getPlayer().getPosition().getX();
				 int py = level.getPlayer().getPosition().getY();
				 System.out.println("w");
	
					 level.removeElement(level.getPlayer());
					 level.addGameElement(new Healthpot(level.getPlayer().getPosition(),new Vector2d(5,5)));
				     level.addGameElement(player);
			 
			 }			 
			 
			 if(e.getKeyCode()==77){
				 int sx = startpos.getX();
				 int sy = startpos.getY();
				 int px = level.getPlayer().getPosition().getX();
				 int py = level.getPlayer().getPosition().getY();
				 System.out.println("w");
	
					 level.removeElement(level.getPlayer());
					 level.addGameElement(new Manapot(level.getPlayer().getPosition(),new Vector2d(5,5)));
				     level.addGameElement(player);
			 
			 }
			 
			 
			 
			 
			 
			 
		}
		keys.set(e.getKeyCode());
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		keys.clear(e.getKeyCode());
	}
	private String convertLvltoStr(int currentLevel) {
		String str;
		try {
			str = 0 +  Integer.toString(currentLevel);
			str = str.substring(str.length()-2);
			System.out.println(str);

		} catch (Exception e) {
			return "99";
		}
		return str;
	}
	
	
	private void writeLvl(){
		LinkedList<GameElement> currentelement;
		 file.delete();
		file = new File("Levels"+File.separator+"level" + convertLvltoStr(app.currentLevel) +".lvl");
			FileWriter writer = null;
		    try {
	        	PrintWriter outputstream = new PrintWriter(file);
		        while ((currentelement=level.getGameElements())!=null && level.getPlayer() != currentelement.getFirst()){
		        	outputstream.println(currentelement.element().getName()+","+currentelement.element().getPosition().getX()+","+currentelement.element().getPosition().getY()+","+currentelement.element().getSize().getX()+","+currentelement.element().getSize().getY());
		        	level.removeElement(currentelement.getFirst());
		        	System.out.println(currentelement.element().getName()+","+currentelement.element().getPosition().getX()+","+currentelement.element().getPosition().getY()+","+currentelement.element().getSize().getX()+","+currentelement.element().getSize().getY());
		        	outputstream.flush();
		        }
	        	outputstream.println(currentelement.element().getName()+","+currentelement.element().getPosition().getX()+","+currentelement.element().getPosition().getY()+","+currentelement.element().getSize().getX()+","+currentelement.element().getSize().getY());
	        	System.out.println(currentelement.element().getName()+","+currentelement.element().getPosition().getX()+","+currentelement.element().getPosition().getY()+","+currentelement.element().getSize().getX()+","+currentelement.element().getSize().getY());
	        	outputstream.flush();
		        outputstream.close();
		    } catch (IOException e1) {
		       
		    }		
		 
	}
	
	
	
	private void increaselevels(){
		file = new File("Levels"+File.separator+"level.lvl");
///////////////////////////////////////////////////7
		
		
		
		   FileWriter writer = null;
		    try {
		        writer = new FileWriter(file);
		        writer.write(String.valueOf(app.level));
		    } catch (IOException e1) {
		        e1.printStackTrace(); 
		    } finally {
		        if (writer != null) try { writer.close(); } catch (IOException ignore) {}
		    }
		    System.out.printf("File is located at %s%n", file.getAbsolutePath());				
		
		
		
		
		
		
		
		
//////////////////////////////////////////////////////		
		try {
			file.createNewFile();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
		
		
		
		if (app.editmode==true){
			 if(keys.get(27)){
				    writeLvl();
				    System.out.println("geht weiter");
				 	app.editmode=false;
				 	app.currentLevel=app.level;
				 	app.startGame();
				 	increaselevels();
			     }

		}
		if (app.editmode == false){		
			
			if (keys.get(37)) {// left
				direction=direction.addX(-1);
				System.out.println("LEFT");
			}		
			if (keys.get(38)) {// up
				direction=direction.addY(-1);
				System.out.println("UP");
			}
			if (keys.get(39)) {// right
				direction=direction.addX(1);
				System.out.println("RIGHT");
			}
			if (keys.get(40)) {// down
				direction=direction.addY(1);
				System.out.println("DOWN");
			}
		 if(keys.get(27)){
			       if (timer.isRunning()){
			         timer.stop();
			       }
			       else {
			         timer.start();
			       }
			     }
		if (e.getKeyChar() == 'h') {// "h" for health
			keys.clear(72);
			Iterator<GameObject> it = player.getInventar().iterator();
			boolean b = true;
			GameObject obj;
			while (it.hasNext() && b) {
				obj = it.next();
				if (obj.getClass().getName().equalsIgnoreCase("dungeonCrawler.GameObjects.HealthPotion")) {
					obj.performOn(player);
					player.getInventar().remove(obj);
					b = false;
				}
			}
		}
		if (e.getKeyChar() == 'm') {// "m" for mana
			keys.clear(77);
			Iterator<GameObject> it = player.getInventar().iterator();
			boolean b = true;
			GameObject obj;
			while (it.hasNext() && b) {
				obj = it.next();
				if (obj.getClass().getName().equalsIgnoreCase("dungeonCrawler.GameObjects.ManaPotion")) {
					obj.performOn(player);
					player.getInventar().remove(obj);
					b = false;
				}
			}
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
		if (app.editmode==false){
		if(e.type.contains(ElementType.MOVABLE)){ //TODO call handleCollision only once per GameElement
			//			System.out.println("test" + collisioncheck.type.toString());
			e.setPosition(e.position.add(new Vector2d(direction.getX(), 0)));
			for(GameElement collisioncheck : level.getGameElements()){
				if(e.collision(collisioncheck)){
					if(!collisioncheck.type.contains(ElementType.WALKABLE)){
						e.setPosition(e.position.add(new Vector2d(-direction.getX(), 0)));
					}
					handleCollision(e, collisioncheck); //handle collision (e.g. traps, exit ...)
				}
			}
			//if(level.getGameElements().contains(e)){
			e.setPosition(e.position.add(new Vector2d(0, direction.getY())));
			for(GameElement collisioncheck : level.getGameElements()){
				if(e.collision(collisioncheck)){
					if(!collisioncheck.type.contains(ElementType.WALKABLE)){
						e.setPosition(e.position.add(new Vector2d(0, -direction.getY())));
					}
					handleCollision(e, collisioncheck); //handle collision (e.g. traps, exit ...)
				}
			}
			//}
			//e.setPosition(direction.add(e.position));
			return true;
		}
		else
		
			return false;
	}
		else return true;
		}

	public boolean teleportElement(GameElement e, Vector2d position){
		e.setPosition(position);
		return false;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		player = (Player)level.getPlayer();
		Vector2d position = player.getPosition();
		// TODO: abfragen, welche Bits gesetzt sind und ensprechend handeln


		if (app.editmode==true){
			if (keys.get(100)) {// cheat left
				player.setPosition(position.addX(-10));
				System.out.println("CHEAT LEFT");
			}		
			if (keys.get(104)) {// cheat up
				player.setPosition(position.addY(-10));
				System.out.println("CHEAT UP");
			}
			if (keys.get(98)) {// cheat down
				player.setPosition(position.addY(10));
				System.out.println("CHEAT DOWN");
			}
			if (keys.get(102)) {// cheat right
				player.setPosition(position.addX(10));
				System.out.println("CHEAT RIGHT");
			}
			
			
			
			if (keys.get(37)) {// left
				player.setPosition(position.addX(-1));
				System.out.println("LEFT");
			}		
			if (keys.get(38)) {// up
				player.setPosition(position.addY(-1));
				System.out.println("UP");
			}
			if (keys.get(39)) {// right
				player.setPosition(position.addX(1));
				System.out.println("RIGHT");
			}
			if (keys.get(40)) {// down
				player.setPosition(position.addY(1));
				System.out.println("DOWN");
			}
			
		
		if (keys.get(103)) {// position output
				System.out.println("x= " + player.getPosition().getX() + " ; y= " + player.getPosition().getY());
		}
			
		}
		if (app.editmode==false){

			
			
			
			if(!(direction.getX() == 0 && direction.getY() == 0)){
				lastDirection = direction;
			}
			direction = new Vector2d(0,0);
			if (keys.get(100)) {// cheat left
				player.setPosition(position.addX(-10));
				System.out.println("CHEAT LEFT");
			}		
			if (keys.get(104)) {// cheat up
				player.setPosition(position.addY(-10));
				System.out.println("CHEAT UP");
			}
			if (keys.get(98)) {// cheat down
				player.setPosition(position.addY(10));
				System.out.println("CHEAT DOWN");
			}
			if (keys.get(102)) {// cheat right
				player.setPosition(position.addX(10));
				System.out.println("CHEAT RIGHT");
			}
			if (keys.get(101)) {// cheat Leben
				player.setHealth(player.getHealth()+1000);
				System.out.println("CHEAT Leben");
			}

			if (keys.get(103)) {// position output
				System.out.println("x= " + player.getPosition().getX() + "y= " + player.getPosition().getY());
			}

			if (keys.get(99)) {// Exit
				if (level.getExit() != null){
					level.getPlayer().setPosition(level.getExit().getPosition().addX(10).addY(60));}
				System.out.println("CHEAT EXIT");
			}
			if (keys.get(37)) {// left arrow
				direction = direction.addX(-1);
				System.out.println("LEFT");
			}
			if (keys.get(38)) {// up arrow
				direction = direction.addY(-1);
				System.out.println("UP");
			}
			if (keys.get(39)) {// right arrow
				direction = direction.addX(1);
				System.out.println("RIGHT");
			}
			if (keys.get(40)) {// down arrow
				direction = direction.addY(1);
				System.out.println("DOWN");
			}
			
			
			
			
			
			
			
			
			
			
			
		if (keys.get(83)) { // s
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

		if(delay[32] >= 0){
			delay[32] -= 1;
		}
		if (keys.get(32)){
			if (player.hasBow()) {
				if(delay[32] < 0){
					delay[32] = 70;
				Vector2d pos = new Vector2d(position.add(player.size.mul(0.5)).add(new Vector2d(-5, -5)));
				if(lastDirection.getX() > 0)
					pos = pos.add(new Vector2d(player.size.getX()-2,0));
				if(lastDirection.getX() < 0)
					pos = pos.add(new Vector2d(-player.size.getX()+2,0));
				if(lastDirection.getY() > 0)
					pos = pos.add(new Vector2d(0,player.size.getX()-2));
				if(lastDirection.getY() < 0)
					pos = pos.add(new Vector2d(0,-player.size.getX()+2));
				Bullet tmp = new Bullet(pos, new Vector2d(10, 10));
				tmp.setDirection(lastDirection.mul(3));
				level.addGameElement(tmp);
				}
			}
		}
		if(delay[KeyEvent.VK_Q] >= 0){
			delay[KeyEvent.VK_Q] -= 1;
		}
		if (keys.get(KeyEvent.VK_Q)){
			if(delay[KeyEvent.VK_Q] < 0 && player.reduceMana(8, this)){
				delay[KeyEvent.VK_Q] = 70;

				Vector2d pos = new Vector2d(position.add(player.size.mul(0.5)).add(new Vector2d(-5, -5)));
				if(lastDirection.getX() > 0)
					pos = pos.add(new Vector2d(player.size.getX()-2,0));
				if(lastDirection.getX() < 0)
					pos = pos.add(new Vector2d(-player.size.getX()+2,0));
				if(lastDirection.getY() > 0)
					pos = pos.add(new Vector2d(0,player.size.getX()-2));
				if(lastDirection.getY() < 0)
					pos = pos.add(new Vector2d(0,-player.size.getX()+2));
				Spell tmp = new Spell(pos, new Vector2d(10, 10));
				tmp.setDirection(lastDirection.mul(2));
				level.addGameElement(tmp);
			}
		
		}
		if(!keys.isEmpty()) moveElement(player, direction);
		if (((Player) player).getHealth()<=0){
			app.cp.removeAll();
			app.cp.validate();
			app.gameContent = new GameContent();
			app.loader = new LevelLoader(app.gameContent, app);
			this.timer.stop();
			app.startMainMenu();

		}
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
		}
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
		return this.Inventar;	
	}

	public void setinventory(LinkedList<GameObject> Inventar) {
		  this.Inventar = Inventar;
	}	

}
