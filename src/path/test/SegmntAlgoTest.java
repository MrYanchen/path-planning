package path.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import path.algo.SegmntAlgo;
import path.algo.SegmntAlgoImpl;
import path.domain.Barrier;
import path.domain.Border;
import path.domain.Cell;
import path.domain.Event;
import path.domain.Point;

/**
 * @author MrYanc
 *
 */
class SegmntAlgoTest {

	@BeforeEach
	void setUp() throws Exception {

	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void segmntAlgoOneBarrierTest1() {
		// set up border
		Point[] left = new Point[2];
		left[0] = new Point(0, 8);
		left[1] = new Point(0, 0);
		Point[] right = new Point[2];
		right[0] = new Point(8, 8);
		right[1] = new Point(8, 0);
		Border border = new Border(left, right);

		// setup barrier list
		Barrier[] barrierList = new Barrier[1];
		Event[] eventList = new Event[4];
		eventList[0] = new Event(new Point(3, 7), Event.EventType.IN);
		eventList[0].setLeft(new Point(4, 5));
		eventList[0].setRight(new Point(2, 5));
		eventList[1] = new Event(new Point(2, 5), Event.EventType.RIGHT);
		eventList[1].setRight(new Point(3, 3));
		eventList[2] = new Event(new Point(4, 5), Event.EventType.LEFT);
		eventList[2].setLeft(new Point(3, 3));
		eventList[3] = new Event(new Point(3, 3), Event.EventType.OUT);
		barrierList[0] = new Barrier(eventList);

		// setup segment algorithm service
		SegmntAlgo segmntAlgo = new SegmntAlgoImpl(border, barrierList);
		segmntAlgo.segmntAlgo();
		Cell[] cellList = segmntAlgo.getCellList();
		assertEquals(cellList.length, 4, "Segment algorithm service fails");
	}

	@Test
	void segmntAlgoOneBarrierTest2() {
		// set up border
		Point[] left = new Point[3];
		left[0] = new Point(0, 8);
		left[1] = new Point(1, 7);
		left[2] = new Point(0, 0);
		Point[] right = new Point[3];
		right[0] = new Point(8, 8);
		right[1] = new Point(7, 7);
		right[2] = new Point(8, 0);
		Border border = new Border(left, right);

		// setup barrier list
		Barrier[] barrierList = new Barrier[1];
		Event[] eventList = new Event[4];
		eventList[0] = new Event(new Point(3, 7), Event.EventType.IN);
		eventList[0].setLeft(new Point(4, 5));
		eventList[0].setRight(new Point(2, 5));
		eventList[1] = new Event(new Point(2, 5), Event.EventType.RIGHT);
		eventList[1].setRight(new Point(3, 3));
		eventList[2] = new Event(new Point(4, 5), Event.EventType.LEFT);
		eventList[2].setLeft(new Point(3, 3));
		eventList[3] = new Event(new Point(3, 3), Event.EventType.OUT);
		barrierList[0] = new Barrier(eventList);

		// setup segment algorithm service
		SegmntAlgo segmntAlgo = new SegmntAlgoImpl(border, barrierList);
		segmntAlgo.segmntAlgo();
		Cell[] cellList = segmntAlgo.getCellList();
		assertEquals(cellList.length, 4, "Segment algorithm service fails");
	}

	@Test
	void segmntTwoBarrierAlgoTest1() {
		// set up border
		Point[] left = new Point[2];
		left[0] = new Point(0, 8);
		left[1] = new Point(0, 0);
		Point[] right = new Point[2];
		right[0] = new Point(8, 8);
		right[1] = new Point(8, 0);
		Border border = new Border(left, right);

		// setup barrier list
		Barrier[] barrierList = new Barrier[2];

		Event[] eventList1 = new Event[4];
		eventList1[0] = new Event(new Point(3, 7), Event.EventType.IN);
		eventList1[0].setLeft(new Point(4, 5));
		eventList1[0].setRight(new Point(2, 5));
		eventList1[1] = new Event(new Point(2, 5), Event.EventType.RIGHT);
		eventList1[1].setRight(new Point(3, 3));
		eventList1[2] = new Event(new Point(4, 5), Event.EventType.LEFT);
		eventList1[2].setLeft(new Point(3, 3));
		eventList1[3] = new Event(new Point(3, 3), Event.EventType.OUT);
		barrierList[0] = new Barrier(eventList1);

		Event[] eventList2 = new Event[4];
		eventList2[0] = new Event(new Point(6, 6), Event.EventType.IN);
		eventList2[0].setLeft(new Point(7, 4));
		eventList2[0].setRight(new Point(5, 4));
		eventList2[1] = new Event(new Point(5, 4), Event.EventType.RIGHT);
		eventList2[1].setRight(new Point(6, 2));
		eventList2[2] = new Event(new Point(7, 4), Event.EventType.LEFT);
		eventList2[2].setLeft(new Point(6, 2));
		eventList2[3] = new Event(new Point(6, 2), Event.EventType.OUT);
		barrierList[1] = new Barrier(eventList2);

		// setup segment algorithm service
		SegmntAlgo segmntAlgo = new SegmntAlgoImpl(border, barrierList);
		segmntAlgo.segmntAlgo();
		Cell[] cellList = segmntAlgo.getCellList();
		assertEquals(cellList.length, 7, "Segment algorithm service fails");
	}

	@Test
	void segmntTwoBarrierAlgoTest2() {
		// set up border
		Point[] left = new Point[3];
		left[0] = new Point(0, 8);
		left[1] = new Point(1, 7);
		left[2] = new Point(0, 0);
		Point[] right = new Point[3];
		right[0] = new Point(8, 8);
		right[1] = new Point(7, 7);
		right[2] = new Point(8, 0);
		Border border = new Border(left, right);

		// setup barrier list
		Barrier[] barrierList = new Barrier[2];

		Event[] eventList1 = new Event[4];
		eventList1[0] = new Event(new Point(3, 7), Event.EventType.IN);
		eventList1[0].setLeft(new Point(4, 5));
		eventList1[0].setRight(new Point(2, 5));
		eventList1[1] = new Event(new Point(2, 5), Event.EventType.RIGHT);
		eventList1[1].setRight(new Point(3, 3));
		eventList1[2] = new Event(new Point(4, 5), Event.EventType.LEFT);
		eventList1[2].setLeft(new Point(3, 3));
		eventList1[3] = new Event(new Point(3, 3), Event.EventType.OUT);
		barrierList[0] = new Barrier(eventList1);

		Event[] eventList2 = new Event[4];
		eventList2[0] = new Event(new Point(6, 6), Event.EventType.IN);
		eventList2[0].setLeft(new Point(7, 4));
		eventList2[0].setRight(new Point(5, 4));
		eventList2[1] = new Event(new Point(5, 4), Event.EventType.RIGHT);
		eventList2[1].setRight(new Point(6, 2));
		eventList2[2] = new Event(new Point(7, 4), Event.EventType.LEFT);
		eventList2[2].setLeft(new Point(6, 2));
		eventList2[3] = new Event(new Point(6, 2), Event.EventType.OUT);
		barrierList[1] = new Barrier(eventList2);

		// setup segment algorithm service
		SegmntAlgo segmntAlgo = new SegmntAlgoImpl(border, barrierList);
		segmntAlgo.segmntAlgo();
		Cell[] cellList = segmntAlgo.getCellList();
		assertEquals(cellList.length, 7, "Segment algorithm service fails");
	}

	@Test
	void test() {
		// set up border
		Point[] left = new Point[2];
		left[0] = new Point(0, 8);
		left[1] = new Point(0, 0);
		Point[] right = new Point[2];
		right[0] = new Point(10, 8);
		right[1] = new Point(10, 0);
		Border border = new Border(left, right);

		// setup barrier list
		Barrier[] barrierList = new Barrier[3];
		Event[] eventList1 = new Event[4];
		eventList1[0] = new Event(new Point(1, 6), Event.EventType.IN);
		eventList1[0].setLeft(new Point(3, 6));
		eventList1[0].setRight(new Point(1, 3));
		eventList1[1] = new Event(new Point(3, 6), Event.EventType.LEFT);
		eventList1[1].setLeft(new Point(3, 3));
		eventList1[2] = new Event(new Point(1, 3), Event.EventType.RIGHT);
		eventList1[2].setRight(new Point(3, 3));
		eventList1[3] = new Event(new Point(3, 3), Event.EventType.OUT);
		barrierList[0] = new Barrier(eventList1);

		Event[] eventList2 = new Event[4];
		eventList2[0] = new Event(new Point(4, 6), Event.EventType.IN);
		eventList2[0].setLeft(new Point(6, 6));
		eventList2[0].setRight(new Point(4, 3));
		eventList2[1] = new Event(new Point(6, 6), Event.EventType.LEFT);
		eventList2[1].setLeft(new Point(6, 3));
		eventList2[2] = new Event(new Point(4, 3), Event.EventType.RIGHT);
		eventList2[2].setRight(new Point(6, 3));
		eventList2[3] = new Event(new Point(6, 3), Event.EventType.OUT);
		barrierList[1] = new Barrier(eventList2);

		Event[] eventList3 = new Event[4];
		eventList3[0] = new Event(new Point(7, 6), Event.EventType.IN);
		eventList3[0].setLeft(new Point(9, 6));
		eventList3[0].setRight(new Point(7, 3));
		eventList3[1] = new Event(new Point(9, 6), Event.EventType.LEFT);
		eventList3[1].setLeft(new Point(9, 3));
		eventList3[2] = new Event(new Point(7, 3), Event.EventType.RIGHT);
		eventList3[2].setRight(new Point(9, 3));
		eventList3[3] = new Event(new Point(9, 3), Event.EventType.OUT);
		barrierList[2] = new Barrier(eventList3);

		// setup segment algorithm service
		SegmntAlgo segmntAlgo = new SegmntAlgoImpl(border, barrierList);
		segmntAlgo.segmntAlgo();
		Cell[] cellList = segmntAlgo.getCellList();
		assertEquals(cellList.length, 6, "Segment algorithm service fails");
	}

}
