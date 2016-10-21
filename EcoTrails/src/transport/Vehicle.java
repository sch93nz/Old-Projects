/**
 * 
 */
package transport;

/**
 * @author Matthew Schmidt
 *
 */
public class Vehicle extends Transport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2522747553199548427L;

	/* (non-Javadoc)
	 * @see transport.Transport#Type()
	 */
	@Override
	public String Type() {
		// TODO Auto-generated method stub
		return "Vehicle";
	}

	/* (non-Javadoc)
	 * @see transport.Transport#minSpeed()
	 */
	@Override
	public double minSpeed() {
		// TODO Auto-generated method stub
		return 30d;
	}

	/* (non-Javadoc)
	 * @see transport.Transport#maxSpeed()
	 */
	@Override
	public double maxSpeed() {
		// TODO Auto-generated method stub
		return 120d;
	}

	/* (non-Javadoc)
	 * @see transport.Transport#value()
	 */
	@Override
	public int value() {
		// TODO Auto-generated method stub
		return -5;
	}
	
	

}
