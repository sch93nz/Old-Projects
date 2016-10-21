/**
 * 
 */
package systemManagment;

import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import busInfo.Routes;

/**
 * @author schmidmatt
 *
 */
public class RoutesHandler extends DefaultHandler {

	private boolean route_id = false;
	private boolean agency_id = false;
	private boolean short_name = false;
	private boolean long_name = false;
	private boolean route_type = false;
	private int Route_ID;

	ArrayList<Routes> nodes = new ArrayList<Routes>();
	Routes r ;

	public RoutesHandler(int routeID) {
		this.Route_ID=routeID;
	}

	public void startElement(String uri, String localName,String qName, 
			Attributes attributes) throws SAXException {

		//	System.out.println("Start Element :" + qName);

		if (qName.equalsIgnoreCase("route_id")) {
			route_id = true;
		}

		if (qName.equalsIgnoreCase("agency_id")) {
			agency_id = true;
		}

		if (qName.equalsIgnoreCase("route_short_name")) {
			short_name = true;
		}

		if (qName.equalsIgnoreCase("route_long_name")) {
			long_name = true;
		}

		if (qName.equalsIgnoreCase("route_type")) {
			route_type = true;
		}

	}

	public void endElement(String uri, String localName,
			String qName) throws SAXException {

	}

	public void characters(char ch[], int start, int length) throws SAXException {

		String stuff = new String(ch, start, length);
		stuff = stuff.trim();

		if (route_id) {
			if (Route_ID==-1||Integer.getInteger(stuff)==Route_ID){
				r = new Routes(Integer.parseInt(stuff));
			}
			route_id = false;
		}

		if (agency_id) {
			agency_id = false;
			if(r!=null){
			r.setAgency_id(stuff);
			}
		}

		if (short_name) {
			if(r!=null){
			r.setShortName(stuff);
			}
			short_name = false;
		}

		if (long_name) {
			long_name = false;
			if(r!=null){
			r.setLongName(stuff);
			}
		}

		if(route_type) {
			if(r!=null){
			r.setType(Integer.parseInt(stuff));
			nodes.add(r);
			r= null;
			}
			route_type = false;
		}

	}

	public Routes[] getNodes() {
		Routes[] rute = new Routes[nodes.size()];
		int i =0;
		for (Routes r: nodes){
			rute[i]= r;
			i++;
		}
		return rute;
	}
}
