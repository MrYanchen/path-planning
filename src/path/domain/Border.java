package path.domain;

/**
 * @author MrYanc
 *
 */
public class Border {

	// the left boundary of border
	private Point[] left;
	// the right boundary of border
	private Point[] right;

	/**
	 * @constructor
	 */
	public Border() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @constructor
	 * @param left
	 * @param floor
	 */
	public Border(Point[] left, Point[] right) {
		this.left = left;
		this.right = right;
	}

	/**
	 * @return left
	 */
	public Point[] getLeft() {
		return left;
	}

	/**
	 * @return right
	 */
	public Point[] getRight() {
		return right;
	}

	/**
	 * @param index
	 * @return left point at index
	 */
	public Point getLeftPointAt(int index) {
		return this.left[index];
	}

	/**
	 * @param index
	 * @return right point at index
	 */
	public Point getRightPointAt(int index) {
		return this.right[index];
	}

	/**
	 * @param point
	 * @return intersect point with left border
	 */
	public Point getIntSectPntWithLeft(Point point) {
		for (int index = 0; index < this.left.length; index++) {
			if (this.left[index].getY() == point.getY())
				return this.left[index];
			if (this.left[index].getY() < point.getY())
				return getIntSectPnt(this.left[index - 1], this.left[index], point.getY());
		}
		return null;
	}

	/**
	 * @param point
	 * @return intersect point with right border
	 */
	public Point getIntSectPntWithRight(Point point) {
		for (int index = 0; index < this.right.length; index++) {
			if (this.right[index].getY() == point.getY())
				return this.right[index];
			if (this.right[index].getY() < point.getY())
				return getIntSectPnt(this.right[index - 1], this.right[index], point.getY());
		}
		return null;
	}

	/**
	 * @param point1
	 * @param point2
	 * @param y
	 * @return new point @exception
	 */
	private Point getIntSectPnt(Point point1, Point point2, double y) {
		try {
			double x = (point1.getX() - point2.getX()) / (point1.getY() - point2.getY()) * (point1.getY() - y)
					+ point2.getX();
			return new Point(x, y);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	/**
	 * add all points among intersect point and start point into cell left list
	 *
	 * @param point
	 * @param cell
	 */
	public void addPntsFromLeftToCell(Point point, Cell cell) {
		for (int i = 0; this.left[i].getY() > point.getY(); i++) {
			if (this.left[i].getY() >= cell.getLeft().getLast().getY())
				continue;
			else
				cell.getLeft().add(this.left[i]);
		}
	}

	/**
	 * add all points among intersect point and start point into cell right list
	 *
	 * @param point
	 * @param cell
	 */
	public void addPntsFromRightToCell(Point point, Cell cell) {
		for (int i = 0; this.right[i].getY() > point.getY(); i++) {
			if (this.right[i].getY() >= cell.getRight().getLast().getY())
				continue;
			else
				cell.getRight().add(this.right[i]);
		}
	}

}
