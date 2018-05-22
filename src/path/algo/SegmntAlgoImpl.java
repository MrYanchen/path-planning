package path.algo;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import path.domain.Barrier;
import path.domain.Border;
import path.domain.Cell;
import path.domain.Event;
import path.domain.Point;

public class SegmntAlgoImpl implements SegmntAlgo {

	// the boundary
	private Border border;
	// the list of barrier
	private Barrier[] barrierList;
	// the result list of cell
	private LinkedList<Cell> cellList = new LinkedList<Cell>();
	// the list of barrier event for sorting
	private List<LinkedList<Event>> eventList = new ArrayList<LinkedList<Event>>();

	/**
	 * @constructor
	 */
	public SegmntAlgoImpl(Border border, Barrier[] barrierList) {
		// TODO Auto-generated constructor stubsegmntAlgo
		setBorder(border);
		setBarrierList(barrierList);
	}

	@Override
	public void segmntAlgo() {
		// initialize event list for sorting
		initBarrierEventList();
		// clear cells list
		cellList.clear();
		// create the cells list
		createCells();
	}

	/**
	 * create cells
	 */
	private void createCells() {
		// create the first cell
		Cell cell = new Cell();
		cell.getLeft().add(this.border.getLeftPointAt(0));
		cell.getRight().add(this.border.getRightPointAt(0));

		// find first event
		Event event = findNextEvent();
		if (event != null) {
			// find intersect point with left and right borders
			Point intSectWithLeft = this.border.getIntSectPntWithLeft(event.getPoint());
			Point intSectWithRight = this.border.getIntSectPntWithRight(event.getPoint());

			// add all points among intersect point and start point into cell left list
			this.border.addPntsFromLeftToCell(intSectWithLeft, cell);
			cell.getLeft().add(intSectWithLeft);

			// add all points among intersect point and start point into cell right list
			this.border.addPntsFromRightToCell(intSectWithRight, cell);
			cell.getRight().add(intSectWithRight);

			// create next left side cell
			Cell nxtCell1 = new Cell();
			nxtCell1.getLeft().add(intSectWithLeft);
			nxtCell1.getRight().add(event.getPoint());
			nxtCell1.getRight().add(event.getRight());

			// create next right side cell
			Cell nxtCell2 = new Cell();
			nxtCell2.getLeft().add(event.getPoint());
			nxtCell2.getLeft().add(event.getLeft());
			nxtCell2.getRight().add(intSectWithRight);

			// add cells into neighbor list with each cell
			cell.getNeighborList().add(nxtCell1);
			cell.getNeighborList().add(nxtCell2);

			// add the finished cell into cell list
			this.cellList.add(cell);

			// add unfinished cells into temporary cell list
			ArrayList<Cell> tmp = new ArrayList<Cell>();
			tmp.add(nxtCell1);
			tmp.add(nxtCell2);

			// create next cells
			createNextCell(tmp);
		}
	}

	/**
	 * create next cell
	 */
	private void createNextCell(ArrayList<Cell> tmp) {
		// find next event
		Event event = findNextEvent();

		if (event != null) {
			switch (event.getType()) {
			case IN:
				for (Cell cell : tmp) {
					if (event.findCell(cell)) {
						// remove to be finished cell from temporary cell list
						tmp.remove(cell);
						//
						if (inEventJudge(cell, event)) {
							// create new cells open at IN event
							Cell nxtCell2 = new Cell();
							// add cells into neighbor list with each cell
							cell.getNeighborList().add(nxtCell2);
							nxtCell2.getNeighborList().add(cell);

							//
							nxtCell2.getLeft().add(event.getPoint());
							nxtCell2.getLeft().add(event.getLeft());
							nxtCell2.getRight().addAll(cell.getRight());
							cell.getRight().clear();
							cell.getRight().add(event.getPoint());
							cell.getRight().add(event.getRight());
							//
							tmp.add(cell);
							tmp.add(nxtCell2);

						} else {
							// create new cells open at IN event
							Cell nxtCell1 = new Cell();
							Cell nxtCell2 = new Cell();
							// add cells into neighbor list with each cell
							cell.getNeighborList().add(nxtCell1);
							cell.getNeighborList().add(nxtCell2);
							nxtCell1.getNeighborList().add(cell);
							nxtCell2.getNeighborList().add(cell);

							// add points to left side points list of cell
							if (cell.getLeft().size() == 1) {
								Point intSectWithLeft = this.border.getIntSectPntWithLeft(event.getPoint());

								// add all points among intersect point and start point into cell left list
								this.border.addPntsFromLeftToCell(intSectWithLeft, cell);

								cell.getLeft().add(intSectWithLeft);
								nxtCell1.getLeft().add(intSectWithLeft);
							} else {
								// add intersect point to left side points list of cell
								Point p = cell.getLeft().removeLast();
								Point np = findIntSectPnt(cell.getLeft().getLast(), p, event.getPoint().getY());
								cell.getLeft().add(np);

								// add intersect and event point to left side points list of next left cell
								nxtCell1.getLeft().add(np);
								nxtCell1.getLeft().add(p);
							}
							nxtCell1.getRight().add(event.getPoint());
							nxtCell1.getRight().add(event.getRight());

							// add points to right side points list of cell
							if (cell.getRight().size() == 1) {
								Point intSectWithRight = this.border.getIntSectPntWithRight(event.getPoint());

								// add all points among intersect point and start point into cell right list
								this.border.addPntsFromRightToCell(intSectWithRight, cell);

								cell.getRight().add(intSectWithRight);
								nxtCell2.getRight().add(intSectWithRight);
							} else {
								// add intersect point to left side points list of cell
								Point p = cell.getRight().removeLast();
								Point np = findIntSectPnt(cell.getRight().getLast(), p, event.getPoint().getY());
								cell.getRight().add(np);

								// add intersect and event point to left side points list of next left cell
								nxtCell2.getRight().add(np);
								nxtCell2.getRight().add(p);
							}
							nxtCell2.getLeft().add(event.getPoint());
							nxtCell2.getLeft().add(event.getLeft());

							// add finished cell into final queue list
							this.cellList.add(cell);
							// add unfinished cells into temporary cell list
							tmp.add(nxtCell1);
							tmp.add(nxtCell2);
						}
						break;
					} else
						continue;
				}
				break;
			case OUT:
				Cell nc = new Cell();
				// temporary stack to store finished cell
				Stack<Cell> stack = new Stack<Cell>();
				for (Cell cell : tmp) {
					if (event.findCell(cell)) {
						stack.add(cell);
						// add cells into neighbor list with each cell
						cell.getNeighborList().add(nc);
						nc.getNeighborList().add(cell);

						if (cell.getRight().getLast().compare(event.getPoint()) == 0) {
							mvToMostRight(cell.getRight());
							if (cell.getLeft().size() == 1) {
								Point intSectWithLeft = this.border.getIntSectPntWithLeft(event.getPoint());

								// add all points among intersect point and start point into cell left list
								this.border.addPntsFromLeftToCell(intSectWithLeft, cell);

								cell.getLeft().add(intSectWithLeft);
								nc.getLeft().add(intSectWithLeft);
							} else {
								Point leftPnt = cell.getLeft().removeLast();
								Point nLeftPnt = findIntSectPnt(cell.getLeft().getLast(), leftPnt,
										event.getPoint().getY());
								cell.getLeft().add(nLeftPnt);

								nc.getLeft().add(nLeftPnt);
								nc.getLeft().add(leftPnt);
							}
						}

						if (cell.getLeft().getLast().compare(event.getPoint()) == 0) {
							if (cell.getRight().size() == 1) {
								Point intSectWithRight = this.border.getIntSectPntWithRight(event.getPoint());

								// add all points among intersect point and start point into cell right list
								this.border.addPntsFromRightToCell(intSectWithRight, cell);

								cell.getRight().add(intSectWithRight);
								nc.getRight().add(intSectWithRight);
							} else {
								Point rightPnt = cell.getRight().removeLast();
								Point nRightPnt = findIntSectPnt(cell.getRight().getLast(), rightPnt,
										event.getPoint().getY());
								cell.getRight().add(nRightPnt);

								nc.getRight().add(nRightPnt);
								nc.getRight().add(rightPnt);
							}
						}
						// add finished cell into final queue list
						this.cellList.add(cell);
					}
				}
				// remove finished cell from temporary cell list
				while (!stack.isEmpty()) {
					tmp.remove(stack.pop());
				}
				// add unfinished cells into temporary cell list
				tmp.add(nc);
				break;
			case LEFT:
				for (Cell cell : tmp) {
					if (event.findCell(cell)) {
						mvToMostRight(cell.getLeft());
						cell.getLeft().add(event.getLeft());
						break;
					} else
						continue;
				}
				break;
			case RIGHT:
				for (Cell cell : tmp) {
					if (event.findCell(cell)) {
						mvToMostLeft(cell.getRight());
						cell.getRight().add(event.getRight());
						break;
					} else
						continue;
				}
				break;
			}
			createNextCell(tmp);
		} else {
			// get cell from temporary cell list
			if (!tmp.isEmpty()) {
				Cell cell = tmp.get(0);
				this.border.addPntsFromLeftToCell(this.border.getLeftPointAt(this.border.getLeft().length - 1), cell);
				this.border.addPntsFromRightToCell(this.border.getRightPointAt(this.border.getRight().length - 1),
						cell);
				cellList.add(cell);
			}
		}
	}

	/**
	 * initialize barriers event list for sorting
	 */
	private void initBarrierEventList() {
		for (Barrier barrier : this.barrierList) {
			LinkedList<Event> list = new LinkedList<Event>();
			for (Event event : barrier.getEventList()) {
				list.add(event);
			}
			this.eventList.add(list);
		}
	}

	/**
	 * find next event
	 */
	private Event findNextEvent() {
		if (this.eventList.isEmpty()) {
			return null;
		} else {
			Event nxtEvent = this.eventList.get(0).getFirst();
			int nxtEventIndex = 0;
			for (int index = 0; index < this.eventList.size(); index++) {
				Event tmp = this.eventList.get(index).getFirst();
				if (nxtEvent.getPoint().compare(tmp.getPoint()) > 0) {
					nxtEvent = tmp;
					nxtEventIndex = index;
				}
			}
			nxtEvent = this.eventList.get(nxtEventIndex).removeFirst();
			if (this.eventList.get(nxtEventIndex).isEmpty())
				this.eventList.remove(nxtEventIndex);
			return nxtEvent;
		}
	}

	/**
	 * @param start
	 * @param end
	 * @param y
	 * @return intersect point of swap line and edge
	 */
	private Point findIntSectPnt(Point start, Point end, double y) {
		double x = (end.getX() - start.getX()) / (end.getY() - start.getY()) * (y - start.getY()) + start.getX();
		return new Point(x, y);
	}

	/**
	 * function move to the most left point in the right list of cell
	 *
	 * @param list
	 */
	private void mvToMostLeft(LinkedList<Point> list) {
		Point p1 = list.removeLast();
		if (p1.getY() == list.getLast().getY())
			mvToMostLeft(list);
		else
			list.add(p1);
	}

	/**
	 * function move to the most right point in the left list of cell
	 *
	 * @param list
	 */
	private void mvToMostRight(LinkedList<Point> list) {
		Point p1 = list.removeLast();
		if (p1.getY() == list.getLast().getY()) {
			list.removeLast();
			list.add(p1);
		} else
			list.add(p1);
	}

	/**
	 *
	 * @param cell
	 * @param event
	 * @return
	 */
	private boolean inEventJudge(Cell cell, Event event) {
		if (cell.getLeft().getFirst().getY() == event.getPoint().getY()
				|| cell.getRight().getFirst().getY() == event.getPoint().getY())
			return true;
		else
			return false;
	}

	@Override
	public void setBorder(Border border) {
		// TODO Auto-generated method stub
		this.border = border;
	}

	@Override
	public void setBarrierList(Barrier[] barrierList) {
		// TODO Auto-generated method stub
		this.barrierList = barrierList;
	}

	@Override
	public Cell[] getCellList() {
		// TODO Auto-generated method stub
		return this.cellList.toArray(new Cell[this.cellList.size()]);
	}

}
