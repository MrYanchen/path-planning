package path.algo;

import path.domain.Cell;
import path.service.AlgoService;

public interface CellCycleAlgo extends AlgoService {

	/**
	 * set cell list
	 *
	 * @param cellList
	 */
	public void setCellList(Cell[] cellList);

	/**
	 * get the hamilton cycle cell list
	 *
	 * @return
	 */
	public Cell[] getHamCycCellList();

}
