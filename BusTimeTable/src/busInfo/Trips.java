package busInfo;

public class Trips {

	private int route_id;
	private Routes route;
	private int service_id;
	private int trip_id;
	private int direction;
	private int block_id;
	private int shape_id;
	
	
	public Trips(int route_id) {
		super();
		this.route_id = route_id; 
	}

	public int getService_id() {
		return service_id;
	}

	/**
	 * @param route_id the route_id to set
	 */
	public void setRoute_id(int route_id) {
		this.route_id = route_id;
	}

	/**
	 * @param service_id the service_id to set
	 */
	public void setService_id(int service_id) {
		this.service_id = service_id;
	}

	/**
	 * @param trip_id the trip_id to set
	 */
	public void setTrip_id(int trip_id) {
		this.trip_id = trip_id;
	}

	/**
	 * @param direction the direction to set
	 */
	public void setDirection(int direction) {
		this.direction = direction;
	}

	/**
	 * @param block_id the block_id to set
	 */
	public void setBlock_id(int block_id) {
		this.block_id = block_id;
	}

	/**
	 * @param shape_id the shape_id to set
	 */
	public void setShape_id(int shape_id) {
		this.shape_id = shape_id;
	}

	public int getTrip_id() {
		return trip_id;
	}


	public int getDirection() {
		return direction;
	}


	public int getBlock_id() {
		return block_id;
	}


	public int getShape_id() {
		return shape_id;
	}
	
	/**
	 * @return the route_id
	 */
	public int getRoute_id() {
		return route_id;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + block_id;
		result = prime * result + direction;
		result = prime * result + route_id;
		result = prime * result + service_id;
		result = prime * result + shape_id;
		result = prime * result + trip_id;
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Trips other = (Trips) obj;
		if (trip_id != other.trip_id)
			return false;
		return true;
	}

	/**
	 * @return the route
	 */
	public Routes getRoute() {
		return route;
	}

	/**
	 * @param route the route to set
	 */
	public void setRoute(Routes route) {
		this.route = route;
	}

	
	
}
