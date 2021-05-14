package rules;

import automat.CellInformation;

/**
 * Implements 'less than'
 * @author Wolfgang Bongartz
 *
 */
public class LessThanExpression extends Operation {

	@Override
	/**
	 * @see rules.Evaluable#evaluate
	 */
	public boolean evaluate(CellInformation ci) {
		int l = _left.getValue(ci);
		int r = _right.getValue(ci);
		return l<r;
	}
}
