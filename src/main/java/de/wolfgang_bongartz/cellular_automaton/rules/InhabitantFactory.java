package de.wolfgang_bongartz.cellular_automaton.rules;

import de.wolfgang_bongartz.cellular_automaton.automaton.Inhabitant;

/**
 * Something that can instantiate new inhabitants.
 * @author Wolfgang Bongartz
 *
 */
public interface InhabitantFactory {

	/**
	 * 
	 * @param inhabitant Species of the new inhabitant.
	 * @return New Inhabitant-instance.
	 */
	Inhabitant instantiateInhabitant(String inhabitant);

}
