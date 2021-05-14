package de.wolfgang_bongartz.cellular_automaton.rules;

import de.wolfgang_bongartz.cellular_automaton.automaton.CellInformation;
import de.wolfgang_bongartz.cellular_automaton.automaton.Habitat;
import de.wolfgang_bongartz.cellular_automaton.automaton.Inhabitant;

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
