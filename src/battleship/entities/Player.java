package battleship.entities;


public class Player {
	private String name;
	private int triesCount = 0;
	
	public Player(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	public int getTriesCount() {
		return triesCount;
	}
	
	public void incrementTriesCount() {
		triesCount++;
	}
	
	public void resetTriesCount() {
		triesCount = 0;
	}
	
}
