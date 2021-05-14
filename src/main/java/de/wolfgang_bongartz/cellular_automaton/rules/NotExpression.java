package de.wolfgang_bongartz.cellular_automaton.rules;


import de.wolfgang_bongartz.cellular_automaton.automaton.CellInformation;

/**
 * Implements the not-operation.
 * @author Wolfgang Bongartz
 *
 */
public class NotExpression implements Evaluable {
	
	private Evaluable _exp; 
	
	public NotExpression(Evaluable e) {
		_exp = e;
	}

	@Override
	/**
	 * @see Evaluable#evaluate
	 */
	public boolean evaluate(CellInformation ci) {
		return ! _exp.evaluate(ci);
	}

	@Override
	/**
	 * @see Evaluable#getValue
	 */
	public int getValue(CellInformation ci) {
		return evaluate(ci)?1:0;
	}

}
