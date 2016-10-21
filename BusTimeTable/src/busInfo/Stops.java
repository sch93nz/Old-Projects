package busInfo;

public class Stops {

	private final int stop_id;
	private int check;
	private String stop_name;
	private float lat;
	private float lon;
	
	public Stops(int stop_id) {
		super();
		this.stop_id = stop_id;
	}
	

	/**
	 * @param check the check to set
	 */
	public void setCheck(int check) {
		this.check = check;
	}


	/**
	 * @param stop_name the stop_name to set
	 */
	public void setStop_name(String stop_name) {
		this.stop_name = stop_name;
	}


	/**
	 * @param lat the lat to set
	 */
	public void setLat(float lat) {
		this.lat = lat;
	}


	/**
	 * @param lon the lon to set
	 */
	public void setLon(float lon) {
		this.lon = lon;
	}


	/**
	 * @return the stop_id
	 */
	public int getStop_id() {
		return stop_id;
	}



	/**
	 * @return the check
	 */
	public int getCheck() {
		return check;
	}



	/**
	 * @return the stop_name
	 */
	public String getStop_name() {
		return stop_name;
	}



	/**
	 * @return the lat
	 */
	public float getLat() {
		return lat;
	}



	/**
	 * @return the lon
	 */
	public float getLon() {
		return lon;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + check;
		result = prime * result + Float.floatToIntBits(lat);
		result = prime * result + Float.floatToIntBits(lon);
		result = prime * result + stop_id;
		result = prime * result
				+ ((stop_name == null) ? 0 : stop_name.hashCode());
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
		Stops other = (Stops) obj;
		if (stop_id != other.stop_id)
			return false;
		
		return true;
	}

	
	
}
