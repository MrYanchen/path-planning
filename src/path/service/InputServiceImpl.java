package path.service;

import path.domain.Barrier;
import path.domain.Border;

/**
 * @author MrYanc
 *
 */
public class InputServiceImpl implements InputService {

	// the list of point read in
	private Barrier[] barrierList;
	// the border point
	private Border border;

	/**
	 * @constructor
	 */
	public InputServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void readInBorder(String filePath) {
		// TODO Auto-generated method stub
		readInFile();
	}

	@Override
	public void readInBarrier(String filepath) {
		// TODO Auto-generated method stub
		readInFile();
	}

	private void readInFile() {

	}

	@Override
	public Border getBorder() {
		// TODO Auto-generated method stub
		return this.border;
	}

	@Override
	public Barrier[] getBarrierList() {
		// TODO Auto-generated method stub
		return this.barrierList;
	}

}
