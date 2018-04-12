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
		Field field = new Field(8, 8, 6);
		userInterface.newGameStarted(field);
		System.out.println("Hra skoncila");
		System.out.println("Hra zacala");
	}

	/**
	 * Main method.
	 * 
	 * @param args
	 *            arguments
	 */
	public static void main(String[] args) {
		new Minesweeper();
	}
}
