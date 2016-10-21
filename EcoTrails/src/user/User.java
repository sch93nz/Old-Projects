package user;

import java.io.Serializable;
import java.util.TreeSet;

import location.Trip;
import transport.Transport;

public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2844611810327524136L;
	
	/**
	 * 
	 * @param name
	 * @param Password
	 */
	public User(String name, String Password){
		NAME = name;
		PASSWORD = Password;
		
	}
	
	/**
	 * 
	 * @since 1.0
	 */
	private String NAME;
	
	/**
	 * 
	 * @since 1.0
	 */
	private String PASSWORD;
	
	/**
	 * 
	 * @since 1.0
	 */
	private TreeSet<Trip> History = new TreeSet<Trip>();
	
	
	/**
	 * 
	 * @since 1.0
	 */
	private int Score = 0,Enviro =0 ;

	/**
	 * 
	 * @since 1.0
	 */
	private Animal animal;
	
	
	/**
	 * 
	 * @since 1.0
	 */
	private Transport[] transport;
	
	/**
	 * 
	 * @return
	 */
	public String getUser(){
		return NAME;
	}
	
	/**
	 * 
	 * @param <code>String</code> pass
	 * @return This will return true if the pass matches the one on the record.
	 * @since 1.0
	 */
	public boolean IsPassword(String pass){
		return PASSWORD.equals(pass);
	}
	
	
	/**
	 * 
	 * @param <code>String</code> oldPass this the old Password.
	 * @param <code>String</code> newPass this the new Password.
	 * @return <b>True</b> if the the Password was changed 
	 * 			<b>False</b> if it was not..
	 * @since 1.0
	 */
	public boolean changePassword(String oldPass, String newPass){
		if(IsPassword(oldPass)){
			PASSWORD = newPass;
			return true;
		}
		return false;
	}

	/**
	 * @return the nAME
	 * @since 1.0
	 */
	public String getNAME() {
		return NAME;
	}

	/**
	 * @return the history
	 * @since 1.0
	 */
	public TreeSet<Trip> getHistory() {
		return History;
	}

	/**
	 * @return the score
	 * @since 1.0
	 */
	public int getScore() {
		return Score;
	}

	/**
	 * @return the environment state how clean it is and so on
	 * @since 1.0
	 */
	public int getEnviro() {
		return Enviro;
	}

	/**
	 * @return the <code>Animal</code> so you can get
	 * 			 the information about the animal.
	 * @since 1.0
	 */
	public Animal getAnimal() {
		return animal;
	}

	/**
	 * @return the transport
	 * @since 1.0
	 */
	public Transport[] getTransport() {
		return transport;
	}

	/**
	 * @param transport the transport to set
	 * @since 1.0
	 */
	public void setTransport(Transport[] transport) {
		this.transport = transport;
	}
	
	


}
