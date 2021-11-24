/**
 * The type World.
 */
public class World implements IWorld {

	private final int[][] grid;

	/**
	 * Instantiates a new World.
	 *
	 * @param width the width
	 * @param height the height
	 */
	public World(final int width, final int height) {
		grid = new int[width][height];
	}

	/**
	 * Gets width.
	 *
	 * @return the width
	 */
	@Override
	public int getWidth() {
		return grid.length;
	}

	/**
	 * Gets height.
	 *
	 * @return the height
	 */
	@Override
	public int getHeight() {
		return grid[1].length;
	}
}
