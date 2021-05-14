package rules;

import automat.CellInformation;

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
	 * @see rules.Evaluable#evaluate
	 */
	public boolean evaluate(CellInformation ci) {
		return ! _exp.evaluate(ci);
	}

	@Override
	/**
	 * @see rules.Evaluable#getValue
	 */
	public int getValue(CellInformation ci) {
		return evaluate(ci)?1:0;
	}

}
