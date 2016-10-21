/**
 * 
 */
package transport;

import java.io.Serializable;

/**
 * @author User
 *
 */
public abstract class Transport implements Comparable<Transport>,Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5220371596760250791L;

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(Transport o) {
		// TODO Auto-generated method stub
		return this.value() - o.value();
	}
	
	/**
	 * 
	 * @return <code>String</code> this is the type
	 * 			 of <code>Transport</code> that this
	 * 			 <code>Class</code> represents.
	 * @since 1.1
	 */
	public abstract String Type();
	
	/**
	 * 
	 * @return <code>double</code> this is the <b>minimum</b> 
	 * 			speed of the form of <code>Transport</code>.
	 * @since 1.1
	 */
	public abstract double minSpeed();
	
	/**
	 * 
	 * @return <code>double</code> this is the <b>maximum</b>
	 *			speed of the form of <code>Transport</code>.
	 *@since 1.1
	 */
	public abstract double maxSpeed();
	
	/**
	 * 
	 * @return <code>int</code> the value
	 * 			 of this form of transport.
	 * @since 1.1
	 */
	public abstract int value();

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Transport [");
		if (Type() != null) {
			builder.append("Type()=");
			builder.append(Type());
		}
		builder.append("]");
		return builder.toString();
	}
	

}
