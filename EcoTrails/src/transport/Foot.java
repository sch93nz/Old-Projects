/**
 * 
 */
package transport;

/**
 * @author Matthew Schmidt
 *
 */
public class Foot extends Transport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7305506061195786040L;

	/* (non-Javadoc)
	 * @see transport.Transport#Type()
	 */
	@Override
	public String Type() {
		// TODO Auto-generated method stub
		return "Foot";
	}

	/* (non-Javadoc)
	 * @see transport.Transport#minSpeed()
	 */
	@Override
	public double minSpeed() {
		// TODO Auto-generated method stub
		return 0d;
	}

	/* (non-Javadoc)
	 * @see transport.Transport#maxSpeed()
	 */
	@Override
	public double maxSpeed() {
		// TODO Auto-generated method stub
		return 6.5d;
	}

	/* (non-Javadoc)
	 * @see transport.Transport#value()
	 */
	@Override
	public int value() {
		// TODO Auto-generated method stub
		return 5;
	}

}
