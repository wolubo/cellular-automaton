package de.wolfgang_bongartz.cellular_automaton.automaton;

import java.awt.EventQueue;
import java.util.ArrayList;

import de.wolfgang_bongartz.cellular_automaton.rules.Action;
import de.wolfgang_bongartz.cellular_automaton.rules.InhabitantFactory;
import de.wolfgang_bongartz.cellular_automaton.rules.Rule;

/**
 * Implements a cellular automat that can be simulated within a thread.
 * @author Wolfgang Bongartz
 *
 */
public class CellularAutomaton implements InhabitantFactory, Runnable {
	
	private Habitat _habitat;
	private ArrayList<Inhabitant> _inhabitantTemplates;
	private ArrayList<Rule> _rules;
	private int _generation;

	public CellularAutomaton() {
		_inhabitantTemplates = new ArrayList<Inhabitant>();
		_rules = new ArrayList<Rule>();
		_generation=0;
	}

	public Habitat getHabitat() {
		return _habitat;
	}
	
	public void setHabitat(Habitat h) {
		_habitat = h;
	}

	/**
	 * Adds a template to be used to create new inhabitants.
	 * @param i New template.
	 */
	public void addInhabitantTemplate(Inhabitant i) {
		_inhabitantTemplates.add(i);
	}
	
	/**
	 * Delivers a freshly created inhabitant.
	 * @see InhabitantFactory
	 */
	public Inhabitant instantiateInhabitant(String species) {
		int i=0, s;
		Inhabitant it=_inhabitantTemplates.get(i);
		s=_inhabitantTemplates.size();
		while(i<s && it!=null && it.getSpecies().compareTo(species)!=0) {
			i++;
			it=_inhabitantTemplates.get(i);
		}
		if(i<s) 
			return new Inhabitant(it);
		else
			return null;
	}

	public void addRule(Rule r) {
		_rules.add(r);
	}
	
	/**
	 * Populates the habitat by random.
	 * @param percentage Defines the amount of cells to be filled.
	 */
	public void populateRandomly(int percentage) {
		if(_habitat!=null) {
			_habitat.populateRandomly(_inhabitantTemplates, percentage);
		}
	}
	
	@Override
	/*
	 * Creates the next generation...
	 * @see java.lang.Runnable#run()
	 */
	public void run() {
		CollectCommandsVisitor v = new CollectCommandsVisitor(_rules, this, _habitat);
		_habitat.visitAll(v);
		ArrayList<Action> actions = v.getActions();
		for(Action a: actions) {
			a.perform();			
		}
		_generation++;
		EventQueue.invokeLater(new PaintHabitatEvent(_habitat, _generation));
	}
	
	
}
