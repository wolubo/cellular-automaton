package de.wolfgang_bongartz.cellular_automaton.rules;


import de.wolfgang_bongartz.cellular_automaton.automaton.CellInformation;

/**
 * Implements a constant integer value.
 * @author Wolfgang Bongartz
 *
 */
public class Skalar implements Evaluable {

	private int _value;
	
	/**
	 * 
	 * @param i The int-value.
	 */
	public Skalar(int i) {
		_value = i;
	}

	@Override
	/**
	 * @see Evaluable#evaluate
	 */
	public boolean evaluate(CellInformation ci) {
		return _value != 0; // Interpret '0' as 'false' and all other values as 'true'.
	}

	@Override
	/**
	 * @see Evaluable#getValue
	 */
	public int getValue(CellInformation ci) {
		return _value;
	}

}
