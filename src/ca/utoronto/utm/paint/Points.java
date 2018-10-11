package ca.utoronto.utm.paint;

/**
 * Creates Point class
 * 
 * @author
 *
 */
public class Points {
	int x, y;

	/**
	 * Create new point
	 * 
	 * @param x
	 *            x coordinate
	 * @param y
	 *            y coordinate
	 */
	public Points(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * @return get x coordinate
	 */
	public int getX() {
		return x;
	}

	/**
	 * Set x coordinate
	 * 
	 * @param x
	 *            new x coordinate
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * @return get y coordinate
	 */
	public int getY() {
		return y;
	}

	/**
	 * Set y coordinate
	 * 
	 * @param y
	 *            new y coordinate
	 */
	public void setY(int y) {
		this.y = y;
	}

}
