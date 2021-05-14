package de.wolfgang_bongartz.cellular_automaton.rules;

import de.wolfgang_bongartz.cellular_automaton.automaton.*;

/**
 * Create a new inhabitant and put it into the current cell. 
 * @author Wolfgang Bongartz
 *
 */
public class CreateCommand extends Command {
	
	/**
	 * Constructor
	 * @param parameter Name of the species of the inhabitant to be created.
	 */
	public CreateCommand(String parameter) {
		super(parameter);
		if(parameter==null) throw new IllegalArgumentException();
	}

	@Override
	/**
	 * @see Command#perform
	 */
	public void perform(CellInformation cell, InhabitantFactory factory, Habitat habitat) {
		Inhabitant i = cell.getInhabitant();
		Inhabitant ni = factory.instantiateInhabitant(_parameter);
		if(i==null) {
			habitat.moveIn(cell.getPosition(), ni);
		} else {
			Direction d = Direction.random(habitat.isMooreEnvironment());
			Coordinate c = cell.getPosition().gotoDirection(d);
			Inhabitant in = habitat.getInhabitant(c);
			if(in==null) {
				habitat.moveIn(c, ni);
			}
		}
	}

}
