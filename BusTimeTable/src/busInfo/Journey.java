/**
 * 
 */
package busInfo;

import java.util.TreeSet;

/**
 * @author User
 *
 */
public class Journey {

	private Routes Route;
	
	private TreeSet<StopTimes> stops = new TreeSet<StopTimes>();
	
	public Journey(Routes r){
		Route = r;
	}
	
	public Journey(StopTimes[] s){
		stops.addAll(stops);
	}

	public boolean addStop(StopTimes s){
		return stops.add(s);
	}

	/**
	 * @return the route
	 */
	public Routes getRoute() {
		return Route;
	}

	/**
	 * @return the stops
	 */
	public TreeSet<StopTimes> getStops() {
		return stops;
	}
	
}
