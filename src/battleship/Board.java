package battleship;

public class Board {
	int size;
	Ship[] ships;
	int[][] board;
	int neutralMarker = 0;
	int hitMarker = -1;
	int missMarker = -2;
	Gui gui = new Gui();
	
	public Board(int widthAndHeight) {
		this.size = widthAndHeight;
		this.board = new int[widthAndHeight][widthAndHeight];
		initializeShips();
	}
	
	public int getSize() {
		return size;
	}
	
	public int[][] getBoard(){
		return board;
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
		// TODO: loop through each of the ships
		// Based on the size of the ship, set coordinates
		// Ensure that the coordinates don't conflict with other boats
		// But do I need to track the ship on the board and also within the ships coordinates array?
		for (int i = 0; i < ships.length; i++) {
			generateShipCoordinates(ships[i]);
		}
	}
	
	public void generateShipCoordinates(Ship ship) {
		//TODO: is 0 == false in Java? Use mod 2 to either get 0 or 1
		boolean isHorizontal = Math.round(Math.random() * 10) > 5;
		while (true) {
			int startCoordX = (int) Math.round(Math.random() * (size - 1));
			int startCoordY = (int) Math.round(Math.random() * (size - 1));
			
			if (isAvailablePosition(ship.getSize(), isHorizontal, startCoordX, startCoordY)) {
				setShipCoordinates(ship, isHorizontal, startCoordX, startCoordY);
				break;
			}
		}
	}
	
	public boolean isAvailablePosition(int shipSize, boolean isHorizontal, int startCoordX, int startCoordY) {
		for (int i = 0; i < shipSize; i++) {
			if (isHorizontal) {
				if(startCoordY + i >= size) {
					return false;
				} else if (board[startCoordX][startCoordY + i] != 0) {
					return false;
				}
			} else {
				if(startCoordX + i >= size) {
					return false;
				} else if (board[startCoordX + i][startCoordY] != 0) {
					return false;
				}
			}
		}
		return true;
	}
	
	public void setShipCoordinates(Ship ship, boolean isHorizontal, int startCoordX, int startCoordY) {
		for (int i = 0; i < ship.getSize(); i++) {
			if (isHorizontal) {
				board[startCoordX][startCoordY + i] = ship.getMarker(); 
			} else {
				board[startCoordX + i][startCoordY] = ship.getMarker();
			}
		}
	}
	
	public void processGuess(int[] coordinatesToHit) {
		int x = coordinatesToHit[0];
		int y = coordinatesToHit[1];
		if (board[x][y] == neutralMarker) {
			board[x][y] = missMarker;
			gui.displayMiss();
		} else if (board[x][y] == hitMarker || board[x][y] == missMarker) {
			gui.displaySillyGuess();
		} else {
			gui.displayHit();
			board[x][y] = hitMarker;
			checkNewShipsSunk();
		}
	}
	
	public void checkNewShipsSunk() {
		for (int i = 0; i < ships.length; i++) {
			// Only process ships that haven't been marked as sunk yet
			if (!ships[i].getIsSunk()) {
				if (!isShipPresent(ships[i])) {
					ships[i].setIsSunk(true);
					gui.showShipSunk(ships[i]);
				}
			}
		}
	}
	
	public boolean isShipPresent(Ship ship) {
		for (int x = 0; x < size; x++) {
			for (int y = 0; y < size; y++) {
				if (board[x][y] == ship.getMarker()) {
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean isGameOver() {
		for (int x = 0; x < size; x++) {
			for (int y = 0; y < size; y++) {
				if (board[x][y] != neutralMarker && board[x][y] != hitMarker && board[x][y] != missMarker) {
					return false;
				}
			}
		}
		return true;
	}
}
