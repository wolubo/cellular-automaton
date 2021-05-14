package de.wolfgang_bongartz.cellular_automaton.rules;


import de.wolfgang_bongartz.cellular_automaton.automaton.CellInformation;

/**
 * To be implemented by all expressions.
 * @author Wolfgang Bongartz
 *
 */
public interface Evaluable {
	boolean evaluate(CellInformation ci);
	int getValue(CellInformation ci);
}
