/**
 * The interface Rover controller.
 */
public interface IRoverController {

	/** Constants for rover movement. */
	String FORWARD = "F";
	String LEFT = "L";
	String RIGHT = "R";

	/**
	 * Add rover.
	 *
	 * @param rover the rover
	 */
	void addRover(IRover rover);

	/**
	 * Move rover around the world in a given sequence.
	 *
	 * @param sequence the sequence of movements
	 */
	void moveRover(String sequence);
}
