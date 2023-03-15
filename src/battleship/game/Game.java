package battleship.game;

import battleship.entities.Board;
import battleship.entities.Player;
import battleship.view.Gui;

public class Game {
	
	public void run() {
		Board board = new Board();
		Player player;
		
		Gui.showWelcomeScreen();
		player = new Player(Gui.getPlayerName());
		
		while(true) {
			board.takeTurn(player);
			
			if(board.isGameOver()) {
				Gui.displayGameOver(player);
				
				if (Gui.getPlayAgain()) {
					board = new Board();
					player.resetTriesCount();
				} else {
					Gui.displayThanksForPlaying();
					break;
				}
			}
			
		}
	}
	

}
