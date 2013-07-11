package dungeonCrawler.GameElements;

public class Inventar {
	
	private int Armor;
	private int Bow;
	private int Bullet;
	private int Mana;
	
	private int Money;
	private int Health;
	
	
	public void initInventar(){
		this.Armor=0;
		this.Bow=0;
		this.Bullet=0;
		this.Mana=0;
		
		this.Money=0;
		this.Health=0;

	}
	
	public void inventar(String name, int number){
		if     (name == "Armor")this.Armor =this.Armor +number;
		else if(name=="Bow")    this.Bow   =this.Bow   +number; 
		else if(name=="Bullet") this.Bullet=this.Bullet+number; 
		else if(name=="Mana")   this.Mana  =this.Mana  +number; 
		else if(name=="Money" ) this.Money =this.Money +number; 
		else if(name=="Health") this.Health=this.Health+number; 
		else System.err.println("Unknown operator " + name );
	}
	
	public void printInventar(String name, int number){
		if 	   (name=="Armor" )System.out.println("Armor: "+this.Armor);
		else if(name=="Bow"   )System.out.println("Bow:   "+this.Bow  );
		else if(name=="Bullet")System.out.println("Bullet:"+this.Bullet);
		else if(name=="Mana"  )System.out.println("Mana:  "+this.Mana  );
		else if(name=="Money" )System.out.println("Money: "+this.Money );
		else if(name=="Health")System.out.println("Health:"+this.Health);
		else System.err.println( "Unknown operator " + name );
		
		}
		
	

}
