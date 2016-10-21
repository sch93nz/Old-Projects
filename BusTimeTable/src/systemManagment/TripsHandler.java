/**
 * 
 */
package systemManagment;

import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import busInfo.Trips;

/**
 * @author User
 *
 */
public class TripsHandler extends DefaultHandler {
	private boolean route_id = false;
	private boolean service_id = false;
	private boolean trip_id = false;
	private boolean direction_id = false;
	private boolean block_id = false;
	private boolean shape_id =false; 
	private int Route_ID;

	ArrayList<Trips> nodes = new ArrayList<Trips>();
	Trips r ;

	public TripsHandler(int i){
		Route_ID = i;
	}

	public void startElement(String uri, String localName,String qName, 
			Attributes attributes) throws SAXException {


		if (qName.equalsIgnoreCase("route_id")) {
			route_id = true;
		}

		if (qName.equalsIgnoreCase("service_id")) {
			service_id = true;
		}

		if (qName.equalsIgnoreCase("trip_id")) {
			trip_id = true;
		}

		if (qName.equalsIgnoreCase("direction_id")) {
			direction_id = true;
		}

		if (qName.equalsIgnoreCase("block_id")) {
			block_id = true;
		}

		if (qName.equalsIgnoreCase("shape_id")) {
			shape_id = true;
		}

	}

	public void endElement(String uri, String localName,
			String qName) throws SAXException {

	}

	public void characters(char ch[], int start, int length) throws SAXException {
		String stuff = new String(ch, start, length);
		stuff = stuff.trim();

		if (route_id) {
			int k = Integer.parseInt(stuff);
			if(k==Route_ID){
				r = new Trips(k);
			}
			route_id = false;
		}

		if (service_id) {
			if(r!=null)
				r.setService_id(Integer.parseInt(stuff));
			service_id = false;
		}

		if (trip_id) {
			if(r!=null)
				r.setTrip_id(Integer.parseInt(stuff));
			trip_id = false;
		}

		if (direction_id) {
			if(r!=null)
				r.setDirection(Integer.parseInt(stuff));
			direction_id = false;
		}

		if(block_id) {
			if(r!=null)
				r.setBlock_id(Integer.parseInt(stuff));
			block_id = false;
		}

		if(shape_id){
			if(r!=null){
				r.setShape_id(Integer.parseInt(stuff));
				nodes.add(r);
				r= null;
			}
			shape_id=false;
		}

	}

	public Trips[] getNodes() {
		Trips[] trips = new Trips[nodes.size()];
		int i =0;
		for (Trips r: nodes){
			trips[i]= r;
			i++;
		}
		return trips;
	}
}
