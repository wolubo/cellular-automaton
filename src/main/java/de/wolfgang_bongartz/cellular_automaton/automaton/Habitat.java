package de.wolfgang_bongartz.cellular_automaton.automaton;

import java.util.ArrayList;
import java.util.Random;
import java.lang.Math;

/**
 * Represents a set of cells to be used by a cellular automat.
 * @author Wolfgang Bongartz
 *
 */
public class Habitat implements ReferenceSystem2D {

	private static Random _rnd = new Random();

	private int _width;
	private int _height;
	private boolean _moore;
	
	private Inhabitant _cells[][];
	
	/**
	 * @param width Number of cells in x-direction
	 * @param height Number of cells in y-direction
	 * @param moore False: Neumann-environment. True: Moore-environment.
	 */
	public Habitat(int width, int height, boolean moore) {
		_width = width;
		_height = height;
		_moore = moore;
		if( width>0 && height>0 ) {
			_cells = new Inhabitant[width][height];
		}
	}
	
	/**
	 * Fills the habitat randomly with inhabitants. 
	 * @param _inhabitantTemplates  List of templates for the new inhabitants. Chosen randomly.
	 * @param percentage Defines how much space is to be filled (0..100).
	 */
	public void populateRandomly(ArrayList<Inhabitant> _inhabitantTemplates , int percentage) {
		if(_cells==null) return;
		
		if(_inhabitantTemplates ==null || _inhabitantTemplates.size()<1) throw new IllegalArgumentException();
		if(percentage<0 && percentage>100) throw new IllegalArgumentException("Value range of <percentage> is [0..100]");
		
		int total = _width * _height;
		
		if(percentage<75)
		{
			// Filling the habitat randomly with new inhabitants is the more efficient way.
			_cells = new Inhabitant[_width][_height];
			int numberOfInhabitants = total * percentage / 100;
			for(int n=0; n<numberOfInhabitants; n++) {
				Coordinate c;
				do {
					c = randomCoordinate(_rnd);
				} while(get(c)!=null);
				Inhabitant newInmate = randomInhabitant(_rnd, _inhabitantTemplates );
				put(c, newInmate);
			}
		} else {
			// Filling the whole habitat completely and then killing some inhabitants randomly is the more efficient way.
			populateCompletely(_inhabitantTemplates );
			int numberOfKills = total * (100-percentage) / 100;
			for(int n=0; n<numberOfKills; n++) {
				Coordinate c;
				do {
					c = randomCoordinate(_rnd);
				} while(get(c)==null);
				put(c, null);
			}
		}
		
	}
	
	/**
	 * Fill the habitat completely with inhabitants. Re-uses source of pseudo-random-numbers.
	 * @param templates List of templates for the new inhabitants. Chosen randomly.
	 */
	public void populateCompletely(ArrayList<Inhabitant> templates) {
		if(_cells==null) return;
		
		if(templates==null || templates.size()<1) throw new IllegalArgumentException();

		_cells = new Inhabitant[_width][_height];
		
		for(int x=0; x<_width; x++) {
			for(int y=0; y<_height; y++) {
				_cells[x][y]=randomInhabitant(_rnd, templates);
			}
		}
	}

	private Coordinate randomCoordinate(Random rnd) {
		int x, y;
		Coordinate c;
		x = Math.abs(rnd.nextInt()) % _width;
		y = Math.abs(rnd.nextInt()) % _height;
		c = new Coordinate(x,y,this);
		return c;
	}
	
	private Inhabitant randomInhabitant(Random rnd, ArrayList<Inhabitant> templates) {
		int i = Math.abs(rnd.nextInt()) % templates.size();
		return new Inhabitant(templates.get(i));
	}
	
	/**
	 * Fills a cell with an inhabitant.
	 * @param position Position of the cell.
	 * @param i New inhabitant.
	 */
	public void moveIn(Coordinate position, Inhabitant i) {
		if(_cells==null) throw new IllegalStateException ();
		Coordinate c = new Coordinate(position, this); // Make sure to use proper reference system.
		if(i==null) throw new IllegalArgumentException();
		if(get(c)!=null) throw new IllegalArgumentException();
		put(c,i);
	}

	/**
	 * Empties a cell.
	 * @param position Position of the cell.
	 * @return Former inhabitant.
	 */
	public Inhabitant moveOut(Coordinate position) {
		if(_cells==null) throw new IllegalStateException ();
		Coordinate c = new Coordinate(position, this); // Make sure to use proper reference system.
		Inhabitant i = null;
		i=get(c);
		if(i==null) throw new IllegalArgumentException();
		put(c,null);
		return i;
	}

	public Inhabitant getInhabitant(Coordinate position) {
		if(_cells==null) throw new IllegalStateException ();
		Coordinate c = new Coordinate(position, this); // Make sure to use proper reference system.
		return get(c);
	}

	public Environment getEnvironment(Coordinate position) {
		if(_cells==null) throw new IllegalStateException ();
		Environment e = new Environment(this, position); 
		return e;
	}
	
	/**
	 * Perform whatever is defined for the visitor.
	 * E.g.: Call v.perform() for the cell with the givven position.
	 * @param position Position of the cell thats visited.
	 * @param v Visitor (an instance of a class that implements the Visitor-interface)
	 * @see Visitor
	 */
	public void visit(Coordinate position, Visitor v) {
		if(_cells==null) throw new IllegalStateException ();
		Coordinate c = new Coordinate(position, this); // Make sure to use proper reference system.
		CellInformation ci = new CellInformation(c, this); 
		v.visit(ci);
	}

	/**
	 * Perform whatever is defined for the visitor on every cell.
	 * E.g.: Call v.perform() for all cells.
	 * @param v Visitor (an instance of a class that implements the Visitor-interface)
	 * @see Visitor
	 */
	public void visitAll(Visitor v) {
		if(_cells==null) throw new IllegalStateException ();
		for(int x=0; x<_width; x++)
		{
			for(int y=0; y<_height; y++)
			{
				Coordinate c = new Coordinate(x,y,this);
				visit(c, v);
			}
		}
	}

	private Inhabitant get(Coordinate p) {
		return _cells[p.getX()][p.getY()];
	}
	
	private void put(Coordinate p, Inhabitant i) {
		_cells[p.getX()][p.getY()]=i;
	}

	@Override
	/**
	 * @see ReferenceSystem2d
	 */
	public int getMinX() {
		return 0;
	}

	@Override
	/**
	 * @see ReferenceSystem2d
	 */
	public int getMaxX() {
		return _width-1;
	}

	@Override
	/**
	 * @see ReferenceSystem2d
	 */
	public int getMinY() {
		return 0;
	}

	@Override
	/**
	 * @see ReferenceSystem2d
	 */
	public int getMaxY() {
		return _height-1;
	}
	
	public boolean isMooreEnvironment() {
		return _moore;
	}

	/**
	 * Resize the habitat.
	 * @param width
	 * @param height
	 */
	public void resize(int width, int height) {
		Inhabitant[][] oldCells=null;
		if(_cells!=null) oldCells = _cells.clone();
		int oldWidth = _width;
		int oldHeight = _height;
		_width = width;
		_height = height;
		if( width>0 && height>0 ) {
			_cells = new Inhabitant[width][height];
			int maxWidth  = Math.min(oldWidth,  width);
			int maxHeight = Math.min(oldHeight, height);
			if( oldCells!=null && maxWidth>0 && maxHeight>0 ) {
				for(int x=0; x<maxWidth; x++) {
					for(int y=0; y<maxHeight; y++) {
						_cells[x][y]=oldCells[x][y];
					}
				}
			}
		} else {
			_cells = null;
		}
	}
}
