package de.wolfgang_bongartz.cellular_automaton.automaton;

import java.util.ArrayList;

import de.wolfgang_bongartz.cellular_automaton.rules.Action;
import de.wolfgang_bongartz.cellular_automaton.rules.InhabitantFactory;
import de.wolfgang_bongartz.cellular_automaton.rules.Rule;

/**
 * Collects the actions to be performed on the habitat.
 * @author Wolfgang Bongartz
 *
 */
public class CollectCommandsVisitor implements Visitor {
	
	private InhabitantFactory _factory;
	private Habitat _habitat;
	private ArrayList<Rule>   _rules;
	private ArrayList<Action> _actions;
	
	/**
	 * 
	 * @param rules The set of rules that may apply.
	 * @param factory A factory that can instantiate new inhabitants (if needed).
	 * @param habitat A reference to the habitat.
	 */
	public CollectCommandsVisitor(ArrayList<Rule> rules, InhabitantFactory factory, Habitat habitat) {
		_factory = factory;
		_habitat = habitat;
		_actions = new ArrayList<Action>();
		_rules = rules;
	}

	@Override
	/**
	 * @see Visitor#visit()
	 */
	public void visit(CellInformation ci) {
		for(Rule r: _rules)
		{
			r.apply(ci, _factory, _habitat, _actions); 
		}
	}

	/**
	 * Returns the list of collected actions.
	 * @return Action-List
	 */
	public ArrayList<Action> getActions() {
		return _actions;
	}

}
