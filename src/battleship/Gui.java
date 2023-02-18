package battleship;

import java.util.Scanner;

public class Gui {
	// Note: Constants have to be static -> otherwise it's just a variable that doesn't change!
	public static final String CYAN_TEXT = "\u001B[36m";
    public static final String GREEN_TEXT = "\u001B[32m";
    public static final String YELLOW_TEXT = "\u001B[33m";
    public static final String PURPLE_TEXT = "\u001B[35m";
    public static final String BLUE_TEXT = "\u001B[34m";
    public static final String RED_TEXT = "\u001B[31m";
    public static final String ANSCI_RESET = "\u001B[0m";
    public static final String NEUTRAL_MARKER = "\u001B[34mW\u001B[0m";
    public static final String HIT_MARKER = "\u001B[31mX\u001B[0m";
    public static final String MISS_MARKER = "O";
    public static final Scanner SC = new Scanner(System.in);
    
    private Gui() {}
	
	public static void showWelcomeScreen() {
		System.out.print(PURPLE_TEXT);
		System.out.println("Welcome to battleship!");
		System.out.println("You will play against me, the computer and try to sink my ships.");
		System.out.println("The goal is to sink all ships with as few guesses as possible.");
		System.out.print(ANSCI_RESET);
	}
	
	public static String getPlayerName() {
		System.out.print(CYAN_TEXT);
		System.out.println("\n\nPlease enter your name: ");
		System.out.print(ANSCI_RESET);
		return SC.nextLine();
	}
    
	public static void showBoard(Board board) {
		int linesPerRow = 3;
		StringBuilder sb = new StringBuilder();
		sb.append("\n\n\n\t");
		for (int i = 0; i < board.getSize(); i++) {
			// Show i + 1 since the user should enter a value between 1 - 10
			sb.append("  " + (i + 1) + "  ");
		}
		sb.append("\n");
		for (int x = 0; x < board.getSize(); x++) {
			for (int i = 0; i < linesPerRow; i++) {
				if (i == (int) linesPerRow / 2) {
					// Show i + 1 since the user should enter a value between 1 - 10
					sb.append((x + 1) + " \t");
				} else {
					sb.append("\t");
				}
				
				for (int y = 0; y < board.getSize(); y++) {
					int coords = board.getBoard()[x][y];
					if (coords == board.getMissMarker()) {
						sb.append(MISS_MARKER + MISS_MARKER + MISS_MARKER + MISS_MARKER + MISS_MARKER);
					} else if (coords == board.getHitMarker()) {
						sb.append(HIT_MARKER + HIT_MARKER + HIT_MARKER + HIT_MARKER + HIT_MARKER);
					} else {
						sb.append(NEUTRAL_MARKER + NEUTRAL_MARKER + NEUTRAL_MARKER + NEUTRAL_MARKER + NEUTRAL_MARKER);
					}
				}
				
				sb.append("\n");
			}
		}
		
		System.out.print(sb);
	}
	
	public static Coordinate getPlayerGuess() {
		String[] rawInput;
		
		while (true) {
			
			System.out.print(CYAN_TEXT);
			System.out.println("\n\nPlease enter the coordinates you want to strike as follows: x y");
			System.out.print(ANSCI_RESET);
			rawInput = SC.nextLine().split(" ");
			
			try {
				Coordinate coord = new Coordinate(Integer.parseInt(rawInput[0]) - 1, Integer.parseInt(rawInput[1]) - 1);
				if (coord.isValid() && rawInput.length == 2) {
					return coord;
				} else {
					displayInvalidInput();
				}
				
			} catch (NumberFormatException nfe) {
				displayInvalidInput();
			}
		} 
	}
	
	public static void displayInvalidInput() {
		System.out.print(RED_TEXT);
		System.out.println("\n\nPlease enter valid input!");
		System.out.println(ANSCI_RESET);
		sleep(2000);
	}
	
	public static void displayMiss() {
		System.out.print(PURPLE_TEXT);
		System.out.println("\n\nMiss!");
		System.out.print(ANSCI_RESET);
		sleep(1000);
	}
	
	public static void displaySillyGuess() {
		System.out.print(RED_TEXT);
		System.out.println("\n\nHow embarrassing! You already sent a torpedo there.");
		System.out.print(ANSCI_RESET);
		sleep(2000);
	}
	
	public static void displayHit() {
		System.out.print(GREEN_TEXT);
		System.out.println("\n\nWoohoo! You got a direct hit!");
		System.out.print(ANSCI_RESET);
		sleep(2000);
	}
	
    public static void showShipSunk(Ship ship) {
		System.out.print(RED_TEXT);
		System.out.println("\n\nYou sank my " + ship.getType() + "!");
		System.out.print(ANSCI_RESET);
		sleep(2000);
	}
    
    public static void displayGameOver(Player player) {
    	System.out.print(GREEN_TEXT);
    	System.out.println("\n\nGame over! All ships sunk.");
    	System.out.println("It took you " + player.getTriesCount() + " tries to sink all ships.");
    	sleep(2000);
    	System.out.print(ANSCI_RESET);
    }
    
    public static void sleep(int milliseconds) {
    	try {
			Thread.sleep(milliseconds);
		} catch (InterruptedException e) {
			return;
		}
    }
    
    public static boolean getPlayAgain() {
    	System.out.print(CYAN_TEXT);
    	System.out.println("\n\nWould you like to play again?");
    	System.out.println("Type y to play again, any other key to end.");
    	System.out.print(ANSCI_RESET);
    	String input = SC.nextLine().toLowerCase();
    	if (input == "y") {
    		return true;
    	} else {
    		return false;
    	}
    }
    
    public static void displayThanksForPlaying() {
    	System.out.print(CYAN_TEXT);
    	System.out.println("\n\nThanks so much for playing!");
    	System.out.print(ANSCI_RESET);
    }
}
