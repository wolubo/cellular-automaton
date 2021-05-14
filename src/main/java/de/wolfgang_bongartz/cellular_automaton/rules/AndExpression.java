package de.wolfgang_bongartz.cellular_automaton.rules;


import de.wolfgang_bongartz.cellular_automaton.automaton.CellInformation;

/**
 * Implements the AND-operation.
 * @author Wolfgang Bongartz
 *
 */
public class AndExpression extends Operation {

	@Override
	/**
	 * @see Evaluable#evaluate
	 */
	public boolean evaluate(CellInformation ci) {
		return _left.evaluate(ci) && _right.evaluate(ci);
	}

}
