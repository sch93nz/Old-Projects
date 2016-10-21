/**
 * 
 */
package systemManagment;

import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import busInfo.StopTimes;
import busInfo.TimeString;

/**
 * @author User
 *
 */
public class StopTimesHandler extends DefaultHandler {

	private boolean trip_id = false;
	private boolean arrival_time = false;
	private boolean departure_time = false;
	private boolean stop_id = false;
	private boolean stop_sequence = false;
	private boolean pickup_type = false;
	private boolean drop_off_type = false;
	private int StopID;


	ArrayList<StopTimes> nodes = new ArrayList<StopTimes>();
	StopTimes r ;

	public StopTimesHandler(int stopID) {
		this.StopID=stopID;
	}

	public void startElement(String uri, String localName,String qName, 
			Attributes attributes) throws SAXException {

	
		if (qName.equalsIgnoreCase("trip_id")) {
			trip_id = true;
		}

		if (qName.equalsIgnoreCase("arrival_time")) {
			arrival_time = true;
		}

		if (qName.equalsIgnoreCase("departure_time")) {
			departure_time = true;
		}

		if (qName.equalsIgnoreCase("stop_id")) {
			stop_id = true;
		}

		if (qName.equalsIgnoreCase("stop_sequence")) {
			stop_sequence = true;
		}

		if (qName.equalsIgnoreCase("pickup_type")) {
			pickup_type = true;
		}

		if(qName.equalsIgnoreCase("drop_off_type")){
			drop_off_type= true;	
		}

	}

	public void endElement(String uri, String localName,
			String qName) throws SAXException {

		
	}

	public void characters(char ch[], int start, int length) throws SAXException {
		String stuff = new String(ch, start, length);
		stuff = stuff.trim();

		if (trip_id) {
			r = new StopTimes(Integer.parseInt(stuff));
			trip_id = false;
		}

		if (arrival_time) {
			System.out.println(stuff);
			r.setArrival_time(new TimeString(stuff));
			arrival_time = false;
		}

		if (departure_time) {
			r.setDeparture_time(new TimeString(stuff));
			departure_time = false;
		}

		if (stop_id) {
			int k = Integer.parseInt(stuff);
			if (this.StopID==k) {	
				r.setStop_id(k);
			}else{ 				
				r = null;
				}
			stop_id = false;
		}
		
		if(stop_sequence) {
			if (r!=null){
				r.setStop_sequence(Integer.parseInt(stuff));
			}
			stop_sequence = false;
		}

		if(pickup_type) {
			if (r!=null)	{	
				r.setPickup_type(Integer.parseInt(stuff));
			}
			pickup_type = false;
		}

		if (drop_off_type){
			if (r!=null){
				r.setDrop_off_type(Integer.parseInt(stuff));
				nodes.add(r);
				r= null;
			}
			drop_off_type =false;
		}
	}

	public StopTimes[] getNodes() {
		StopTimes[] stop = new StopTimes[nodes.size()];
		int i =0;
		for (StopTimes r: nodes){
			stop[i]= r;
			i++;
		}
		return stop;
	}
}
