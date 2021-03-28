package rules;

import automat.*;

/**
 * Implement the getValue-function that delivers the current value of an attribute.
 * @author Wolfgang Bongartz
 *
 */
public class GetValueFunction implements Evaluable {

	private String _parameter; 

	/**
	 * 
	 * @param parameter Name of the attribute.
	 */
	public GetValueFunction(String parameter) {
		_parameter = parameter;
	}
	
	@Override
	/**
	 * @see rules.Evaluable#evaluate
	 */
	public boolean evaluate(CellInformation ci) {
		return getValue(ci)!=0;
	}

	@Override
	/**
	 * @see rules.Evaluable#getValue
	 */
	public int getValue(CellInformation ci) {
		Inhabitant i = ci.getInhabitant();
		if(i==null) {
			return 0;
		} else {
			return i.getAttributeValue(_parameter);
		}
	}

}
