package rules;

import automat.CellInformation;
import automat.Habitat;

/**
 * Contains all information necessary to perform an action.
 * @author Wolfgang Bongartz
 *
 */
public class Action {
	
	private CellInformation _cell;
	private Command _command;
	private InhabitantFactory _factory; 
	private Habitat _habitat;
	
	/**
	 * Constructor
	 * @param cell Information about the cell the action is to be performed upon.
	 * @param command Action to be performed.
	 * @param factory A factory that can instantiate new inhabitants (if needed).
	 * @param habitat A reference to the habitat.
	 */
	public Action(CellInformation cell, Command command, InhabitantFactory factory, Habitat habitat) {
		_cell = cell;
		_command = command;
		_factory = factory;
		_habitat = habitat;
	}
	
	/**
	 * Actually perform the action.
	 */
	public void perform() {
		_command.perform(_cell, _factory, _habitat);
	}
}
