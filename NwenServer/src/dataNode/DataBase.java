/**
 * 
 */
package dataNode;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * @author schmidmatt
 *
 */
public class DataBase {

	private static HashMap<Long,ConData> data = new HashMap<Long,ConData>();
	
	public boolean addData(String name , long number){
		 data.put(number,new ConData(name,number));
		 return data.containsKey(number);
	}

	public String getContacts(long[] No){
		HashSet<Long> numbers = new HashSet<Long>();
		StringBuffer str = new StringBuffer();
		
		for( Long n:No){
			numbers.add(n);
		}
		Set<Long> num =data.keySet();
		if (!num.containsAll(numbers)){
			for (Long n: numbers){
				if (num.contains(n)){
					str.append( data.get(n).toString());
				}
			}
		}
			return str.toString();
	}
	
}
