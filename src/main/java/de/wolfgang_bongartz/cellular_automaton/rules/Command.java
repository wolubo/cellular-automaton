package de.wolfgang_bongartz.cellular_automaton.rules;

import de.wolfgang_bongartz.cellular_automaton.automaton.CellInformation;
import de.wolfgang_bongartz.cellular_automaton.automaton.Habitat;

/**
 * Super-class for all commands.
 * @author Wolfgang Bongartz
 *
 */
public abstract class Command {
	
	protected String _parameter;
	
	/**
	 * 
	 * @param parameter Parameter for the operation. 
	 */
	public Command(String parameter) {
		_parameter = parameter;
	}
	
	/**
	 * Perform the command.
	 * @param cell Information about the cell the action is to be performed upon.
	 * @param factory A factory that can instantiate new inhabitants (if needed).
	 * @param habitat A reference to the habitat.
	 */
	public abstract void perform (CellInformation cell, InhabitantFactory factory, Habitat habitat);

}
