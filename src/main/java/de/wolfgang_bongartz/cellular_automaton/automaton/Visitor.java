package de.wolfgang_bongartz.cellular_automaton.automaton;

/**
 * To be implemented by all classes that need to visit cells within a habitat.
 * @author Wolfgang Bongartz
 *
 */
public interface Visitor {

	/**
	 * Method to be performed on the visited cells.
	 * @param ci Information about the cell visited.
	 */
	void visit(CellInformation ci);

}
