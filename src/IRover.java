/**
 * The interface Rover.
 */
public interface IRover {

	/**
	 * The enum Orientation.
	 */
	enum Orientation {
		N, E, S, W
	}

	/**
	 * Gets location.
	 *
	 * @return the location
	 */
	Location getLocation();

	/**
	 * Sets location.
	 *
	 * @param location the location
	 */
	void setLocation(Location location);

	/**
	 * Gets orientation.
	 *
	 * @return the orientation
	 */
	Orientation getOrientation();

	/**
	 * Sets orientation.
	 *
	 * @param orientation the orientation
	 */
	void setOrientation(Orientation orientation);
}
