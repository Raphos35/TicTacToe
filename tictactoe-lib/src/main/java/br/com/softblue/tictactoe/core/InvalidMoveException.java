package br.com.softblue.tictactoe.core;

@SuppressWarnings("serial")
public class InvalidMoveException extends Exception { // extends é usado para indicar a herança de outra classe

	public InvalidMoveException(String message) {
		super(message);
	}

}
