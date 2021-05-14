package de.wolfgang_bongartz.cellular_automaton.rules;

import de.wolfgang_bongartz.cellular_automaton.automaton.CellInformation;

/**
 * Implements const-expressions. That is a constant value of either TRUE or FALSE.
 * @author Wolfgang Bongartz
 *
 */
public class ConstExpression implements Evaluable {

	private boolean _value;
	
	/**
	 * Constructor.
	 * @param b Value of the constant.
	 */
	public ConstExpression(boolean b) {
		_value = b;
	}

	@Override
	/**
	 * @see Evaluable#evaluate
	 */
	public boolean evaluate(CellInformation ci) {
		return _value;
	}

	@Override
	/**
	 * @see Evaluable#getValue
	 */
	public int getValue(CellInformation ci) {
		return _value ? 1 : 0;
	}

}
