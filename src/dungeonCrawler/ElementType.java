package dungeonCrawler;

//import java.util.EnumSet;

public enum ElementType {
	WALKABLE, 
	MOVABLE, 
	ACTIVE, // (can move itself)
	PASSIVE, // (can be moved)
	IMMOVABLE;
	
/*	public static final EnumSet<ElementType> ACTIVE_MOVABLE = EnumSet.of(MOVABLE, ACTIVE);
	public static final EnumSet<ElementType> PASSIVE_MOVABLE = EnumSet.of(MOVABLE, PASSIVE);
	
	public final boolean isMovable(){
		return false;//t.contains(MOVABLE);
	}*/
}


