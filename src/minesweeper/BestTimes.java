package minesweeper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Formatter;
import java.util.Iterator;
import java.util.List;

/**
 * Player times.
 */
public class BestTimes implements Iterable<BestTimes.PlayerTime> {
	/** List of best player times. */
	private List<PlayerTime> playerTimes = new ArrayList<PlayerTime>();

	private BestTimes bestTimes = new BestTimes();

	private Formatter f;

	/**
	 * Returns an iterator over a set of best times.
	 * 
	 * @return an iterator
	 */
	public Iterator<PlayerTime> iterator() {
		return playerTimes.iterator();
	}

	/**
	 * Adds player time to best times.
	 * 
	 * @param name
	 *            name of the player
	 * @param time
	 *            player time in seconds
	 */
	public void addPlayerTime(String name, int time) {
		PlayerTime playerTime = new PlayerTime(name, time);
		playerTimes.add(playerTime);
		Collections.sort(playerTimes);
	}

	/**
	 * Returns a string representation of the object.
	 * 
	 * @return a string representation of the object
	 */
	public String toString() {
		f = new Formatter();
		for (int i = 0; i < playerTimes.size(); i++) {
			f.format("%d. %s -> %d", i, playerTimes.get(i).getName(), playerTimes.get(i).getTime());
		}
		return f.toString();
	}

	public BestTimes getBestTimes() {
		return bestTimes;
	}

	public void reset() {
		for (int i = 0; i < playerTimes.size(); i++) {
			if (!playerTimes.isEmpty()) {
				playerTimes.remove(i);
			}
		}
	}

	/**
	 * Player time.
	 */
	public static class PlayerTime implements Comparable<PlayerTime> {
		/** Player name. */
		private final String name;

		/** Playing time in seconds. */
		private final int time;

		/**
		 * Constructor.
		 * 
		 * @param name
		 *            player name
		 * @param time
		 *            playing game time in seconds
		 */
		public PlayerTime(String name, int time) {
			this.name = name;
			this.time = time;
		}

		public String getName() {
			return name;
		}

		public int getTime() {
			return time;
		}

		@Override
		public int compareTo(BestTimes.PlayerTime o) {
			return Integer.compare(this.getTime(), o.getTime());
		}

	}
}