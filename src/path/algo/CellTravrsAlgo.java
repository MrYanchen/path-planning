package path.algo;

import path.domain.Cell;
import path.domain.Point;
import path.service.AlgoService;

/**
 * @author MrYanc
 *
 */
public interface CellTravrsAlgo extends AlgoService {

	/**
	 * set traverse cell
	 *
	 * @param cell
	 */
	public void setCell(Cell cell);

	/**
	 * set start point
	 *
	 * @param point
	 */
	public void setStart(Point point);

	/**
	 * set clean area size
	 *
	 * @param length
	 */
	public void setLength(double length);

}
