import java.util.Scanner;

/**
 * The type Main.
 */
public class Main {

	/**
	 * The entry point of application.
	 *
	 * @param args the input arguments
	 */
	public static void main(final String[] args) {
		// Set up scanner to listen for user input.
		Scanner scanner = new Scanner(System.in);

		System.out.println("Please enter the size for a new world or enter -1 to quit: ");
		String worldSize = scanner.nextLine();

		if (worldSize.trim().equals("-1")) {
			System.exit(0);
		} else {
			String[] splitSize = worldSize.trim().split(" ");

			// Verify that that user has provided exactly 2 values.
			if (splitSize.length == 2) {
				// Take the user's input and construct a new world of specified size.
				int width = Integer.parseInt(splitSize[0]);
				int height = Integer.parseInt(splitSize[1]);
				World newWorld = new World(width, height);

				// Create a new controller using the world created above.
				IRoverController roverController = new RoverController(newWorld);

				while (true) {
					System.out.println("Please create a new rover with a start position and orientation or enter -1 to quit: ");
					String userInput = scanner.nextLine();

					if (userInput.trim().equals("-1")) {
						System.exit(0);
					} else {
						// Take the user's input and construct a rover with the parameters specified.
						String trimmedAttributes = userInput.trim();
						int index = trimmedAttributes.lastIndexOf(" ");
						String initialRoverParameters = trimmedAttributes.substring(0, index);
						String roverMovementSequence = trimmedAttributes.substring(index + 1);

						IRover rover = createRoverWithInitialParameters(initialRoverParameters);

						// Add the rover to the controller and move it using the steps provided by the user.
						roverController.addRover(rover);
						roverController.moveRover(roverMovementSequence);
					}
				}
			}
		}
	}

	/**
	 * Create rover with initial parameters.
	 *
	 * @param initialParameters the initial parameters
	 * @return the rover
	 */
	private static IRover createRoverWithInitialParameters(final String initialParameters) {
		String extractedParameters = initialParameters.substring(initialParameters.indexOf("(") + 1, initialParameters.indexOf(")"));
		String[] splitParameters = extractedParameters.split(",");

		// Extract initial x-position, y-position and orientation from the user input.
		String initialXPos = splitParameters[0].trim();
		String initialYPos = splitParameters[1].trim();
		String orientationPointer = splitParameters[2].trim();

		Location location = new Location(Integer.parseInt(initialXPos), Integer.parseInt(initialYPos));
		IRover.Orientation orientation = IRover.Orientation.valueOf(orientationPointer);

		// Return a new rover object with an initial location and orientation.
		return new Rover(location, orientation);
	}
}
