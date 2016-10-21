/**
 * 
 */
package busInfo;

/**
 * @author User
 *
 */
public class Routes {


	private final int id;
	private String Agency_id;
	private String shortName;
	private String longName;
	private int type;

	public Routes (int id){
		this.id = id;
	}

	/**
	 * @param shortName the shortName to set
	 */
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	/**
	 * @param longName the longName to set
	 */
	public void setLongName(String longName) {
		this.longName = longName;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(int type) {
		this.type = type;
	}



	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((Agency_id == null) ? 0 : Agency_id.hashCode());
		result = prime * result + id;
		result = prime * result
				+ ((longName == null) ? 0 : longName.hashCode());
		result = prime * result
				+ ((shortName == null) ? 0 : shortName.hashCode());
		result = prime * result + type;
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
		if (getClass() != obj.getClass())
			return false;
		Routes other = (Routes) obj;
		if (Agency_id == null) {
			if (other.Agency_id != null)
				return false;
		} else if (!Agency_id.equals(other.Agency_id))
			return false;
		if (id != other.id)
			return false;
		return true;
	}

	public String getAgency_id() {
		return Agency_id;
	}

	public void setAgency_id(String agency_id) {
		Agency_id = agency_id;
	}
	
	public String toString(){
		return id + " "+Agency_id+" "+shortName+" "+longName+" "+type+"\n";
	}

	public int getId() {
		return id;
	}

	public String getShortName() {
		return shortName;
	}

	public String getLongName() {
		return longName;
	}

	public int getType() {
		return type;
	}

}
