package de.wolfgang_bongartz.cellular_automaton.automaton;

import java.util.Random;

/**
 * Defines the possible directions.
 * @author Wolfgang Bongartz
 *
 */
public enum Direction {
	NORTHWEST(0),
	NORTH(1),
	NORTHEAST(2),
	EAST(3),
	SOUTHEAST(4),
	SOUTH(5),
	SOUTHWEST(6),
	WEST(7);
		
	private static Random _rnd = new Random();

	/**
	 * Create a direction by random.
	 * @param moore TRUE if a direction for Moore-environment is wanted. FALSE for a direction in a Neumann-environment.
	 * @return A randomly chosen direction.
	 */
	public static Direction random(boolean moore) {
		if(moore) {
			int r = Math.abs(_rnd.nextInt()) % 8;
			return values()[r];
		} else {
			int r = ( Math.abs(_rnd.nextInt()) % 4 ) * 2 + 1;
			return values()[r];
		}
	}
	
	@SuppressWarnings("unused")
	private final int value;
	
	private Direction(int intValue) {
		value = intValue;
	}
}
