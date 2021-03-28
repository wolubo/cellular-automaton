package rules;

import automat.*;

/**
 * Implements the check-function that checks if there sits a certain inhabitant in a cell.
 * @author Wolfgang Bongartz
 *
 */
public class CheckFunction implements Evaluable {

	private String _parameter; 
	
	/**
	 * Constructor.
	 * @param parameter Name of the species to look for. Checks if cell is empty if this parameter is NULL.
	 */
	public CheckFunction(String parameter) {
		_parameter = parameter;
	}

	@Override
	/**
	 * @see rules.Evaluable#evaluate
	 */
	public boolean evaluate(CellInformation ci) {
		Inhabitant i = ci.getInhabitant();
		if(_parameter==null) {
			return (i==null);
		} else {
			if(i==null) {
				return false;
			} else {
				return i.getSpecies().compareToIgnoreCase(_parameter)==0;
			}
		}
	}

	@Override
	/**
	 * @see rules.Evaluable#getValue
	 */
	public int getValue(CellInformation ci) {
		return evaluate(ci) ? 1 : 0;
	}

}

