package minesweeper.consoleui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import minesweeper.UserInterface;
import minesweeper.core.Clue;
import minesweeper.core.Field;
import minesweeper.core.GameState;
import minesweeper.core.Mine;
import minesweeper.core.Tile;

/**
 * Console user interface.
 */
public class ConsoleUI implements UserInterface {
	/** Playing field. */
	private Field field;

	/** Input reader. */
	private BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

	/**
	 * Reads line of text from the reader.
	 * 
	 * @return line as a string
	 */
	private String readLine() {
		try {
			return input.readLine();
		} catch (IOException e) {
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * minesweeper.consoleui.UserInterface#newGameStarted(minesweeper.core.Field)
	 */
	@Override
	public void newGameStarted(Field field) {
		this.field = field;
		do {
			update();
			processInput();
			if (field.getState() == GameState.SOLVED) {
				System.out.println("Vyhral si! GRATULUJEM!");
			}
			if (field.getState() == GameState.FAILED) {
				update();
				System.out.println("Trafil si minu! :(");
				System.exit(0);
			}

		} while (true);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see minesweeper.consoleui.UserInterface#update()
	 */
	@Override
	public void update() {
		System.out.println("  0 1 2 3 4 5 6 7 8");
		int charBorder = 65;
		for (int row = 0; row < field.getRowCount(); row++) {
			System.out.print((char) (charBorder + row));
			System.out.print(" ");
			for (int column = 0; column < field.getColumnCount(); column++) {
				Tile tile = field.getTile(row, column);
				switch (tile.getState()) {
				case MARKED:
					System.out.print("M");
					break;
				case OPEN:
					if (tile instanceof Mine) {
						System.out.print("X");
					} else {
						System.out.print(((Clue) tile).getValue());
					}
					break;
				case CLOSED:
					System.out.print("-");
					break;
				}
				System.out.print(" ");

			}
			System.out.println();
		}

	}

	/**
	 * Processes user input. Reads line from console and does the action on a
	 * playing field according to input string.
	 */
	private void processInput() {
		System.out.println(
				"ZADAJ: [Pre ukoncenie hry: X] [Pre oznacenie dlazdice A1: MA1] [Pre otvorenie dlazdice B4: OB4]");
		System.out.println("ZOSTAVA OZNACIT MIN: " + field.getRemainingMineCount());
		try {
			handleInput(readLine());
		} catch (WrongFormatException e) {
			System.out.println(e.getMessage());
		}

	}

	private void handleInput(String input) throws WrongFormatException {
		String toUppperCase = input.toUpperCase();
		
		Pattern pattern = Pattern.compile("(O|M)([A-I])([0-8])");
		Matcher matcher = pattern.matcher(toUppperCase);

		if (matcher.matches()) {
			if (toUppperCase.equals("X")) {
				System.out.println("Koniec hry!");
				System.exit(0);
			}
			if (Pattern.matches("O([A-I])([0-8])", toUppperCase)) {
				int row = input.charAt(1);
				int column = Character.getNumericValue(input.charAt(2));
				row -= 65;
				field.openTile(row, column);
			}
			if (Pattern.matches("M([A-I])([0-8])", toUppperCase)) {
				int row = input.charAt(1);
				int column = Character.getNumericValue(input.charAt(2));
				row -= 65;
				field.markTile(row, column);
			}

		} else {
			throw new WrongFormatException("Zle zadany vstup!");
		}
	}
}
