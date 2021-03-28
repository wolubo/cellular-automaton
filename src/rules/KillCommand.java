package rules;

import automat.CellInformation;
import automat.Habitat;
import automat.Inhabitant;

/**
 * Implements the kill-command that empties a cell.
 * @author Wolfgang Bongartz
 *
 */
public class KillCommand extends Command {

	
	public KillCommand(String parameter) {
		super(parameter);
	}

	@Override
	public void perform(CellInformation cell, InhabitantFactory factory, Habitat habitat) {
		Inhabitant i = cell.getInhabitant();
		if(i!=null) {
			habitat.moveOut(cell.getPosition());
		}
		
	}

}
