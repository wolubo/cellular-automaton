package rules;

import automat.CellInformation;

/**
 * Implements the AND-operation.
 * @author Wolfgang Bongartz
 *
 */
public class AndExpression extends Operation {

	@Override
	/**
	 * @see rules.Evaluable#evaluate
	 */
	public boolean evaluate(CellInformation ci) {
		return _left.evaluate(ci) && _right.evaluate(ci);
	}

}
