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
	private Point startPoint;
	// clean area size
	private double length;
	// final point path to traverse cell
	private LinkedList<Point> pointList = new LinkedList<Point>();

	/**
	 * @constructor
	 */
	public CellTravrsAlgoImpl(Cell cell, Point startPoint) {
		// TODO Auto-generated constructor stub
		// setup
		setCell(cell);
		setStartPoint(startPoint);
		// call traverse function
		cellTravrs();
	}

	/**
	 * function implement cell traverse algorithm
	 *
	 * @param start
	 */
	private void cellTravrs() {
		// find start point cell list
		if (this.startPoint.compare(this.cell.getLeft().getFirst()) == 0) {
			// find end point
			Point end = findEndPoint(this.startPoint, this.cell.getRight());
			// generate path from start point to end point
			generatePath(this.startPoint, end);
			// find next start point
			Point nextStart = findNextStartPoint(end, this.cell.getRight());
			// generate next tarverse path
			nextTravrs(nextStart, this.cell.getRight(), this.cell.getLeft());
		} else {
			// find end point
			Point end = findEndPoint(this.startPoint, this.cell.getLeft());
			// generate path from start point to end point
			generatePath(this.startPoint, end);
			// find next start point
			Point nextStart = findNextStartPoint(end, this.cell.getLeft());
			// generate next tarverse path
			nextTravrs(nextStart, this.cell.getLeft(), this.cell.getRight());
		}
	}

	/**
	 * function generate next tarverse path
	 *
	 * @param start
	 * @param startLine
	 * @param endLine
	 */
	private void nextTravrs(Point start, LinkedList<Point> startLine, LinkedList<Point> endLine) {
		Point end = findEndPoint(start, endLine);
		generatePath(start, end);
		if (start.compare(startLine.getLast()) != 0) {
			Point nextStart = findNextStartPoint(end, endLine);
			nextTravrs(nextStart, endLine, startLine);
		}
	}

	/**
	 * function find end point
	 * 
	 * @param point
	 * @param list
	 * @return end point
	 */
	private Point findEndPoint(Point point, LinkedList<Point> list) {
		return null;
	}

	/**
	 *
	 * @param point
	 * @param list
	 * @return next start point
	 */
	private Point findNextStartPoint(Point point, LinkedList<Point> list) {
		return null;
	}

	/**
	 * generate traverse path from start point to end point
	 *
	 * @param start
	 * @param end
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
	public void setStartPoint(Point startPoint) {
		// TODO Auto-generated method stub
		this.startPoint = startPoint;
	}

	@Override
	public void setLength(double length) {
		// TODO Auto-generated method stub
		this.length = length;
	}

}
