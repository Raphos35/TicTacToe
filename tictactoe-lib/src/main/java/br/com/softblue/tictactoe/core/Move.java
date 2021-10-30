package br.com.softblue.tictactoe.core;

public class Move {

	private int i;
	private int j;
	
	public Move (String moveStr) throws InvalidMoveException {
		try {
		
		// String � uma  classe e n�o um tipo primitivo
		String[] tokens = moveStr.split(",");
		
		//O comando Integer.parseInt() converte o valor do string em valor int
		this.i = Integer.parseInt(tokens[0]);
		this.j = Integer.parseInt(tokens[1]);
		} catch (Exception e) {
			throw new InvalidMoveException("A jogada � inv�lida");
		}
	}
	
	public int getI() {
		return i;
	}

	public int getJ() {
		return j;
	}
	
}
