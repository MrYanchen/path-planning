package path.domain;

import java.util.LinkedList;

/**
 * @author MrYanc
 *
 */
public class Cell {

	// the ceiling boundary of cell
	private LinkedList<Point> left = new LinkedList<Point>();
	// the floor boundary of cell
	private LinkedList<Point> right = new LinkedList<Point>();
	// the neighbor list of the cell
	private LinkedList<Cell> neighborList = new LinkedList<Cell>();
	// the center point of the cell
	private Point center;

	/**
	 * @constructor
	 */
	public Cell() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return left side point list
	 */
	public LinkedList<Point> getLeft() {
		return left;
	}

	/**
	 * @return right side point list
	 */
	public LinkedList<Point> getRight() {
		return right;
	}

	/**
	 * @return list of neighbor cell
	 */
	public LinkedList<Cell> getNeighborList() {
		return neighborList;
	}

	/**
	 * @return center point of cell
	 */
	public Point getCenter() {
		return center;
	}

	/**
	 * @param center
	 */
	public void setCenter(Point center) {
		this.center = center;
	}

}
