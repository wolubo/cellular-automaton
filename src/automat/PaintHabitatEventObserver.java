package automat;

/**
 * This interface needs to be implemented in all classes that receive PaintHabitatEvent-events.
 * @author Wolfgang Bongartz
 *
 */
public interface PaintHabitatEventObserver {

	/**
	 * Event handler.
	 * @param habitat Payload: Reference of the new habitat-version.
	 * @param generation Payload: Generation-number.
	 */
	void handleEvent(Habitat habitat, int generation);

}
