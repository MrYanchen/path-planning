package path.algo;

import path.domain.Barrier;
import path.domain.Border;
import path.domain.Cell;

public interface SegmntAlgo {

	/**
	 * call algorithm function
	 */
	public void segmntAlgo();

	/**
	 * set border
	 *
	 * @param border
	 */
	public void setBorder(Border border);

	/**
	 * set barrier list
	 *
	 * @param barrierList
	 */
	public void setBarrierList(Barrier[] barrierList);

	/**
	 * get final segmented cell list
	 *
	 * @return
	 */
	public Cell[] getCellList();

}
