package busInfo;

public class TimeString  implements Comparable<TimeString> {

	

	private int hours;
	private int minutes;
	private int seconds;

	public TimeString(String t){
		String hours = t.substring(0, t.indexOf(':'));
		t=t.substring(t.indexOf(':')+1);
		this.hours = Integer.parseInt(hours);
		String minutes =t.substring(0, t.indexOf(':'));
		this.minutes =Integer.parseInt(minutes);
		String seconds = t.substring(t.indexOf(':')+1);
		this.seconds = Integer.parseInt(seconds);
	}

	/**
	 * @return the hours
	 */
	public int getHours() {
		return hours;
	}

	/**
	 * @return the minutes
	 */
	public int getMinutes() {
		return minutes;
	}

	/**
	 * @return the seconds
	 */
	public int getSeconds() {
		return seconds;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + hours;
		result = prime * result + minutes;
		result = prime * result + seconds;
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
		TimeString other = (TimeString) obj;
		if (hours != other.hours)
			return false;
		if (minutes != other.minutes)
			return false;
		if (seconds != other.seconds)
			return false;
		return true;
	}

	@Override
	public int compareTo(TimeString i) {
		Integer oHours = i.getHours();
		if(oHours.compareTo((Integer)this.hours)==0){
			Integer oMinutes = i.getMinutes(); 
			if(oMinutes.compareTo((Integer)this.minutes)==0){
				Integer oSeconds = i.getSeconds();
				return oSeconds.compareTo((Integer)this.seconds);
			}else
				return oMinutes.compareTo((Integer) minutes);
		} else{
			return 	oHours.compareTo((Integer) hours);
		}
	}

	@Override
	public String toString() {
		return " " + hours + ":" + minutes+ "." + seconds + "\r\n";
	}
	
	public static void main(String[] s){
		
	}

}
