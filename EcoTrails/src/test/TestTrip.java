/**
 * 
 */
package test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import location.Location;
import location.Trip;

import org.xml.sax.SAXException;

/**
 * @author User
 *
 */
public class TestTrip {

	/**
	 * 
	 */
	public TestTrip() {
		super();
	}

	private List<Location> loc = new ArrayList<Location>();
	
	private List<Trip> trip = new ArrayList<Trip>();
	
	private Trip working;
	
	public void load(String s){
		try {
			
			loc= TestPaser.getHistory(s);
			
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	public void loadPath(){
		for(Location l:loc){
			if(working == null){
				working =new Trip();
			}
			
			if(!working.addLocation(l)){
				trip.add(working);
				working = new Trip();
				working.addLocation(l);
			}
		}
		working.TripFinished();
		trip.add(working);
		
	}
	
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		System.out.println(Location.distance(-41.217662,174.809276,-41.216241,174.883777));
		
		System.out.println("Start");
		TestTrip tt = new TestTrip();
		tt.load("src/test/history1.xml");
		tt.load("src/test/history2.xml");
		tt.load("src/test/history3.xml");
		tt.loadPath();
		
		//System.out.println(tt.trip.get(0).getTotalDistance());
		FileWriter fw;
		try {
			fw = new FileWriter(new File("results.txt"));
			for (Trip t :tt.trip){
				t.sortPaths();
			fw.write(t.toString());
			fw.write("\r\n");
			}
			
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Done");
		
	}

}
