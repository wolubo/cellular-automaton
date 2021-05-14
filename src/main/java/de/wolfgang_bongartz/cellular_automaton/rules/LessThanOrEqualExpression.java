package de.wolfgang_bongartz.cellular_automaton.rules;


import de.wolfgang_bongartz.cellular_automaton.automaton.CellInformation;

/**
 * Implements 'less than or equal'
 * @author Wolfgang Bongartz
 *
 */
public class LessThanOrEqualExpression extends Operation {

	@Override
	/**
	 * @see Evaluable#evaluate
	 */
	public boolean evaluate(CellInformation ci) {
		int l = _left.getValue(ci);
		int r = _right.getValue(ci);
		return l<=r;
	}

}
