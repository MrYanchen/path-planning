package path.domain;

/**
 * @author MrYanc
 *
 */
public class Point {

	// x-coordinate of point
	private double x;
	// y-coordinate of point
	private double y;

	/**
	 * @constructor
	 */
	public Point() {
		this(0, 0);
	}

	/**
	 * @constructor
	 * @param x
	 * @param y
	 */
	public Point(double x, double y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * @param point
	 * @return -1 if this prior to point;
	 * @return 0 if this equal to point;
	 * @return 1 if this later to point
	 */
	public int compare(Point point) {
		if (this.getX() == point.getX() && this.getY() == point.getY())
			return 0;
		else {
			if (this.getY() > point.getY())
				return -1;
			else if (this.getY() == point.getY() && this.getX() < point.getX())
				return -1;
			else
				return 1;
		}
	}

	/**
	 * @return x-coordinate of point
	 */
	public double getX() {
		return x;
	}

	/**
	 * @return y-coordinate of point
	 */
	public double getY() {
		return y;
	}

}
