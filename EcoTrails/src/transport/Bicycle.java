/**
 * 
 */
package transport;

/**
 * @author Matthew Schmidt
 *
 */
public class Bicycle extends Transport {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -192073249591296865L;


	/* (non-Javadoc)
	 * @see transport.Transport#Type()
	 */
	@Override
	public String Type() {
		// TODO Auto-generated method stub
		return "Bicycle";
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
		return 24d;
	}

	/* (non-Javadoc)
	 * @see transport.Transport#value()
	 */
	@Override
	public int value() {
		// TODO Auto-generated method stub
		return 10;
	}
	
	

}
