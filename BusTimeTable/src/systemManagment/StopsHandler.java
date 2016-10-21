/**
 * 
 */
package systemManagment;

import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import busInfo.Stops;

/**
 * @author User
 *
 */
public class StopsHandler extends DefaultHandler {
	
	private boolean stop_id = false;
	private boolean check = false;
	private boolean stop_name = false;
	private boolean stop_lat = false;
	private boolean stop_lon = false;
	private int STOP_ID;
	
	ArrayList<Stops> nodes = new ArrayList<Stops>();
	Stops r ;

	public StopsHandler(int stopID) {
		this.STOP_ID=stopID;
	}

	public void startElement(String uri, String localName,String qName, 
			Attributes attributes) throws SAXException {

		if (qName.equalsIgnoreCase("stop_id")) {
			stop_id = true;
		}

		if (qName.equalsIgnoreCase("check")) {
			check = true;
		}

		if (qName.equalsIgnoreCase("stop_name")) {
			stop_name = true;
		}

		if (qName.equalsIgnoreCase("stop_lat")) {
			stop_lat = true;
		}

		if (qName.equalsIgnoreCase("stop_lon")) {
			stop_lon = true;
		}

	}

	public void endElement(String uri, String localName,
			String qName) throws SAXException {

		}

	public void characters(char ch[], int start, int length) throws SAXException {
		String stuff = new String(ch, start, length);
		stuff = stuff.trim();
	
		if (stop_id) {
			if(STOP_ID==-1||STOP_ID==Integer.getInteger(stuff)){
				r = new Stops(Integer.parseInt(stuff));
			}
			stop_id = false;
		}

		if (check) {
			if(r!=null){
				r.setCheck(Integer.parseInt(stuff));
			}
			check = false;
		}

		if (stop_name) {
			if (r!=null){
				r.setStop_name(stuff);
			}
			stop_name = false;
		}

		if (stop_lat) {
			if(r!=null){
				r.setLat(Float.parseFloat(stuff));
			}
			stop_lat = false;
		}

		if(stop_lon) {
			if(r!=null){
				r.setLon(Float.parseFloat(stuff));
				nodes.add(r);
				r= null;
			}
			stop_lon = false;
		}
	}

	public Stops[] getNodes() {
		Stops[] stop = new Stops[nodes.size()];
		int i =0;
		for (Stops r: nodes){
			stop[i]= r;
			i++;
		}
		return stop;
	}
}
