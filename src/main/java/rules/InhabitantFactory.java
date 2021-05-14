package rules;

import automat.Inhabitant;

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
