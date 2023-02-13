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
		String[] rawInput;
		int[] guessCoordinates;
		while (true) {
			rawInput = gui.getPlayerGuess();
			if(isInputValid(rawInput)) {
				guessCoordinates = convertInputToCoords(rawInput);
				board.processGuess(guessCoordinates);
				triesCount++;
				return;
			} else {
				gui.displayInvalidInput();
			}
		}
		
	}
	
	public boolean isInputValid(String[] input) {
		if (input.length == 2) {
			try {
				int xCoord = Integer.parseInt(input[0]);
				int yCoord = Integer.parseInt(input[1]);
				if (xCoord >= 1 && xCoord <= 10 && yCoord >= 1 && yCoord <= 10) {
					return true;
				}
			} catch (NumberFormatException nfe) {
				return false;
			}
		}
		return false;
	}
	
	public int[] convertInputToCoords(String[] stringInput) {
		int[] intInput = new int[2];
		// The player inputs the coordinates from 1 - 10, but the array starts at index 0
		intInput[0] = Integer.parseInt(stringInput[0]) - 1;
		intInput[1] = Integer.parseInt(stringInput[1]) - 1;
		return intInput;
	}
	
	public void resetTriesCount() {
		triesCount = 0;
	}
	
}
