package minesweeper.core;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Test;

import minesweeper.core.Tile.State;

public class FieldTest {

	static final int ROWS = 9;
	static final int COLUMNS = 9;
	static final int MINES = 10;

	@Test
	public void isSolved() {
		Field field = new Field(ROWS, COLUMNS, MINES);

		assertEquals(GameState.PLAYING, field.getState());

		int open = 0;
		for (int row = 0; row < field.getRowCount(); row++) {
			for (int column = 0; column < field.getColumnCount(); column++) {
				if (field.getTile(row, column) instanceof Clue) {
					field.openTile(row, column);
					open++;
				}
				if (field.getRowCount() * field.getColumnCount() - open == field.getMineCount()) {
					assertEquals(GameState.SOLVED, field.getState());
				} else {
					assertNotSame(GameState.FAILED, field.getState());
				}
			}
		}

		assertEquals(GameState.SOLVED, field.getState());
	}

	@Test
	public void generate() {
		Field field = new Field(ROWS, COLUMNS, MINES);

		int mineCount = 0;
		int clueCount = 0;

		assertEquals(ROWS, field.getRowCount());
		assertEquals(COLUMNS, field.getColumnCount());
		assertEquals(MINES, field.getMineCount());

		for (int row = 0; row < field.getRowCount(); row++) {
			for (int column = 0; column < field.getColumnCount(); column++) {
				assertNotNull(field.getTile(row, column));
			}
		}

		for (int row = 0; row < field.getRowCount(); row++) {
			for (int column = 0; column < field.getColumnCount(); column++) {
				if (field.getTile(row, column) instanceof Mine) {
					mineCount++;
				}
			}
		}

		assertEquals(MINES, mineCount);

		for (int row = 0; row < field.getRowCount(); row++) {
			for (int column = 0; column < field.getColumnCount(); column++) {
				if (field.getTile(row, column) instanceof Clue) {
					clueCount++;
				}
			}
		}

		assertEquals(ROWS * COLUMNS - MINES, clueCount);

	}

	@Test
	public void markTile() {
		Field field = new Field(ROWS, COLUMNS, MINES);

		for (int row = 0; row < field.getRowCount(); row++) {
			for (int column = 0; column < field.getColumnCount(); column++) {
				if ((field.getTile(row, column)).getState() == State.MARKED) {
					field.markTile(row, column);
					assertEquals(State.CLOSED, (field.getTile(row, column)).getState());
				}
				
				if ((field.getTile(row, column)).getState() == State.CLOSED) {
					field.markTile(row, column);
					assertEquals(State.MARKED, (field.getTile(row, column)).getState());
				}
				if ((field.getTile(row, column)).getState() == State.OPEN) {
					field.markTile(row, column);
					assertEquals(State.OPEN, (field.getTile(row, column)).getState());
				}
				
				
			}
		}

	}
	
	@Test
	public void openTile() {
		Field field = new Field(ROWS, COLUMNS, MINES);

		for (int row = 0; row < field.getRowCount(); row++) {
			for (int column = 0; column < field.getColumnCount(); column++) {
				if ((field.getTile(row, column)).getState() == State.MARKED) {
					field.openTile(row, column);
					assertEquals(State.OPEN, (field.getTile(row, column)).getState());
				}
				
				if ((field.getTile(row, column)).getState() == State.CLOSED) {
					field.openTile(row, column);
					assertEquals(State.OPEN, (field.getTile(row, column)).getState());
				}
				if ((field.getTile(row, column)).getState() == State.OPEN) {
					field.openTile(row, column);
					assertEquals(State.OPEN, (field.getTile(row, column)).getState());
				}
				
				
			}
		}

	}
	
	@Test
	public void isFailed() {
		Field field = new Field(ROWS, COLUMNS, MINES);

		for (int row = 0; row < field.getRowCount(); row++) {
			for (int column = 0; column < field.getColumnCount(); column++) {
				if(field.getTile(row, column) instanceof Mine) {
					field.openTile(row, column);
					assertEquals(GameState.FAILED, field.getState());
				}
				
				}
	}}

}
