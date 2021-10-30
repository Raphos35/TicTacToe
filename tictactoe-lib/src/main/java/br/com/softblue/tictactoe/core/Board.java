package br.com.softblue.tictactoe.core;

import br.com.softblue.tictactoe.Constantes;
import br.com.softblue.tictactoe.ui.UI;

public class Board {
	// Atributo
	private char[][] matrix;

	// Construtor - É um método especial que só é chamando quando um objeto é
	// criado/iniciado
	public Board() {
		matrix = new char[Constantes.BOARD_SIZE][Constantes.BOARD_SIZE];
		clear();
	}

	// Métodos
	public void clear() {
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				matrix[i][j] = ' ';
			}
		}
	}
	//Construção da estrutura do tabuleiro
	public void print() {
		UI.printNewLine();
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				UI.printTextWithNoNewLine(String.valueOf(matrix[i][j]));

				if (j < matrix[i].length - 1) {
					UI.printTextWithNoNewLine(" | ");
				}
			}
			UI.printNewLine();

			if (i < matrix.length - 1) {
				UI.printText("------------");
			}
		}

	}
	//Validação para verificar se o tabuleiro esta cheio
	public boolean isFull() {
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				if (matrix[i][j] == ' ') {
					return false;
				}
			}
		}
		return true;
	}
	//Validação para movimentos feitos/inválidos
	public boolean play(Player player, Move move) throws InvalidMoveException {
		
		int i = move.getI();
		int j = move.getJ();
		
		if (i < 0 || j < 0 || i >= Constantes.BOARD_SIZE || j >= Constantes.BOARD_SIZE ) {
			throw new InvalidMoveException("O intervalo da jogada é inválido");
		}
		
		if (matrix[i][j] != ' ') {
			throw new InvalidMoveException("Essa jogada já foi realizada");
		}
		
		matrix[i][j] = player.getSymbol();
		
		return checkRows(player) || checkCols(player) || checkDiagonal1(player) || checkDiagonal2(player);
		
	}
	//validação para checar as linhas
	private boolean checkRows(Player player) {
		for (int i = 0; i < Constantes.BOARD_SIZE; i++) {
			if (checkRow(i, player)) {
				return true;
			}
		}
		return false;
	}
	//validação para checar a linha
	private boolean checkRow(int i, Player player) {
		char symbol = player.getSymbol();
		
		for (int j = 0; j < Constantes.BOARD_SIZE; j++) {
			if (matrix[i][j] != symbol) {
				return false;
			}
		}
		return true;
	}
	//Validação para checar as colunas
	private boolean checkCols(Player player) {
		for (int j = 0; j < Constantes.BOARD_SIZE; j++) {
			if (checkCol(j, player)) {
				return true;
			}
		}
		return false;
	}
	//Validação para checar a coluna
	private boolean checkCol(int j, Player player) {
		char symbol = player.getSymbol();
		
		for (int i = 0; i < Constantes.BOARD_SIZE; i++) {
			if (matrix[i][j] != symbol) {
				return false;
			}
		}
		return true;
	}
	//Validação para chegar a diagonal 1
	private boolean checkDiagonal1(Player player) {
		char symbol = player.getSymbol();
		
		for (int i = 0; i < Constantes.BOARD_SIZE; i++) {
			if (matrix[i][i] != symbol) {
				return false;
			}
		}
		return true;
	}
	//validação para checar a diagonal 2
	private boolean checkDiagonal2(Player player) {
		char symbol = player.getSymbol();
		int lastLine = Constantes.BOARD_SIZE - 1;
		
		for (int i = lastLine, j = 0; i >= 0; i--, j++) {
			if (matrix[i][j] != symbol) {
				return false;
			}
		}
		return true;
	}
}
