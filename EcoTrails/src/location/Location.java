/**
 * 
 */
package location;

import java.awt.geom.Point2D;
import java.io.Serializable;
import java.sql.Timestamp;

import transport.Transport;

/**
 * Represents a Geo Graphical location
 * otherwise its like <code>Point2D</code>
 * 
 * @author Matthew Schmidt
 *
 */
public class Location implements Comparable<Location>,Serializable  {


	private static final long serialVersionUID = -8281986698341558781L;

	/**
	 * Creates a <code>Location</code> with the give longitude 
	 * and latitude that was given
	 * @param Longitude
	 * @param Latitude
	 * @since 1.0
	 */
	public Location(int Longitude,int Latitude){
		this.longitude = Longitude;
		this.latitude = Latitude;
		time = new Timestamp(System.currentTimeMillis());

	}


	/**
	 * Converts a <code>Location</code> to a <code>Location</code> 
	 * with the give <code>Point2D</code>
	 * @param p <code>Point2D</code>
	 * @since 1.0
	 */
	public Location(Point2D p){
		this.longitude = p.getX();
		this.latitude=p.getY();
		time = new Timestamp(System.currentTimeMillis());
	}

	/**
	 * Creates a <code>Location</code> object with the give longitude 
	 * and latitude that was given.
	 * @param longitude 
	 * @param latitude
	 * @since 1.0
	 */
	public Location(String longitude , String latitude){
		this.longitude = Double.parseDouble(longitude);
		this.latitude = Double.parseDouble(latitude);
		time = new Timestamp(System.currentTimeMillis());
	}

	/**
	 * Creates a <code>Location</code> object with the give longitude 
	 * and latitude that was given.
	 * @param longitude
	 * @param latitude
	 * @since 1.0
	 */
	public Location(double longitude, double latitude) {
		super();
		this.longitude = longitude;
		this.latitude = latitude;
		time = new Timestamp(System.currentTimeMillis());
	}

	public Location(double d, double e, String s) {
		this.longitude = d;
		this.latitude = e;
		time = Timestamp.valueOf(s);
	}
	/**
	 *  If you use this constructor to create a <code>Location</code> then you will be declearing that the mode of transport was know and there for <b>Cannot be Changed</b>..
	 * @param lon <code>double</code> longitude.
	 * @param lat <code>double</code> latitude.
	 * @param s <code>String</code> TimeStamp.
	 * @param mode <code>Transport</code> the mode of <code>Transport</code> that was used.
	 */
	public Location(double lon, double lat, String s, Transport mode){
		this.longitude = lon;
		this.latitude = lat;
		time = Timestamp.valueOf(s);
		this.mode= mode;
		this.UserSet=1;
	}

	/**
	 * The longitude coordinate of this <code>Location</code>.
	 * @since 1.0
	 */
	private double longitude;

	/**
	 * The latitude coordinate of this <code>Location</code>.
	 * @since 1.0
	 */
	private double latitude;

	/**
	 * The Time this coordinate was created <code>Location</code>
	 * @since 1.0
	 * @serial
	 */
	private Timestamp time;


	/**
	 * 
	 */
	private Transport mode;


	private int UserSet =0;

	/**
	 * Gets the time when <code>Location</code> was created
	 * @return <code>TimeStamp</code> representing the time that this
	 * 			<code>Location</code> was created.
	 * @since 1.0
	 */
	public Timestamp getTime() {
		return time;
	}

	/**
	 * Gets the Longitude of this <code>Location</code>.
	 * @since 1.0
	 */
	public double getlongitude() {
		return longitude;
	}

	/**
	 * Gets the Latitude of this <code>Location</code>.
	 * @since 1.0
	 */
	public double getlatitude() {
		return latitude;
	}


	/**
	 * Returns the distance between two GeoLocations.
	 *
	 * @param longitude1 the longitude coordinate of the first specified point
	 * @param latitude1 the latitude coordinate of the first specified point
	 * @param longitude2 the longitude coordinate of the second specified point
	 * @param latitude2 the latitude coordinate of the second specified point
	 * @return the distance between the two sets of specified
	 * coordinates.
	 * @since 1.0
	 */
	public static double distance(double longitude1, double latitude1,
			double longitude2, double latitude2)
	{
		double dlong = Math.toRadians(longitude2 - longitude1);
		double dlat  = Math.toRadians(latitude2 - latitude1);
		// Haversine formula:
		double a = Math.sin(dlat/2)*Math.sin(dlat/2) + Math.cos(latitude1)*Math.cos(latitude2)*Math.sin(dlong/2)*Math.sin(dlong/2);
		double c = 2 * Math.atan2( Math.sqrt(a), Math.sqrt(1-a) );
		return 6371d * c;
	}

	/**
	 * Returns the distance from this <code>Location</code> to
	 * a specified point.
	 *
	 * @param plongitude the longitude coordinate of the specified point to be measured
	 *           against this <code>Location</code>
	 * @param platitude the latitude coordinate of the specified point to be measured
	 *           against this <code>Location</code>
	 * @return the distance between this <code>Location</code>
	 * and a specified point.
	 * @since 1.0
	 */
	public double distance(double plongitude, double platitude) {
		double dlong = Math.toRadians(plongitude - longitude);
		double dlat  = Math.toRadians(platitude - latitude);
		// Haversine formula:
		double a = Math.sin(dlat/2)*Math.sin(dlat/2) + Math.cos(latitude)*Math.cos(platitude)*Math.sin(dlong/2)*Math.sin(dlong/2);
		double c = 2 * Math.atan2( Math.sqrt(a), Math.sqrt(1-a) );
		return 6371d * c;
	}

	/**
	 * Returns the distance from this <code>Location</code> to a
	 * specified <code>Location</code>.
	 *
	 * @param pt the specified point to be measured
	 *           against this <code>Location</code>
	 * @return the distance between this <code>Location</code> and
	 * the specified <code>Location</code>.
	 * @since 1.0
	 */
	public double distance(Location pt) {
		return distance(pt.getlongitude(),pt.getlatitude());
	}

	/**
	 *  Returns the bearing form the <code>Location</code> to a
	 *  specified <code>Location</code> in Degrees.
	 *  
	 * @param pt the specified point to be measured
	 *           against this <code>Location</code>
	 * @return the Bearing in Degrees between this <code>Location</code> and
	 * the specified <code>Location</code>.
	 * @since 1.0
	 */
	public double BearingTo(Location pt){
		return BearingTo(pt.getlatitude(),pt.getlatitude());

	}

	/**
	 * Returns the bearing form the <code>Location</code> to a
	 * specified point in Degrees.
	 * 
	 * @param plongitude the longitude coordinate of the specified point to be measured
	 *           against this <code>Location</code>
	 * @param platitude the latitude coordinate of the specified point to be measured
	 *           against this <code>Location</code>
	 * @return the Bearing in Degrees between this <code>Location</code> and
	 * the specified Points.
	 * @since 1.0
	 */
	public double BearingTo(double plongitude, double platitude){
		double dLon = Math.toRadians(plongitude - longitude);

		double y = Math.sin(dLon) * Math.cos(platitude);
		double x = Math.cos(platitude)*Math.sin(platitude) -
				Math.sin(latitude)*Math.cos(platitude)*Math.cos(dLon);
		double brng =Math.toDegrees(Math.atan2(y, x));
		return (brng +360)%360;
	}


	/**
	 * Returns the bearing from the two specified points in Degrees.
	 * 
	 * @param longitude1 the longitude coordinate of the first specified point
	 * @param latitude1 the latitude coordinate of the first specified point
	 * @param longitude2 the longitude coordinate of the second specified point
	 * @param latitude2 the latitude coordinate of the second specified point
	 * @return the Bearing in Degrees between the two specified points.
	 * @since 1.0
	 */
	public static double BearingTo(double longitude1, double latitude1,
			double longitude2, double latitude2){

		double dLon = Math.toRadians(longitude2 - longitude1);

		double y = Math.sin(dLon) * Math.cos(latitude2);
		double x = Math.cos(latitude1)*Math.sin(latitude2) -
				Math.sin(latitude1)*Math.cos(latitude2)*Math.cos(dLon);
		double brng =Math.toDegrees(Math.atan2(y, x));
		return (brng +360)%360;

	}

	/**
	 * This is a fancy CompareTo 
	 * method that tells you how different they are.
	 * 
	 * @param <code>Timestamp</code> The time you are Comparing two 
	 * @return this returns the difference between the <code>Timestamp</code> of
	 * 		this <code>Location</code> and the <code>Timestamp</code> that was supplied.
	 */
	public double DifferenceTime(Timestamp st){
		return st.getTime() - time.getTime();
	}

	public double DifferenceTime(Location loc){
		return DifferenceTime(loc.getTime());
	}

	@Override
	public int compareTo(Location o) {
		return getTime().compareTo(o.getTime());
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Location\r\n\t\t\t [longitude=" + longitude + ", latitude=" + latitude
				+ ", " + (time != null ? "time=" + time : "") + "]\r\n";
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(latitude);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(longitude);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((time == null) ? 0 : time.hashCode());
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
		if (!(obj instanceof Location))
			return false;
		Location other = (Location) obj;
		if (Double.doubleToLongBits(latitude) != Double
				.doubleToLongBits(other.latitude))
			return false;
		if (Double.doubleToLongBits(longitude) != Double
				.doubleToLongBits(other.longitude))
			return false;
		if (time == null) {
			if (other.time != null)
				return false;
		} else if (!time.equals(other.time))
			return false;
		return true;
	}


	/**
	 * @return the mode
	 */
	public Transport getMode() {
		return mode;
	}

	public void setUserMode(Transport mode){
		UserSet=1;
		this.mode = mode;
	}


	/**
	 * @param mode the mode to set
	 */
	public void setMode(Transport mode) {
		if(UserSet==0){
			this.mode = mode;
		}
	}


	/**
	 * @return the userSet
	 */
	public int getUserSet() {
		return UserSet;
	}


}



