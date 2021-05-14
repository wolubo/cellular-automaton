package de.wolfgang_bongartz.cellular_automaton.automaton;

/**
 * Contains information about a cell.
 * @author Wolfgang Bongartz
 *
 */
public class CellInformation {
	
	private Coordinate _position;
	private Habitat _habitat;

	public CellInformation(Coordinate position, Habitat habitat) {
		setPosition(position);
		setHabitat(habitat);
	}

	public Coordinate getPosition() {
		return _position;
	}

	public void setPosition(Coordinate _position) {
		this._position = _position;
	}

	public Habitat getHabitat() {
		return _habitat;
	}

	public void setHabitat(Habitat _habitat) {
		this._habitat = _habitat;
	}

	public Inhabitant getInhabitant() {
		return _habitat.getInhabitant(_position);
	}

	public Environment getEnvironment() {
		return _habitat.getEnvironment(_position);
	}
}
