package path.domain;

/**
 * @author MrYanc
 *
 */
public class Event {

	public static enum EventType {
		IN, OUT, LEFT, RIGHT
	};

	// the point of the event
	private Point point;
	// the type of the event(in, out, ceiling, floor)
	private EventType type;
	// the point pointing to event point from left
	private Point left;
	// the point pointing to event point from right
	private Point right;

	/**
	 * @constructor
	 * @param Point
	 * @param type
	 */
	public Event(Point point, EventType type) {
		this.point = point;
		this.type = type;
	}

	/**
	 * @return the Point
	 */
	public Point getPoint() {
		return this.point;
	}

	/**
	 * @return the type
	 */
	public EventType getType() {
		return this.type;
	}

	/**
	 * @return the left point to event
	 */
	public Point getLeft() {
		return this.left;
	}

	/**
	 * @return the right point to event
	 */
	public Point getRight() {
		return this.right;
	}

	/**
	 * @param left
	 */
	public void setLeft(Point left) {
		this.left = left;
	}

	/**
	 * @param right
	 */
	public void setRight(Point right) {
		this.right = right;
	}

	/**
	 * @param event
	 * @return -1 if this prior to event
	 * @return 0 if this equal to event
	 * @return 1 if this later to event
	 */
	public int compare(Event event) {
		return this.point.compare(event.getPoint());
	}

	/**
	 * @param cell
	 * @return true if event works on input cell
	 * @return false if event not work on input cell
	 */
	public boolean findCell(Cell cell) {
		if (cell.getLeft().isEmpty() || cell.getRight().isEmpty())
			return false;
		switch (this.getType()) {
		case IN:
			double x = this.getPoint().getX();
			double x1 = cell.getLeft().getLast().getX();
			double x2 = cell.getRight().getLast().getX();
			if (x1 < x && x < x2)
				return true;
			else
				return false;
		case OUT:
			if (cell.getLeft().getLast().compare(this.getPoint()) == 0
					|| cell.getRight().getLast().compare(this.getPoint()) == 0)
				return true;
			else
				return false;
		case LEFT:
			if (cell.getLeft().getLast().compare(this.getPoint()) == 0)
				return true;
			else
				return false;
		case RIGHT:
			if (cell.getRight().getLast().compare(this.getPoint()) == 0)
				return true;
			else
				return false;
		}
		return false;
	}

}