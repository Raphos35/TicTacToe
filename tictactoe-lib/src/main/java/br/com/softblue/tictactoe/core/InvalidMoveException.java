package br.com.softblue.tictactoe.core;

@SuppressWarnings("serial")
public class InvalidMoveException extends Exception { // extends � usado para indicar a heran�a de outra classe

	public InvalidMoveException(String message) {
		super(message);
	}

}
