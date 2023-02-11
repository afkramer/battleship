package battleship;

public class Board {
	int widthAndHeight;
	Ship[] ships;
	int[][] board;
	int hitMarker = -1;
	int missMarker = -2;
	
	public Board(int widthAndHeight) {
		this.widthAndHeight = widthAndHeight;
		this.board = new int[widthAndHeight][widthAndHeight];
		initializeShips();
	}
	
	public void initializeShips() {
		ships = new Ship[5];
		ships[0] = new Carrier();
		ships[1] = new Battleship();
		ships[2] = new Cruiser();
		ships[3] = new Submarine();
		ships[4] = new PatrolBoat();
		
		placeShips();
	}
	
	public void placeShips() {
		// TODO: loop through each of the ships
		// Based on the size of the ship, set coordinates
		// Ensure that the coordinates don't conflict with other boats
		// But do I need to track the ship on the board and also within the ships coordinates array?
	}
}
