package minesweeper;

import minesweeper.consoleui.ConsoleUI;
import minesweeper.core.Field;

/**
 * Main application class.
 */
public class Minesweeper {
	/** User interface. */
	private UserInterface userInterface;
	private long startMillis;
	private static Minesweeper instance;

	/**
	 * Constructor.
	 */
	private Minesweeper() {
		instance = this;
		userInterface = new ConsoleUI();
		Field field = new Field(9, 9, 10);
		userInterface.newGameStarted(field);
		startMillis = System.currentTimeMillis();
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

	public int getPlayingSeconds() {
		return (int) (startMillis - System.currentTimeMillis()) / 1000;
	}

	public static Minesweeper getInstance() {
		if (instance == null) {
			new Minesweeper();
		}
		return instance;
	}

}
