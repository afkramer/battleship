package battleship;

public class Player {
	private String name;
	private int triesCount = 0;
	Gui gui = new Gui();
	
	public Player(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	public int getTriesCount() {
		return triesCount;
	}
	
	public void takeTurn(Board board) {
		gui.showBoard(board);
	}
	
	public void resetTriesCount() {
		triesCount = 0;
	}
	
}
