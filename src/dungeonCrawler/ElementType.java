package dungeonCrawler;

import java.util.EnumSet;

/**Element types
 */
public enum ElementType {
	WALKABLE, 
	MOVABLE, 
	ACTIVE, // (can move itself)
	PASSIVE, // (can be moved)
	IMMOVABLE;
	
/*	public static final EnumSet<ElementType> ACTIVE_MOVABLE = EnumSet.of(MOVABLE, ACTIVE);
	public static final EnumSet<ElementType> PASSIVE_MOVABLE = EnumSet.of(MOVABLE, PASSIVE);
	*/
	public static final boolean isMovable(EnumSet<ElementType> set){
		return set.contains(MOVABLE);
	}
}


