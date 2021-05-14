package rules;

import automat.CellInformation;

/**
 * Implement 'equal to'.
 * @author Wolfgang Bongartz
 *
 */
public class EqualsExpression extends Operation {

	@Override
	/**
	 * @see rules.Evaluable#evaluate
	 */
	public boolean evaluate(CellInformation ci) {
		int l = _left.getValue(ci);
		int r = _right.getValue(ci);
		return l==r;
	}

}
