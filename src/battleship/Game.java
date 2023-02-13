package battleship;

public class Game {
	Board board = new Board(10);
	Gui gui = new Gui();
	Player player;
	
	public void run() {
		gui.showWelcomeScreen();
		player = new Player(gui.getPlayerName());
		while(true) {
			player.takeTurn(board);
			if(board.isGameOver()) {
				gui.displayGameOver(player);
				if (!gui.getPlayAgain()) {
					gui.displayThanksForPlaying();
					break;
				} else {
					board = new Board(10);
					player.resetTriesCount();
				}
			}
			
		}
	}
	

}
