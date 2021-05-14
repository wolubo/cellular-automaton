package de.wolfgang_bongartz.cellular_automaton.automaton;

/**
 * Coordinates in 2d
 * 
 * @author Wolfgang Bongartz
 *
 */
public class Coordinate {


	private int _x;
	private int _y;
	private ReferenceSystem2D _reference;

	/**
	 * Constructor
	 * @param x
	 * @param y
	 * @param reference Reference-system to be used or NULL.
	 */
	public Coordinate(int x, int y, ReferenceSystem2D reference) {
		_reference = reference;
		if( ! checkAgainstReferenceSystem(x,y) )
		{
			throw new IllegalArgumentException();
		}
		_x = x;
		_y = y;
	}

	/**
	 * Clone-constructor
	 * @param position
	 * @param reference Reference-system to be used or NULL.
	 */
	public Coordinate(Coordinate position, ReferenceSystem2D reference) {
		int x = position.getX();
		int y = position.getY();
		_reference = reference;
		if( ! checkAgainstReferenceSystem(x,y) )
		{
			throw new IllegalArgumentException();
		}
		_x = x;
		_y = y;
	}

	/**
	 * Generate a new coordinate.
	 * @param dir Direction to go to.
	 * @return New coordinate.
	 */
	public Coordinate gotoDirection(Direction dir) {
		int dx=Integer.MIN_VALUE, dy=Integer.MIN_VALUE;
		switch(dir) {
		case NORTHWEST:	dx=_x-1; dy=_y-1; break;
		case NORTH:		dx=_x  ; dy=_y-1; break;
		case NORTHEAST:	dx=_x+1; dy=_y-1; break;
		case EAST:		dx=_x+1; dy=_y  ; break;
		case SOUTHEAST:	dx=_x+1; dy=_y+1; break;
		case SOUTH:		dx=_x  ; dy=_y+1; break;
		case SOUTHWEST:	dx=_x-1; dy=_y+1; break;
		case WEST:		dx=_x-1; dy=_y  ; break;
		}

		if(_reference!=null)
		{
			int minX = _reference.getMinX(); 
			int maxX = _reference.getMaxX(); 
			int minY = _reference.getMinY(); 
			int maxY = _reference.getMaxY(); 
			if( dx < minX ) dx = maxX;
			if( dx > maxX ) dx = minX;
			if( dy < minY ) dy = maxY;
			if( dy > maxY ) dy = minY;		
		}

		return new Coordinate(dx,dy,_reference);
	}

	public int getX() {
		return _x;
	}

	public int getY() {
		return _y;
	}

	private boolean checkAgainstReferenceSystem(int x, int y)
	{
		boolean rv = true;
		if(_reference!=null) {
			rv = rv && x >= _reference.getMinX();
			rv = rv && x <= _reference.getMaxX();
			rv = rv && y >= _reference.getMinY();
			rv = rv && y <= _reference.getMaxY();
		}
		return rv;
	}
}
