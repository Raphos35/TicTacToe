package br.com.softblue.tictactoe.core;

import java.io.IOException;

import br.com.softblue.tictactoe.Constantes;
import br.com.softblue.tictactoe.score.FileScoreManager;
import br.com.softblue.tictactoe.score.ScoreManager;
import br.com.softblue.tictactoe.ui.UI;

public class Game {

	private Board board = new Board();
	private Player[] players = new Player[Constantes.SYMBOL_PLAYER.length];
	private int currentPlayerIndex = -1;
	private ScoreManager scoreManager;
	
	public void play() throws IOException {
		scoreManager = createScoreManager();
		
		UI.printGameTitle();
		
		for (int i = 0; i < players.length; i++) {
			players[i] = createPlayer(i);
		}
		
		boolean gameEnded = false;
		Player currentPlayer = nextPlayer();
		Player winner = null;
		
		while(!gameEnded) {
			board.print();
			
			boolean sequenceFound;
			try {
				 sequenceFound = currentPlayer.play();
			
			} catch (InvalidMoveException e) {
				UI.printText("ERRO: " + e.getMessage());
				continue;
			}
			
			if (sequenceFound) {
				gameEnded = true;
				winner = currentPlayer;
			
			} else if (board.isFull()) {
				gameEnded = true;
			
			} else {
				currentPlayer = nextPlayer();
			}
		}
		
		if (winner == null) {
			UI.printText("O jogo terminou empatado");
		
		} else {
			UI.printText("O jogador '" + winner.getName() + "' venceu o jogo!");
			
			scoreManager.saveScore(winner);
		}
		
		board.print();
		UI.printText("Fim do Jogo!");
	}
	
	private Player createPlayer(int index) {
		String name = UI.readInput("Jogador " + (index + 1) + " =>");
		char symbol = Constantes.SYMBOL_PLAYER[index];
		Player player = new Player(name, board, symbol);
		
		Integer score = scoreManager.getScore(player);
		
		if (score != null) {
			UI.printText("O jogador '" + player.getName() + "' j? possui " + score + " vit?ria(s)!");
		}
		
		UI.printText("O jogador '" + name + "' vai usar o s?mbolo '" + symbol + "'");
		
		return player;
	}
	
	private Player nextPlayer() {
		/*
		currentPlayerIndex++;
		
		if (currentPlayerIndex >= players.length) {
			currentPlayerIndex = 0;
		}
		*/
		
		currentPlayerIndex = (currentPlayerIndex + 1) % players.length;
		return players[currentPlayerIndex];
	}
	
	private ScoreManager createScoreManager() throws IOException {
		return new FileScoreManager();
	}
}
	

