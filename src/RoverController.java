/**
 * The type Rover controller.
 */
public class RoverController implements IRoverController {

	private final IWorld world;
	private IRover currentRover;

	/**
	 * Instantiates a new Rover controller.
	 *
	 * @param world the world in which the rover will move around in and is bound by
	 */
	public RoverController(final IWorld world) {
		this.world = world;
	}

	/**
	 * Add rover to the world.
	 *
	 * @param rover the rover
	 */
	@Override
	public void addRover(final IRover rover) {
		currentRover = rover;
	}

	/**
	 * Move rover around the world in a given sequence.
	 *
	 * @param sequence the sequence of movements
	 */
	@Override
	public void moveRover(final String sequence) {
		int sequenceLength = sequence.length();
		// Convert the whole sequence to uppercase to prevent errors.
		String upperCaseSequence = sequence.toUpperCase();
		// Split the sequence up into individual steps.
		String[] splitSequence = upperCaseSequence.split("");

		try {
			for (int i = 0; i < sequenceLength; i++) {
				String move = splitSequence[i];

				switch (move) {
					case FORWARD:
						moveForward();
						break;
					case LEFT:
						turnLeft();
						break;
					case RIGHT:
						turnRight();
						break;
					default:
						throw new IllegalArgumentException("This move is not supported.");
				}
			}
			Location updatedLocation = currentRover.getLocation();
			String updatedLocationString = String.format("(%s, %s, %s)", updatedLocation.getX(),
					updatedLocation.getY(), currentRover.getOrientation().toString());
			System.out.println(updatedLocationString);
		} catch (LostRoverException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Move rover forward in the direction it is facing.
	 */
	private void moveForward() throws LostRoverException {
		// Get the current location and orientation of the rover.
		Location currentRoverLocation = currentRover.getLocation();
		IRover.Orientation currentRoverOrientation = currentRover.getOrientation();
		Location updatedLocation;

		// Move the rover in the direction it is facing if the proposed location is within the bounds of the world,
		// otherwise throw a LostRoverException with the last valid position and orientation.
		switch (currentRoverOrientation) {
			case N:
				if (isInBounds(currentRoverLocation.getX(), currentRoverLocation.getY() + 1)) {
					updatedLocation = new Location(currentRoverLocation.getX(), currentRoverLocation.getY() + 1);
					currentRover.setLocation(updatedLocation);
				} else {
					String lostRoverMessage = createLostRoverMessage(currentRoverLocation, currentRoverOrientation);
					throw new LostRoverException(lostRoverMessage);
				}
				break;
			case E:
				if (isInBounds(currentRoverLocation.getX() + 1, currentRoverLocation.getY())) {
					updatedLocation = new Location(currentRoverLocation.getX() + 1, currentRoverLocation.getY());
					currentRover.setLocation(updatedLocation);
				} else {
					String lostRoverMessage = createLostRoverMessage(currentRoverLocation, currentRoverOrientation);
					throw new LostRoverException(lostRoverMessage);
				}
				break;
			case S:
				if (isInBounds(currentRoverLocation.getX(), currentRoverLocation.getY() - 1)) {
					updatedLocation = new Location(currentRoverLocation.getX(), currentRoverLocation.getY() - 1);
					currentRover.setLocation(updatedLocation);
				} else {
					String lostRoverMessage = createLostRoverMessage(currentRoverLocation, currentRoverOrientation);
					throw new LostRoverException(lostRoverMessage);
				}
				break;
			case W:
				if (isInBounds(currentRoverLocation.getX() - 1, currentRoverLocation.getY())) {
					updatedLocation = new Location(currentRoverLocation.getX() - 1, currentRoverLocation.getY());
					currentRover.setLocation(updatedLocation);
				} else {
					String lostRoverMessage = createLostRoverMessage(currentRoverLocation, currentRoverOrientation);
					throw new LostRoverException(lostRoverMessage);
				}
				break;
		}
	}

	/**
	 * Turn rover left.
	 */
	private void turnLeft() {
		IRover.Orientation currentRoverOrientation = currentRover.getOrientation();

		switch (currentRoverOrientation) {
			case N:
				currentRover.setOrientation(IRover.Orientation.W);
				break;
			case E:
				currentRover.setOrientation(IRover.Orientation.N);
				break;
			case S:
				currentRover.setOrientation(IRover.Orientation.E);
				break;
			case W:
				currentRover.setOrientation(IRover.Orientation.S);
				break;
		}
	}

	/**
	 * Turn rover right.
	 */
	private void turnRight() {
		IRover.Orientation currentRoverOrientation = currentRover.getOrientation();

		switch (currentRoverOrientation) {
			case N:
				currentRover.setOrientation(IRover.Orientation.E);
				break;
			case E:
				currentRover.setOrientation(IRover.Orientation.S);
				break;
			case S:
				currentRover.setOrientation(IRover.Orientation.W);
				break;
			case W:
				currentRover.setOrientation(IRover.Orientation.N);
				break;
		}
	}

	/**
	 * Check if the move is within the bounds of the world.
	 *
	 * @param proposedX the proposed x
	 * @param proposedY the proposed y
	 * @return true if move is within bounds, false otherwise
	 */
	private boolean isInBounds(final int proposedX, final int proposedY) {
		return proposedX <= world.getWidth() && proposedY <= world.getHeight() && proposedX >= 0 && proposedY >= 0;
	}

	/**
	 * Create lost rover message.
	 *
	 * @param location the last known location
	 * @param orientation the last known orientation
	 * @return the message for the lost rover including last known location and orientation.
	 */
	private String createLostRoverMessage(final Location location, final IRover.Orientation orientation) {
		String lastKnowLocation = String.format("(%s, %s, %s)", location.getX(), location.getY(),
				orientation.toString());
		return lastKnowLocation + " LOST";
	}
}
