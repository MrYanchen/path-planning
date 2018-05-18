package path.service;

import path.domain.Barrier;
import path.domain.Border;

/**
 * @author MrYanc
 *
 */
public interface InputService {

	/**
	 * read in input border point list
	 *
	 * @param filePath
	 *
	 */
	public void readInBorder(String filePath);

	/**
	 * read in input barrier point list
	 *
	 * @param filepath
	 */
	public void readInBarrier(String filepath);

	/**
	 * @return point list of border
	 */
	public Border getBorder();

	/**
	 * @return barriers point list
	 */
	public Barrier[] getBarrierList();

}
