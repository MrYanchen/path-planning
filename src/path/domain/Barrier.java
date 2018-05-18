package path.domain;

/**
 * @author MrYanc
 *
 */
public class Barrier {

	// the list of event due to point list
	private Event[] eventList;

	/**
	 * @constructor
	 */
	public Barrier(Event[] eventList) {
		// TODO Auto-generated constructor stub
		this.eventList = eventList;
	}

	/**
	 * @return eventList
	 */
	public Event[] getEventList() {
		return this.eventList;
	}

	/**
	 * @param index
	 * @return event at index
	 */
	public Event getEventAt(int index) {
		return this.eventList[index];
	}

}
