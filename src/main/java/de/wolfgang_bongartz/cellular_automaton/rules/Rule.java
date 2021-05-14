package de.wolfgang_bongartz.cellular_automaton.rules;

import de.wolfgang_bongartz.cellular_automaton.automaton.CellInformation;
import de.wolfgang_bongartz.cellular_automaton.automaton.Habitat;

import java.util.ArrayList;


/**
 * Implements a rule.
 * @author Wolfgang Bongartz
 *
 */
public class Rule {

	private Evaluable _expression;
	private Command _command;
	
	/**
	 * 
	 * @param e The expression to be evaluated.
	 * @param c The action that should be performed if the expression has been evaluated to TRUE.
	 */
	public Rule(Evaluable e, Command c) {
		if(e==null) throw new IllegalArgumentException();
		if(c==null) throw new IllegalArgumentException();
		_expression = e;
		_command = c;
	}

	/**
	 * Aplies the rule upon a certain cell.
	 * @param cell Information about the cell the action is to be performed upon.
	 * @param factory A factory that can instantiate new inhabitants (if needed).
	 * @param habitat A reference to the habitat.
	 * @param actionList
	 */
	public void apply(CellInformation cell, InhabitantFactory factory, Habitat habitat, ArrayList<Action> actionList)
	{
		if(_expression.evaluate(cell)) {
			Action a = new Action(cell, _command, factory, habitat);
			actionList.add(a);
		}
	}
}
