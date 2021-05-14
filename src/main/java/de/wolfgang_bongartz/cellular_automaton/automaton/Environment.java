package de.wolfgang_bongartz.cellular_automaton.automaton;

import java.util.ArrayList;

/**
 * Represents a cells environment.
 * @author Wolfgang Bongartz
 *
 */
public class Environment {

	private Inhabitant[] _inhabitants;
	private boolean _mooreEnvironment;
	private Coordinate _position;

	/**
	 * Constructs a new environment.
	 * @param habitat Habitat that contains the cell for which the environment is valid.
	 * @param position Position of the cell.
	 */
	public Environment(Habitat habitat, Coordinate position) {
		_position = position;
		_mooreEnvironment = habitat.isMooreEnvironment();
		_inhabitants = new Inhabitant[8];

		Coordinate newPosition;

		if(_mooreEnvironment)
		{
			for(Direction cd: Direction.values())
			{
				newPosition = position.gotoDirection(cd);
				_inhabitants[cd.ordinal()] = habitat.getInhabitant(newPosition);
			}
		} else {
			newPosition=position.gotoDirection(Direction.NORTH);
			_inhabitants[Direction.NORTH.ordinal()] = habitat.getInhabitant(newPosition);

			newPosition=position.gotoDirection(Direction.EAST);
			_inhabitants[Direction.EAST.ordinal()] = habitat.getInhabitant(newPosition);

			newPosition=position.gotoDirection(Direction.SOUTH);
			_inhabitants[Direction.SOUTH.ordinal()] = habitat.getInhabitant(newPosition);

			newPosition=position.gotoDirection(Direction.WEST);
			_inhabitants[Direction.WEST.ordinal()] = habitat.getInhabitant(newPosition);
		}
	}

	private void checkDirection(Direction direction) {
		if( ! _mooreEnvironment ) {
			if( direction==Direction.NORTHWEST || direction==Direction.NORTHEAST ||
					direction==Direction.SOUTHWEST || direction==Direction.SOUTHWEST) {
				throw new IllegalArgumentException();
			}
		}
	}

	/**
	 * Deliver the inhabitant of a certain cell within the environment.
	 * @param direction Direction relative to the main cell.
	 * @return Inhabitant
	 */
	public Inhabitant getInhabitant(Direction direction) {
		checkDirection(direction);
		return _inhabitants[direction.ordinal()];
	}

	/**
	 * Delivers the coordinates of all free cells in the environment.
	 * @return List of coordinates.
	 */
	public ArrayList<Coordinate> getAllFreeCells() {
		ArrayList<Coordinate> fc = new ArrayList<Coordinate>();
		if(_mooreEnvironment) {
			for(Direction i: Direction.values()) {
				Inhabitant c = _inhabitants[i.ordinal()];
				if(c==null) fc.add(gotoDirection(i));
			}
		} else {
			if(_inhabitants[Direction.NORTH.ordinal()]==null) fc.add(gotoDirection(Direction.NORTH));
			if(_inhabitants[Direction.EAST.ordinal()]==null)  fc.add(gotoDirection(Direction.EAST));
			if(_inhabitants[Direction.SOUTH.ordinal()]==null) fc.add(gotoDirection(Direction.SOUTH));
			if(_inhabitants[Direction.WEST.ordinal()]==null)  fc.add(gotoDirection(Direction.WEST));
		}
		return fc;
	}

	/** 
	 * Delivers a coordinate relative to the main cell.
	 * @param d Direction
	 * @return Coordinate of the cell in the given direction.
	 */
	public Coordinate gotoDirection(Direction d) {
		checkDirection(d);
		return _position.gotoDirection(d);
	}

	/**
	 * Counts all inhabitants of a certain species.
	 * @param species Name of the species. Counts all inhabitants if parameter is NULL.
	 * @return Number of inhabitants.
	 */
	public int countInhabitants(String species) {
		int retVal=0;
		if(_mooreEnvironment) {
			for(Direction d: Direction.values()) {
				Inhabitant i = getInhabitant(d);
				if(i!=null) {
					if(species==null || i.getSpecies().compareToIgnoreCase(species)==0) {
						retVal++;
					}
				}
			}
		} else {
			Inhabitant e, n, w, s;
			e = getInhabitant(Direction.EAST);
			n = getInhabitant(Direction.NORTH);
			s = getInhabitant(Direction.SOUTH);
			w = getInhabitant(Direction.WEST);
			if(e!=null && (species==null || e.getSpecies().compareToIgnoreCase(species)==0)) retVal++;
			if(n!=null && (species==null || n.getSpecies().compareToIgnoreCase(species)==0)) retVal++;
			if(w!=null && (species==null || w.getSpecies().compareToIgnoreCase(species)==0)) retVal++;
			if(s!=null && (species==null || s.getSpecies().compareToIgnoreCase(species)==0)) retVal++;
		}
		return retVal;
	}

	/**
	 * Delivers a list with all inhabitants within the environment.
	 * @param species Defines the species of the inhabitants to be delivered. Delivers all if this parameter is NULL.
	 * @return List of inhabitants.
	 */
	public ArrayList<Inhabitant> getInhabitants(String species) {
		ArrayList<Inhabitant> retVal = new ArrayList<Inhabitant>();
		if(_mooreEnvironment) {
			for(Direction d: Direction.values()) {
				Inhabitant i = getInhabitant(d);
				if(i!=null) {
					if(species==null || i.getSpecies().compareToIgnoreCase(species)==0) {
						retVal.add(i);
					}
				}
			}
		} else {
			Inhabitant e, n, w, s;
			e = getInhabitant(Direction.EAST);
			n = getInhabitant(Direction.NORTH);
			s = getInhabitant(Direction.SOUTH);
			w = getInhabitant(Direction.WEST);
			if(e!=null && (species==null || e.getSpecies().compareToIgnoreCase(species)==0)) retVal.add(e);
			if(n!=null && (species==null || n.getSpecies().compareToIgnoreCase(species)==0)) retVal.add(n);
			if(w!=null && (species==null || w.getSpecies().compareToIgnoreCase(species)==0)) retVal.add(w);
			if(s!=null && (species==null || s.getSpecies().compareToIgnoreCase(species)==0)) retVal.add(s);
		}
		return retVal;
	}
}
