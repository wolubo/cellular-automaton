package automat;

/**
 * This interface is to be implemented by all classes that support a restricted 2-dimensional-coordinate-system.
 * @author Wolfgang Bongartz
 *
 */
public interface ReferenceSystem2D {
	/**
	 * @return Lowest X-Value
	 */
	int getMinX();
	
	/**
	 * @return Highest X-Value
	 */
	int getMaxX();

	/**
	 * @return Lowest Y-Value
	 */
	int getMinY();
	
	/**
	 * @return Highest Y-Value
	 */
	int getMaxY();
}
