/**
 * 
 */
package smartStuff;

import java.util.ArrayList;

import location.Path;
import transport.Transport;
import user.User;

/**
 * The <code>Classifier</code> takes a <code>Trip</code> and creates a
 * <code>Set</code> of <I>Possible</I> <code>Transport</code> forms. It
 * is at all possible that you may get more than one form of <code>Transport</code>. 
 * As a <code>Trip</code> may contain many different modes of <code>Transport</code>. 
 * 
 * @author Matthew Schmidt
 * @since 1.0
 */
public class Classifier {



	/**
	 * This Creates a Classifier that will be used to work
	 * out what form of <code>Transport</code> the <code>User</code>
	 * has access to and will then work out what form of 
	 * transport they will use 
	 * 
	 * 
	 * @param <code>User</code> user This tells us what 
	 * 		<code>User</code> options of travel they have set
	 */
	public Classifier(User user){
		this.Options = user.getTransport();
	}

	/**
	 * 
	 */
	private Transport[] Options;

	/**
	 * 
	 * @param <code>Path</code>
	 * 
	 * @return <code>Transport[]</code>
	 */
	public Transport[] getMode(Path p){

		ArrayList<Transport> trans = new ArrayList<Transport>();
		double speed = p.getDistance()/p.getTime();

		for (Transport t: Options){
			if(t.maxSpeed()>speed && t.minSpeed()<speed){
				trans.add(t);
			}
		}
		
		return trans.toArray(new Transport[1]);
	}



}
