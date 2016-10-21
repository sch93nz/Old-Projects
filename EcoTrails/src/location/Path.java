/**
 * 
 */
package location;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeSet;

import smartStuff.Classifier;
import transport.Bicycle;
import transport.Foot;
import transport.Transport;
import transport.Vehicle;
import user.User;

/**
 * This Creates a <code>Path</code> which represents a
 * Part of the route taken on the trip it will take the 
 * distance between the two points and the time difference
 * and work out the average speed and define what possible
 * categories it falls in.
 * 
 * @author Matthew Schmidt
 *
 */
public class Path implements Comparable<Path>,Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2943736318235601656L;

	/**
	 * This Creates a <code>Path</code> which represents a
	 * Part of the route taken on the trip. 
	 * 
	 * @since 1.0
	 */
	public Path() {

	}
	/**
	 * The date that this <code>Path</code> covers.
	 */
	private Timestamp date;


	/**
	 * This tells weather or not we are still 
	 * on a <code>Path</code> or if you have finished.
	 * 
	 * @since 1.0
	 */
	private Boolean TripFinished = false;


	/**
	 * This contains all the <code>Locations</code> that are part of this
	 * <code>Path</code>
	 * @since 1.0
	 */
	private TreeSet<Location> locations = new TreeSet<Location>();

	/**
	 * The mode of <code>Transport</code> that is suggested by the <code>Classifier</code>
	 * or by the User.
	 */
	private Transport[] Mode;

	/**
	 * The start <code>Location</code> of the <code>Path</code>.
	 * @return The start <code>Location</code> of the <code>Path</code>.
	 * @since 1.0
	 */
	public Location getStart() {
		if(!locations.isEmpty())
			return locations.first();
		return null;
	}

	/**
	 * The end <code>Location</code> of the <code>Path</code>.
	 * @return The end <code>Location</code> of the <code>Path</code>.
	 * @since 1.0
	 */
	public Location getEnd() {
		if(!locations.isEmpty())
			return locations.last();
		return null;
	}

	/**
	 * The average speed of travel between the start 
	 * <code>Location</code> and the end <code>Location</code>.
	 * @return The average speed of travel between the start 
	 * 			<code>Location</code> and the end <code>Location</code>.
	 * @since 1.0
	 */
	public double getSpeed() {
		return getDistance()/(getTime()/ (1000 * 60 * 60) % 24);
	}

	/**
	 * Gets the time between the start <code>Location</code> and the end <code>Location</code>. if you want this in <b>Hours</b>
	 * then times it by <b>2.77778e-7</b>. For <b>Minutes</b> then times the result by <b>1.66667e-5</b>
	 * 
	 * @return <code>double</code> gets the length of time this trip took in milliseconds.
	 * 
	 * @since1.0
	 */
	public double getTime(){
		double time = getEnd().getTime().getTime()-getStart().getTime().getTime(); 
		return time;
	}


	/**
	 * The Distance between the start <code>Location</code> and
	 * the end <code>Location</code>.
	 * @return The Distance between the start <code>Location</code> and
	 * 			the end <code>Location</code>.
	 * @since 1.0
	 */
	public double getDistance(){
		double distance =0;

		if(!locations.isEmpty()){
			Location last = null;
			for(Location l: locations){
				if(last == null){
					last = l;
				} else {
					distance += last.distance(l);
				}
			}
		}

		return distance;
	}

	/**
	 * The Bearing between the start <code>Location</code> and 
	 * the end <code>Location</code>.
	 * 
	 * @return the Bearing between the start <code>Location</code> and 
	 * 			the end <code>Location</code>.
	 * @since 1.0
	 */
	public double getBearing(){
		return getStart().BearingTo(this.getEnd());
	}

	/**
	 * Tells you weather this <code>Trip</code> is finished or not.
	 * @return weather or not the <code>Trip</code> has finished.
	 * @since 1.0
	 */
	public Boolean getTripFinished() {
		return TripFinished;
	}

	/**
	 * 
	 * @param <code>boolean</code> weather or 
	 * 			not the <code>Path</code> has finished or not.
	 */
	public void setTripFinished(boolean t){
		TripFinished = t;
	}

	/**
	 * 
	 * @param <code>Location</code> Loc. The <code>Location</code> 
	 * 			which is being added.
	 * @return <b>WARNING</b> This will return null if it was added 
	 * 			and the given <code>Location</code> if it was not.
	 * @since 1.0
	 * 
	 */
	public Location addLocation(Location Loc){

		if(locations.isEmpty()){
			locations.add(Loc);
			date = Loc.getTime();
			return null;
		} else if ((!locations.contains(Loc)) && (!(getEnd().compareTo(Loc)>0))
				&& getEnd().distance(Loc) > 0.050d
				&& getEnd().DifferenceTime(Loc.getTime()) > 600000d && !TripFinished 
				){
			if(getEndTime(Loc)){
				this.TripFinished =true;
				return Loc;
			} else {
				locations.add(Loc);
			}
			return null;
		} else if(TripFinished){

			return Loc;

		}
		return null;
	}

	/**
	 * @return <code>TreeSet<Location></code> 
	 */
	public TreeSet<Location> getLocations() {
		return locations;
	}

	/**
	 * 
	 * @return <code>boolean</code> 
	 */
	@SuppressWarnings("deprecation")
	private boolean getEndTime(Location loc) {
		Timestamp ts = new Timestamp(getStart().getTime().getTime());
		ts.setDate(ts.getDate()+1);
		ts.setHours(00);
		ts.setMinutes(00);
		ts.setSeconds(00);
		boolean check = loc.getTime().after(ts);
		return check;
	}

	public boolean contains(Location loc){
		return locations.contains(loc);
	}

	/**
	 * compares Two <code>path</code>.
	 */
	@Override
	public int compareTo(Path o) {
		return this.getStart().compareTo(o.getStart());
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((locations == null) ? 0 : locations.hashCode());
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
		if (!(obj instanceof Path))
			return false;
		Path other = (Path) obj;
		if (locations == null) {
			if (other.locations != null)
				return false;
		} else if (!locations.equals(other.locations))
			return false;
		return true;
	}

	/**
	 * @return <code>Transport[]</code> the mode
	 */
	public Transport[] getMode() {
		return Mode;
	}

	/**
	 * 
	 * @param <code>User</code>
	 */
	public void workOutMode(User u){
		Classifier c = new Classifier(u);
		Mode = c.getMode(this);
	}

	/**
	 * @param mode <code>Transport</code> Mode the mode to set.
	 */
	public void setMode(Transport[] mode) {
		Mode = mode;
	}

	/**
	 * @return <code>Timestamp</code> The date
	 */
	public Timestamp getDate() {
		return date;
	}
	/**
	 * 
	 * @param <code>double</code> E
	 * @return <code>String</code>
	 */
	public String printTime(double E) {

		long s = Math.round((E / 1000) % 60);
		long m = Math.round((E / (1000 * 60)) % 60);
		long h = Math.round((E / (1000 * 60 * 60)));

		return ""+h+":"+m+":"+s;
	}

	public void sortOutMode(){
		Location [] loc = this.locations.toArray(new Location[1]);
		ArrayList<Transport> mode = new ArrayList<Transport>();
		for(int i = 1 ; i < loc.length-1 ; i++){

			if(i+1 <loc.length-1 && loc[i] !=null && loc[i+1] != null){
				if(loc[i].getUserSet()==1){
					double speed = loc[i].distance(loc[i+1])/(loc[i].DifferenceTime(loc[i+1])/(1000 * 60 * 60));

					if(speed > 50d){
						loc[i].setMode(new Vehicle());
						loc[i+1].setMode(new Vehicle());
						mode.add(new Vehicle());
					} else if (speed > 6.5d){
						loc[i].setMode(new Bicycle());
						loc[i+1].setMode(new Bicycle());
						mode.add(new Bicycle());
					} else {
						loc[i].setMode(new Foot());
						loc[i+1].setMode(new Foot());
						mode.add(new Foot());
					}
				}
			}

		}

		this.Mode= mode.toArray(new Transport[1]);

	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Path [date=");
		builder.append(date);
		builder.append(", TripFinished=");
		builder.append(TripFinished);
		builder.append(", locations=");
		builder.append(locations);
		builder.append(", Mode=");
		builder.append(Arrays.toString(Mode));
		builder.append("]");
		return builder.toString();
	}

}
