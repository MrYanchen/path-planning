package path.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import path.domain.Border;
import path.domain.Cell;
import path.domain.Event;
import path.domain.Point;

/**
 * @author MrYanc
 *
 */
class DomainTest {

	@BeforeEach
	void setUp() throws Exception {

	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void pointCompareTest() {
		// setup point
		Point point = new Point(2, 2);

		assertEquals(point.compare(new Point(3, 3)), 1, "Point compare method fails");
		assertEquals(point.compare(new Point(3, 2)), -1, "Point compare method fails");
		assertEquals(point.compare(new Point(2, 2)), 0, "Point compare method fails");
		assertEquals(point.compare(new Point(1, 2)), 1, "Point compare method fails");
		assertEquals(point.compare(new Point(1, 1)), -1, "Point compare method fails");
	}

	@Test
	void eventCompareTest() {
		// setup event
		Event event1 = new Event(new Point(3, 7), Event.EventType.IN);
		Event event2 = new Event(new Point(6, 6), Event.EventType.IN);
		Event event3 = new Event(new Point(2, 5), Event.EventType.LEFT);
		Event event4 = new Event(new Point(4, 5), Event.EventType.RIGHT);

		assertEquals(event1.compare(event2), -1, "Event compare method fails");
		assertEquals(event3.compare(event4), -1, "Event compare method fails");
	}

	@Test
	void eventFindCellTest() {
		// setup cell
		Cell cell1 = new Cell();
		cell1.getLeft().add(new Point(0, 8));
		cell1.getRight().add(new Point(8, 8));

		// setup event
		Event event1 = new Event(new Point(3, 7), Event.EventType.IN);

		assertEquals(event1.findCell(cell1), true, "Event find cell method fails");

		// setup event
		Event event3 = new Event(new Point(2, 5), Event.EventType.RIGHT);

		assertEquals(event3.findCell(cell1), false, "Event find cell method fails");

		Cell cell2 = new Cell();
		cell2.getLeft().add(new Point(0, 7));
		cell2.getRight().add(new Point(3, 7));
		cell2.getRight().add(new Point(2, 5));
		assertEquals(event3.findCell(cell2), true, "Event find cell method fails");

		// setup cell
		Cell cell3 = new Cell();
		cell3.getLeft().add(new Point(3, 7));
		cell3.getLeft().add(new Point(4, 5));
		cell3.getRight().add(new Point(8, 7));

		// setup event
		Event event2 = new Event(new Point(6, 6), Event.EventType.IN);

		assertEquals(event2.findCell(cell3), true, "Event find cell method fails");
		assertEquals(event1.findCell(cell3), false, "Event find cell method fails");

		// setup event
		Event event4 = new Event(new Point(4, 5), Event.EventType.RIGHT);

		assertEquals(event4.findCell(cell3), false, "Event find cell method fails");
	}

	@Test
	void borderGetIntSectPntTest() {
		// setup border
		Point[] left = new Point[2];
		left[0] = new Point(0, 8);
		left[1] = new Point(0, 0);
		Point[] right = new Point[2];
		right[0] = new Point(8, 8);
		right[1] = new Point(8, 0);
		Border border = new Border(left, right);

		// setup event
		Event event = new Event(new Point(3, 7), Event.EventType.IN);

		assertEquals(border.getIntSectPntWithLeft(event.getPoint()).getX(), 0.0,
				"Border find intersect point method fails");
		assertEquals(border.getIntSectPntWithLeft(event.getPoint()).getY(), 7.0,
				"Border find intersect point method fails");
		assertEquals(border.getIntSectPntWithRight(event.getPoint()).getX(), 8.0,
				"Border find intersect point method fails");
		assertEquals(border.getIntSectPntWithRight(event.getPoint()).getY(), 7.0,
				"Border find intersect point method fails");

		// setup border
		left = new Point[2];
		left[0] = new Point(0, 8);
		left[1] = new Point(8, 0);
		border = new Border(left, right);

		// setup event
		event = new Event(new Point(6, 4), Event.EventType.IN);

		assertEquals(border.getIntSectPntWithLeft(event.getPoint()).getX(), 4.0,
				"Border find intersect point method fails");
		assertEquals(border.getIntSectPntWithLeft(event.getPoint()).getY(), 4.0,
				"Border find intersect point method fails");

		// setup border
		left = new Point[2];
		left[0] = new Point(0, 8);
		left[1] = new Point(0, 0);
		right = new Point[2];
		right[0] = new Point(8, 8);
		right[1] = new Point(0, 0);
		border = new Border(left, right);

		// setup event
		event = new Event(new Point(2, 4), Event.EventType.IN);

		assertEquals(border.getIntSectPntWithRight(event.getPoint()).getX(), 4.0,
				"Border find intersect point method fails");
		assertEquals(border.getIntSectPntWithRight(event.getPoint()).getY(), 4.0,
				"Border find intersect point method fails");
	}

	@Test
	void borderAddPntsTest() {
		// setup border
		Point[] left = new Point[3];
		left[0] = new Point(0, 8);
		left[1] = new Point(1, 7);
		left[2] = new Point(0, 0);
		Point[] right = new Point[3];
		right[0] = new Point(8, 8);
		right[1] = new Point(7, 7);
		right[2] = new Point(8, 0);
		Border border = new Border(left, right);

		// setup event
		Event event = new Event(new Point(3, 6), Event.EventType.IN);

		// setup cell
		Cell cell = new Cell();
		cell.getLeft().add(new Point(0, 8));
		border.addPntsFromLeftToCell(border.getIntSectPntWithLeft(event.getPoint()), cell);

		assertEquals(cell.getLeft().size(), 2);

		Point p1 = cell.getLeft().removeLast();
		Point p2 = cell.getLeft().removeLast();

		assertEquals(p1.getX(), 1.0, "Border add points to cell method fails");
		assertEquals(p1.getY(), 7.0, "Border add points to cell method fails");
		assertEquals(p2.getX(), 0.0, "Border add points to cell method fails");
		assertEquals(p2.getY(), 8.0, "Border add points to cell method fails");

		// setup cell
		cell.getRight().add(new Point(8, 8));
		border.addPntsFromRightToCell(border.getIntSectPntWithLeft(event.getPoint()), cell);

		assertEquals(cell.getRight().size(), 2);

		Point p3 = cell.getRight().removeLast();
		Point p4 = cell.getRight().removeLast();

		assertEquals(p3.getX(), 7.0, "Border add points to cell method fails");
		assertEquals(p3.getY(), 7.0, "Border add points to cell method fails");
		assertEquals(p4.getX(), 8.0, "Border add points to cell method fails");
		assertEquals(p4.getY(), 8.0, "Border add points to cell method fails");
	}

}
