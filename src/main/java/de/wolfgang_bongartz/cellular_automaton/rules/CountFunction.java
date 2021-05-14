package de.wolfgang_bongartz.cellular_automaton.rules;


import de.wolfgang_bongartz.cellular_automaton.automaton.CellInformation;
import de.wolfgang_bongartz.cellular_automaton.automaton.Environment;

/**
 * Implements the count-function that counts how many inhabitants of a certain kind life in the environment of a cell.
 * @author Wolfgang Bongartz
 *
 */
public class CountFunction implements Evaluable {

	private String _parameter; 

	/**
	 * Constructor.
	 * @param parameter Name of the species to look for. Counts all living things if parameter is NULL.
	 */
	public CountFunction(String parameter) {
		_parameter = parameter;
	}
	
	@Override
	/**
	 * @see Evaluable#evaluate
	 */
	public boolean evaluate(CellInformation ci) {
		return getValue(ci)!=0;
	}

	@Override
	/**
	 * @see Evaluable#getValue
	 */
	public int getValue(CellInformation ci) {
		Environment e = ci.getEnvironment();
		int c = e.countInhabitants(_parameter);
		return c;
	}

}
