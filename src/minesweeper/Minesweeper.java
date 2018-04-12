package minesweeper;

import minesweeper.consoleui.ConsoleUI;
import minesweeper.core.Field;


/**
 * Main application class.
 */
public class Minesweeper {
	/** User interface. */
	private UserInterface userInterface;

	/**
	 * Constructoooooor.
	 */
	private Minesweeper() {
		userInterface = new ConsoleUI();
		Field field = new Field(9, 9, 10);
		userInterface.newGameStarted(field);
		System.out.println("Hra skoncila");
	}

	
	public static void main(String[] args) {
		new Minesweeper();
	}
}
