package automat;

import java.awt.Color;
import java.util.TreeMap;

/**
 * Represents an inhabitant (e.g.: something that sits in a cell)
 * @author Wolfgang Bongartz
 *
 */
public class Inhabitant {

	private String _species;
	private Color  _color;
	private TreeMap<String,Integer> _attributeSet;
	
	@SuppressWarnings("unchecked")
	/**
	 * Constructor
	 * @param species Name of the species represented by the inhabitant.
	 * @param color Color that represents the inhabitant.
	 * @param attributeSet List of Key-Value-Pairs.
	 */
	public Inhabitant(String species, TreeMap<String,Integer> attributeSet, Color color) {
		_species = species;
		_color = color;
		if(attributeSet!=null) {
			_attributeSet = (TreeMap<String, Integer>) attributeSet.clone();
		} else {
			_attributeSet = new TreeMap<String, Integer>(); 
		}
	}

	@SuppressWarnings("unchecked")
	/**
	 * Clone-Constructor.
	 * @param template Inhabitant to be cloned.
	 */
	public Inhabitant(Inhabitant template) {
		_species = template._species;
		_color = template._color;
		if(template._attributeSet!=null) {
			_attributeSet=(TreeMap<String, Integer>) template._attributeSet.clone();
		} else {
			_attributeSet = new TreeMap<String, Integer>(); 
		}
	}

	public Integer getAttributeValue(String attribute)
	{
		return _attributeSet.get(attribute);
	}

	public void changeAttributeValue(String attribute, int value)
	{
		if(_attributeSet.containsKey(attribute))
		{
			_attributeSet.put(attribute, value);
		} else {
			throw new IllegalArgumentException("Key does not exist!");
		}
	}
	
	public void setColor(Color c) {
		_color = c;
	}

	public Color getColor() {
		return _color;
	}

	public String getSpecies() {
		return _species;
	}

}
