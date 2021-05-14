package de.wolfgang_bongartz.cellular_automaton.automaton;

import java.util.ArrayList;

/**
 * Asynchronous event that informs the receiver that a new generation of the CA is ready to be displayd.
 * @author Wolfgang Bongartz
 *
 */
public class PaintHabitatEvent implements Runnable {
	
	private static ArrayList<PaintHabitatEventObserver> _observers = new ArrayList<PaintHabitatEventObserver>();
	private Habitat _habitat;
	private int _generation;

	/**
	 * Add a new event-receiver.
	 * @param o Instance of a class that implements PaintHabitatEventObserver.
	 * @see PaintHabitatEventObserver
	 */
	public static void AddObserver(PaintHabitatEventObserver o) {
		_observers.add(o);
	}
	
	/**
	 * Constructor
	 * @param h Payload: Reference of the new habitat-version.
	 * @param generation Payload: Generation-number.
	 */
	public PaintHabitatEvent(Habitat h, int generation) {
		_habitat = h;
		_generation = generation;
	}

	@Override
	/**
	 * Call handleEvent() on all observers. 
	 * This method is performed automatically when the event is sent.
	 */
	public void run() {
		// TODO Auto-generated method stub
		for(PaintHabitatEventObserver o: _observers) {
			o.handleEvent(_habitat, _generation);
		}

	}

}
