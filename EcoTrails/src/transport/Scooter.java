/**
 * 
 */
package transport;

/**
 * @author Matthew Schmidt
 *
 */
public class Scooter extends Vehicle {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7543817813717701352L;

	
	/* (non-Javadoc)
	 * @see transport.Transport#Type()
	 */
	@Override
	public String Type() {
		// TODO Auto-generated method stub
		return "Scooter";
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
		return 60d;
	}

	/* (non-Javadoc)
	 * @see transport.Transport#value()
	 */
	@Override
	public int value() {
		// TODO Auto-generated method stub
		return -1;
	}

}
