package battleship;

public class Gui {
	public final String CYAN_TEXT = "\u001B[36m";
    public final String GREEN_TEXT = "\u001B[32m";
    public final String YELLOW_TEXT = "\u001B[33m";
    public final String PURPLE_TEXT = "\u001B[35m";
    public final String BLUE_TEXT = "\u001B[34m";
    public final String RED_TEXT = "\u001B[31m";
    public final String ANSCI_RESET = "\u001B[0m";
    public final String NEUTRAL_MARKER = "\u001B[34mW\u001B[0m";
    public final String HIT_MARKER = "\u001B[31mX\u001B[0m";
    public final String MISS_MARKER = "O";
    public final java.util.Scanner sc = new java.util.Scanner(System.in);
	
	public void showWelcomeScreen() {
		System.out.print(PURPLE_TEXT);
		System.out.println("Welcome to battleship!");
		System.out.println("You will play against me, the computer and try to sink my ships.");
		System.out.println("The goal is to sink all ships with as few guesses as possible.");
		System.out.print(ANSCI_RESET);
	}
	
	public String getPlayerName() {
		System.out.print(CYAN_TEXT);
		System.out.println("\n\nPlease enter your name: ");
		System.out.print(ANSCI_RESET);
		return sc.nextLine();
	}
    
	public void showBoard(Board board) {
		StringBuilder sb = new StringBuilder();
		sb.append("\n\n\n\t");
		for (int i = 0; i < board.getSize(); i++) {
			sb.append("  " + (i + 1) + "  ");
		}
		sb.append("\n");
		for (int x = 0; x < board.getSize(); x++) {
			// TODO: avoid fixed values like this -> I want to make larger squares in the game
			for (int i = 0; i < 3; i++) {
				if (i == 1) {
					sb.append((x + 1) + " \t");
				} else {
					sb.append("\t");
				}
				
				for (int y = 0; y < board.getSize(); y++) {
					// TODO: create method in board to handover coordinates? Seems like Gui knows a lot about Board.board
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
	
	public String[] getPlayerGuess() {
		System.out.print(CYAN_TEXT);
		System.out.println("\n\nPlease enter the coordinates you want to strike as follows:");
		System.out.println("x y");
		return sc.nextLine().split(" ");
	}
	
	public void displayInvalidInput() {
		System.out.print(RED_TEXT);
		System.out.println("\n\nPlease enter valid input!");
		System.out.println(ANSCI_RESET);
		sleep(2000);
	}
	
	//TODO: sleep so that the text appears longer before the board is shown
	public void displayMiss() {
		System.out.print(PURPLE_TEXT);
		System.out.println("\n\nMiss!");
		System.out.print(ANSCI_RESET);
		sleep(1000);
	}
	
	public void displaySillyGuess() {
		System.out.print(RED_TEXT);
		System.out.println("\n\nHow embarrassing! You already sent a torpedo there.");
		System.out.print(ANSCI_RESET);
		sleep(2000);
	}
	
	public void displayHit() {
		System.out.print(GREEN_TEXT);
		System.out.println("\n\nWoohoo! You got a direct hit!");
		System.out.print(ANSCI_RESET);
		sleep(2000);
	}
	
    public void showShipSunk(Ship ship) {
		System.out.print(RED_TEXT);
		System.out.println("\n\nYou sank my " + ship.getType() + "!");
		System.out.print(ANSCI_RESET);
		sleep(2000);
	}
    
    public void displayGameOver(Player player) {
    	System.out.print(GREEN_TEXT);
    	System.out.println("\n\nGame over! All ships sunk.");
    	System.out.println("It took you " + player.getTriesCount() + " tries to sink all ships.");
    	sleep(2000);
    	System.out.print(ANSCI_RESET);
    }
    
    public void sleep(int milliseconds) {
    	try {
			Thread.sleep(milliseconds);
		} catch (InterruptedException e) {
			return;
		}
    }
    
    public boolean getPlayAgain() {
    	System.out.print(CYAN_TEXT);
    	System.out.println("\n\nWould you like to play again?");
    	System.out.println("Type y to play again, any other key to end.");
    	System.out.print(ANSCI_RESET);
    	String input = sc.nextLine().toLowerCase();
    	if (input == "y") {
    		return true;
    	} else {
    		return false;
    	}
    }
    
    public void displayThanksForPlaying() {
    	System.out.print(CYAN_TEXT);
    	System.out.println("\n\nThanks so much for playing!");
    	System.out.print(ANSCI_RESET);
    }
}
