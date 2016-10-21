package location;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.TreeSet;



/**
 * A <code>Trip</code> represents a complete journey
 * which contains a set of <code>Path</code>s. A Trip is 
 * completed when two <code>Path</code> are 
 * with in 0.080km or 80m of each other. 
 * 
 * @author Matthew Schmidt
 *
 */
public class Trip implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1289012707025734220L;


	/**
	 * Creates a <code>Trip</code> with 
	 * empty History.
	 * 
	 * @since 1.0
	 */
	public Trip() {

	}

	/**
	 * This <code>TreeSet</code> contains a set of 
	 * <code>Path</code> that represents a trip 
	 * from beginning to end.
	 * 
	 * @since 1.0
	 */
	private TreeSet<Path> trip = new TreeSet<Path>();


	/**
	 * This <code>Path</code> represents the current <code>Path</code> that is being created..
	 * 
	 * @since 1.0
	 */
	private Path working;


	/**
	 * Use this to add a new <code>Location</code> to the <code>Trip</code> 
	 * @param location adds a <code>Location</code> to the trip if the 
	 *		 <code>Location</code> is over 0.080 km.
	 *
	 * @return true if it was add, False if it was not add 
	 * 		 and you should check if you have reached the end of 
	 * 		 the <code>Trip</code>.
	 * 
	 * @since 1.0
	 */
	public boolean addLocation(Location location){

		if (working==null) 
			working = new Path();

		if(working.addLocation(location)==null ){
			return true;
		} else {
			if(working.getTripFinished()){
				working.sortOutMode();
				trip.add(working);
				working=new Path();
				working.addLocation(location);
			}
		}
		return false;
	}


	/**
	 * This returns a <code>Array</code> of <code>Path</code>s
	 * that the <code>Trip</code> represents.
	 * 
	 * @return the List of <code>Path</code>s that make up this<code>Trip</code>.
	 * 
	 * @since 1.0
	 */
	public Path[] getTrip() {
		return trip.toArray(new Path[1]);
	}


	/**
	 * This returns the total Distance of the <code>Trip</code>
	 * @return The total Distance of the <code>Trip</code>
	 * @since 1.0
	 * 
	 */
	public Double getTotalDistance(){
		double total =0d;
		for(Path p: trip){
			total += p.getDistance();
		}
		return total;
	}

	/**
	 * 
	 */
	public void TripFinished(){
		trip.add(working);
		working = null;
	}


	/** 
	 * @param <code>Timestamp</code> the date.
	 * @return <code>Path</code> this the total list of locations for the day that you requested.
	 */
	public Path getDate(Timestamp s){
		return getDate(s.getTime());
	}

	/**
	 * 
	 * @param <code>Long</code> the date in nanoSeconds.
	 * @return <code>Path</code> this the total list of locations for the day that you requested.
	 */
	@SuppressWarnings("deprecation")
	public Path getDate(Long l){
		Timestamp ts = new Timestamp(l);
		for (Path p:trip){
			if(ts.getYear() == p.getDate().getYear()&&
					ts.getMonth() == p.getDate().getMonth()&&
					ts.getDate() == p.getDate().getDate()){
				return p;
			}
		}
		return null;
	}

	/**
	 * 
	 * @param <code>Timestamp</code> start.
	 * @param <code>Timestamp</code> end.
	 * @return <code>Path[]</code> that you wanted that is between the desired time. 
	 */
	public Path[] getBetween(Timestamp s, Timestamp e){
		ArrayList<Path> path = new ArrayList<Path>();
		for(Path p: trip){
			if(p.getDate().after(s) && p.getDate().before(e)){
				path.add(p);
			}
		}
		return path.toArray(new Path[1]);
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Trip [");
		if (trip != null) {
			builder.append("trip=");
			builder.append(trip);
			builder.append(", ");
		}
		if (working != null) {
			builder.append("working=");
			builder.append(working);
		}
		builder.append("]");
		return builder.toString();
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((trip == null) ? 0 : trip.hashCode());
		result = prime * result + ((working == null) ? 0 : working.hashCode());
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
		if (!(obj instanceof Trip))
			return false;
		Trip other = (Trip) obj;
		if (trip == null) {
			if (other.trip != null)
				return false;
		} else if (!trip.equals(other.trip))
			return false;
		if (working == null) {
			if (other.working != null)
				return false;
		} else if (!working.equals(other.working))
			return false;
		return true;
	}


	/**
	 * <code>sprtPaths</code> just assigns a mode to all the trips
	 * we already has.
	 */
	public void sortPaths(){
		for(Path p : trip){
			p.sortOutMode();
		}
	}
	/**
	 *  If there is a change in the locations from the app side use this meathod to implment the required changes. 
	 * @param loc <code>Location[]</code>  
	 */
	public void changeMode(Location[] loc){
		Timestamp time = loc[0].getTime();
		Path date = getDate(time);
		TreeSet<Location> tree = date.getLocations();
		for(int i = 0 ; i< loc.length;++i){
			if(tree.contains(loc[i])){
				tree.remove(loc[i]);
				tree.add(loc[i]);
			}
		}

	}
}



