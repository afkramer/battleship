package battleship;

/*
 * Note: 
 * Don't put in options if they aren't going to be used or implemented!
 * Board was originally implemented to allow the constructor to change the size of the board
 * But it wasn't implemented or changed. Therefore it didn't make sense!
 * 
 */

public class Board {
	private int size = Utility.getSize();
	private Ship[] ships;
	private int[][] board;
	private int neutralMarker = 0;
	private int hitMarker = -1;
	private int missMarker = -2;
	
	public Board() {
		this.board = new int[size][size];
		initializeShips();
	}
	
	public int getSize() {
		return size;
	}
	
	public int[][] getBoard(){
		return board;
	}
	
	public int getValue(Coordinate coord) {
		return board[coord.getX()][coord.getY()];
	}
	
	public void setValue(Coordinate coord, int value) {
		board[coord.getX()][coord.getY()] = value;
	}
	
	public int getNeutralMarker() {
		return neutralMarker;
	}
	
	public int getHitMarker() {
		return hitMarker;
	}
	
	public int getMissMarker() {
		return missMarker;
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
		for (int i = 0; i < ships.length; i++) {
			generateShipCoordinates(ships[i]);
		}
	}
	
	public void generateShipCoordinates(Ship ship) {
		boolean isHorizontal = Math.random() > 0.5;
		while (true) {
			Coordinate startCoord = new Coordinate();
			
			if (isAvailablePosition(ship.getSize(), isHorizontal, startCoord)) {
				setShipCoordinates(ship, isHorizontal, startCoord);
				break;
			}
		}
	}
	
	public boolean isAvailablePosition(int shipSize, boolean isHorizontal, Coordinate coord) {
		// Note: in the real world -> preferred to only one return per method!
		// Originally I had written this method to return true or false in each of the conditional branches
		boolean isAvailable;
		// Generate a new coordinate to be able to change it without changing the original coordinate
		Coordinate coordToCheck = new Coordinate(coord.getX(), coord.getY());
		
		for (int i = 0; i < shipSize; i++) {
			if (isHorizontal) {
				coordToCheck.shiftCoord(0, 1);
			} else {
				coordToCheck.shiftCoord(1, 0);
			}
			
			if(!coordToCheck.isValid() || getValue(coordToCheck) != neutralMarker) {
				isAvailable = false;
				break;
			}
		}
		
		isAvailable = true;
		return isAvailable;
	}
	
	public void setShipCoordinates(Ship ship, boolean isHorizontal, Coordinate coord) {
		for (int i = 0; i < ship.getSize(); i++) {
			if (isHorizontal) {
				coord.shiftCoord(0, 1);
				setValue(coord, ship.getMarker());
			} else {
				coord.shiftCoord(1, 0);
				setValue(coord, ship.getMarker());
			}
		}
	}
	
	public void takeTurn(Player player) {
		Gui.showBoard(this);
		processGuess(Gui.getPlayerGuess());
		player.incrementTriesCount();
	}
	
	public void processGuess(Coordinate coordinatesToHit) {
		int currValue = getValue(coordinatesToHit);
		if (currValue == neutralMarker) {
			setValue(coordinatesToHit, missMarker);
			Gui.displayMiss();
		} else if (currValue == hitMarker || currValue == missMarker) {
			Gui.displaySillyGuess();
		} else {
			Gui.displayHit();
			setValue(coordinatesToHit, hitMarker);
			checkNewShipsSunk();
		}
	}
	
	public void checkNewShipsSunk() {
		for (int i = 0; i < ships.length; i++) {
			// Only process ships that haven't been marked as sunk yet
			if (!ships[i].getIsSunk()) {
				if (!isShipPresent(ships[i])) {
					ships[i].setIsSunk(true);
					Gui.showShipSunk(ships[i]);
				}
			}
		}
	}
	
	public boolean isShipPresent(Ship ship) {
		Coordinate coord = new Coordinate();
		for (int x = 0; x < size; x++) {
			coord.setX(x);
			
			for (int y = 0; y < size; y++) {
				coord.setY(y);
				
				if (getValue(coord) == ship.getMarker()) {
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean isGameOver() {
		Coordinate coord = new Coordinate();
		int currValue;
		for (int x = 0; x < size; x++) {
			coord.setX(x);
			
			for (int y = 0; y < size; y++) {
				coord.setY(y);
				currValue = getValue(coord);
				if (currValue != neutralMarker && currValue != hitMarker && currValue != missMarker) {
					return false;
				}
			}
		}
		return true;
	}
}
