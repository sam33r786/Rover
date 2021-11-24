/**
 * The type Rover.
 */
public class Rover implements IRover {

	private Location location;
	private Orientation orientation;

	/**
	 * Instantiates a new Rover.
	 *
	 * @param location the location
	 * @param orientation the orientation
	 */
	public Rover(final Location location, final Orientation orientation) {
		this.location = location;
		this.orientation = orientation;
	}

	/**
	 * Gets location.
	 *
	 * @return the location
	 */
	@Override
	public Location getLocation() {
		return location;
	}

	/**
	 * Sets location.
	 *
	 * @param location the location
	 */
	@Override
	public void setLocation(final Location location) {
		this.location = location;
	}

	/**
	 * Gets orientation.
	 *
	 * @return the orientation
	 */
	@Override
	public Orientation getOrientation() {
		return orientation;
	}

	/**
	 * Sets orientation.
	 *
	 * @param orientation the orientation
	 */
	@Override
	public void setOrientation(final Orientation orientation) {
		this.orientation = orientation;
	}
}
