package dataNode;

import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Float;

public class ConData {

	private String name;
	private final Long number;
	private Point2D.Float location = new Point2D.Float();
	
	
	/**
	 * @param name
	 * @param number
	 * @param location
	 */
	public ConData(String name, Long number) {
		super();
		this.name = name;
		this.number = number;
	}

	public Long getNumber() {
		return number;
	}

	/**
	 * @return the location
	 */
	public Point2D.Float getLocation() {
		return location;
	}

	/**
	 * @param location the location to set
	 */
	public void setLocation(Point2D.Float location) {
		this.location = location;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((number == null) ? 0 : number.hashCode());
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
		ConData other = (ConData) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (number == null) {
			if (other.number != null)
				return false;
		} else if (!number.equals(other.number))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ContactData [" + name + "," + number + "]";
	}

	
	
	
	
	
	
	
}
