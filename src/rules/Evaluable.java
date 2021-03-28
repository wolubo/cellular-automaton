package rules;

import automat.CellInformation;

/**
 * To be implemented by all expressions.
 * @author Wolfgang Bongartz
 *
 */
public interface Evaluable {
	boolean evaluate(CellInformation ci);
	int getValue(CellInformation ci);
}
