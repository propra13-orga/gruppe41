package dungeonCrawler;

/**Damage types
 * @author Tissen
 */
public enum DamageType {
	CONVENTIONAL,
	FIRE,
	ICE;
	public static String toString(DamageType type){
		switch(type){
		case CONVENTIONAL:
			return "CONVENTIONAL";
		case FIRE:
			return "FIRE";
		case ICE:
			return "ICE";
		}
		return "CONVENTIONAL";
	}

	public static DamageType get(String string) {
		switch(string){
		case "CONVENTIONAL":
			return CONVENTIONAL;
		case "FIRE":
			return FIRE;
		case "ICE":
			return ICE;
		}
		return null;
	}
}
