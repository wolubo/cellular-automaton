package rules;

import automat.CellInformation;

/**
 * Superclass for all operations.
 * @author Wolfgang Bongartz
 *
 */
public abstract class Operation implements Evaluable {

	protected Evaluable _left;
	protected Evaluable _right;
	
	public void setOperands(Evaluable left, Evaluable right) {
		_left  = left;
		_right = right;
	}
	
	/**
	 * @see rules.Evaluable#getValue
	 */
	public int getValue(CellInformation ci) {
		return evaluate(ci) ? 1 : 0;
	}

}
