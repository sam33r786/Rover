import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RoverControllerTest {

	private IRoverController controller;

	@Before
	public void setUp() throws Exception {
		IWorld world = new World(4, 8);
		controller = new RoverController(world);
	}

	@Test
	public void testMovementScenario1() {
		Location location = new Location(2, 3);
		Rover rover = new Rover(location, IRover.Orientation.E);

		controller.addRover(rover);
		controller.moveRover("LFRFF");

		assertEquals(4, rover.getLocation().getX());
		assertEquals(4, rover.getLocation().getY());
		assertEquals(IRover.Orientation.E, rover.getOrientation());
	}

	@Test
	public void testMovementScenario2() {
		Location location = new Location(2, 3);
		Rover rover = new Rover(location, IRover.Orientation.N);

		controller.addRover(rover);
		controller.moveRover("FLLFR");

		assertEquals(2, rover.getLocation().getX());
		assertEquals(3, rover.getLocation().getY());
		assertEquals(IRover.Orientation.W, rover.getOrientation());
	}

	@Test
	public void testLossScenario() {
		Location location = new Location(1, 0);
		Rover rover = new Rover(location, IRover.Orientation.S);

		controller.addRover(rover);
		controller.moveRover("FFRLF");

		assertEquals(1, rover.getLocation().getX());
		assertEquals(0, rover.getLocation().getY());
		assertEquals(IRover.Orientation.S, rover.getOrientation());
	}
}