package rules;

import automat.CellInformation;

/**
 * Implements the xor-operation.
 * @author Wolfgang Bongartz
 *
 */
public class XorExpression extends Operation {

	@Override
	/**
	 * @see rules.Evaluable#evaluate
	 */
	public boolean evaluate(CellInformation ci) {
		return _left.evaluate(ci) ^ _right.evaluate(ci);
	}

}
