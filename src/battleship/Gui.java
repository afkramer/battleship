package battleship;

public class Gui {
	public final String CYAN_TEXT = "\u001B[36m";
    public final String GREEN_TEXT = "\u001B[32m";
    public final String YELLOW_TEXT = "\u001B[33m";
    public final String PURPLE_TEXT = "\u001B[35m";
    public final String BLUE_TEXT = "\u001B[34m";
    public final String RED_TEXT = "\u001B[31m";
    public final String ANSCI_RESET = "\u001B[0m";
    public final java.util.Scanner sc = new java.util.Scanner(System.in);
	
	public void showWelcomeScreen() {
		System.out.print(BLUE_TEXT);
		System.out.println("Welcome to battleship!");
		System.out.println("You will play against the computer to sink their ships.");
		System.out.println("The goal is to sink all the ships with as few guesses as possible.");
		System.out.print(ANSCI_RESET);
	}
	
	public String getPlayerName() {
		System.out.print(CYAN_TEXT);
		System.out.println("\n\nPlease enter your name: ");
		return sc.nextLine();
	}
    
	public void showBoard(Board board) {
		
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
	}
	
    public void showShipSunk(Ship ship) {
		System.out.print(RED_TEXT);
		System.out.println("\n\nYou sank my " + ship.getType());
		System.out.print(ANSCI_RESET);
	}
    
    public void displayGameOver(Player player) {
    	System.out.print(GREEN_TEXT);
    	System.out.println("\n\nGame over! All ships sunk.");
    	System.out.println("It took you " + " tries to sink all ships.");
    	System.out.print(ANSCI_RESET);
    }
    
    public boolean getPlayAgain() {
    	System.out.print(CYAN_TEXT);
    	System.out.println("\n\nWould you like to play again?");
    	System.out.println("Type y to play again, any other key to end.");
    	System.out.print(ANSCI_RESET);
    	if (sc.nextLine().toLowerCase() == "y") {
    		return true;
    	} else {
    		return false;
    	}
    }
}
