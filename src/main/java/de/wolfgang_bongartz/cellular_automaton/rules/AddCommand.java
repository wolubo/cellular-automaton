package de.wolfgang_bongartz.cellular_automaton.rules;

import de.wolfgang_bongartz.cellular_automaton.automaton.CellInformation;
import de.wolfgang_bongartz.cellular_automaton.automaton.Habitat;
import de.wolfgang_bongartz.cellular_automaton.automaton.Inhabitant;

/**
 * Implements the add-command that adds or substracts a value to/from an attribute.
 * @author Wolfgang Bongartz
 *
 */
public class AddCommand extends Command {

	private int _number;
	
	/**
	 * Constructor.
	 * @param parameter Name of the attribute.
	 * @param number Value to be added/substracted.
	 */
	public AddCommand(String parameter, int number) {
		super(parameter);
		_number = number;
	}

	@Override
	/**
	 * @see Command#perform
	 */
	public void perform(CellInformation cell, InhabitantFactory factory,
						Habitat habitat) {
		Inhabitant i = cell.getInhabitant();
		if(i!=null) {
			Integer v = i.getAttributeValue(_parameter);
			if(v!=null) {
				v = v + _number;
				i.changeAttributeValue(_parameter, v);
			}
		}
	}

}
