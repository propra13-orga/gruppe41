package dungeonCrawler.Network.Lounge;

public class State {
	private boolean ready = false;
	private int playerID = -1;

	public boolean isReady() {
		return ready;
	}

	public void setReady(boolean ready) {
		this.ready = ready;
	}

	public int getPlayerID() {
		return playerID;
	}

	public void setPlayerID(int playerID) {
		this.playerID = playerID;
	}

}
