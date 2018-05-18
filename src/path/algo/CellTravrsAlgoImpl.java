package path.algo;

import java.util.LinkedList;

import path.domain.Cell;
import path.domain.Point;

/**
 * @author MrYanc
 *
 */
public class CellTravrsAlgoImpl implements CellTravrsAlgo {

	// current cell
	private Cell cell;
	// start point
	private Point start;
	// clean area size
	private double length;
	// final point path to traverse cell
	private LinkedList<Point> pointList = new LinkedList<Point>();

	/**
	 * @constructor
	 */
	public CellTravrsAlgoImpl() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void setup() {
		// TODO Auto-generated method stub

	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	private void cellTravrs() {

	}

	private void generateTraverse() {
		// Point[] ceiling = (Point[]) this.cell.getCeiling().toArray();
		// Point[] floor = (Point[]) this.cell.getFloor().toArray();
		//
		// if (this.start.compare(ceiling[0])) {
		//
		// } else if (this.start.compare(ceiling[ceiling.length - 1])) {
		//
		// } else if (this.start.compare(floor[0])) {
		//
		// } else if (this.start.compare(floor[floor.length - 1])) {
		//
		// } else {
		//
		// }

	}

	private void findEndPoint() {

	}

	private void findStartPoint() {

	}

	/**
	 * generate traverse path from start point to end point
	 *
	 * @param start:
	 *            start point of path
	 * @param end:
	 *            end point of path
	 */
	private void generatePath(Point start, Point end) {
		Point p;
		double y = start.getY();
		if (start.getX() < end.getX()) {
			for (double x = start.getX(); x <= end.getX(); x = x + this.length) {
				p = new Point(x, y);
				this.pointList.add(p);
			}
		} else if (start.getX() > end.getX()) {
			for (double x = start.getX(); x >= end.getX(); x = x - this.length) {
				p = new Point(x, y);
				this.pointList.add(p);
			}
		} else {
			this.pointList.add(start);
		}
	}

	/**
	 * @param start
	 * @param end
	 * @param y-ordinate
	 * @return intersect point
	 */
	private Point intersetPoint(Point start, Point end, double y) {
		if (y == start.getY())
			return start;
		if (y == end.getY())
			return end;
		double x = (end.getX() - start.getX()) / (end.getY() - start.getY()) * (y - start.getY()) + start.getX();
		return new Point(x, y);
	}

	@Override
	public void setCell(Cell cell) {
		// TODO Auto-generated method stub
		this.cell = cell;
	}

	@Override
	public void setStart(Point point) {
		// TODO Auto-generated method stub
		this.start = point;
	}

	@Override
	public void setLength(double length) {
		// TODO Auto-generated method stub
		this.length = length;
	}

}
