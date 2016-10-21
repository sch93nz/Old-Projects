/**
 * 
 */
package busInfo;

/**
 * @author User
 *
 */
public class StopTimes implements Comparable<StopTimes>{

	private int trip_id;
	private Trips trip;
	private TimeString arrival_time;
	private TimeString departure_time;
	private int stop_id;
	private Stops stop;
	private int stop_sequence;
	private int pickup_type;
	private int drop_off_type;
	
	/**
	 * @return the trip_id
	 */
	public int getTrip_id() {
		return trip_id;
	}
	
	/**
	 * @return the stop_id
	 */
	public int getStop_id() {
		return stop_id;
	}
	
	/**
	 * @return the stop
	 */
	public Stops getStop() {
		return stop;
	}
	
	/**
	 * @return the stop_sequence
	 */
	public int getStop_sequence() {
		return stop_sequence;
	}
	
	/**
	 * @return the pickup_type
	 */
	public int getPickup_type() {
		return pickup_type;
	}
	
	/**
	 * @return the drop_off_type
	 */
	public int getDrop_off_type() {
		return drop_off_type;
	}
	
	/**
	 * @param trip_id
	 */
	public StopTimes(int trip_id) {
		super();
		this.trip_id = trip_id;
	}
	
	/**
	 * @param trip_id the trip_id to set
	 */
	public void setTrip_id(int trip_id) {
		this.trip_id = trip_id;
	}

	/**
	 * @param stop_id the stop_id to set
	 */
	public void setStop_id(int stop_id) {
		this.stop_id = stop_id;
	}
	
	/**
	 * @param stop the stop to set
	 */
	public void setStop(Stops stop) {
		this.stop = stop;
	}
	
	/**
	 * @param stop_sequence the stop_sequence to set
	 */
	public void setStop_sequence(int stop_sequence) {
		this.stop_sequence = stop_sequence;
	}
	
	/**
	 * @param pickup_type the pickup_type to set
	 */
	public void setPickup_type(int pickup_type) {
		this.pickup_type = pickup_type;
	}
	
	/**
	 * @param drop_off_type the drop_off_type to set
	 */
	public void setDrop_off_type(int drop_off_type) {
		this.drop_off_type = drop_off_type;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + drop_off_type;
		result = prime * result + pickup_type;
		result = prime * result + ((stop == null) ? 0 : stop.hashCode());
		result = prime * result + stop_id;
		result = prime * result + stop_sequence;
		result = prime * result + trip_id;
		return result;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		StopTimes other = (StopTimes) obj;
		if (drop_off_type != other.drop_off_type) {
			return false;
		}
		if (pickup_type != other.pickup_type) {
			return false;
		}
		if (stop == null) {
			if (other.stop != null) {
				return false;
			}
		} else if (!stop.equals(other.stop)) {
			return false;
		}
		if (stop_id != other.stop_id) {
			return false;
		}
		if (stop_sequence != other.stop_sequence) {
			return false;
		}
		if (trip_id != other.trip_id) {
			return false;
		}
		return true;
	}
	
	/**
	 * @return the departure_time
	 */
	public TimeString getDeparture_time() {
		return departure_time;
	}
	
	public String getDepTimeString(){
		return "Departure Time = "+departure_time.toString();
	}
	
	/**
	 * @param departure_time the departure_time to set
	 */
	public void setDeparture_time(TimeString departure_time) {
		this.departure_time = departure_time;
	}
	
	/**
	 * @return the arrival_time
	 */
	public TimeString getArrival_time() {
		return arrival_time;
	}
	
	/**
	 * @param arrival_time the arrival_time to set
	 */
	public void setArrival_time(TimeString arrival_time) {
		this.arrival_time = arrival_time;
	}
	
	
	@Override
	public int compareTo(StopTimes other) {
		Integer stop = other.getStop_sequence();

		if(stop.compareTo((Integer)this.stop_sequence)==0){
			return other.arrival_time.compareTo(arrival_time);
		}
		return stop.compareTo((Integer)this.stop_sequence);
	}
	
	/**
	 * @return the trip
	 */
	public Trips getTrip() {
		return trip;
	}
	
	/**
	 * @param trip the trip to set
	 */
	public void setTrip(Trips trip) {
		this.trip = trip;
	}

	
	


}
