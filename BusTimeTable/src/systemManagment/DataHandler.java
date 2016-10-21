package systemManagment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import android.util.Log;
import busInfo.Routes;
import busInfo.StopTimes;
import busInfo.Stops;
import busInfo.Trips;



/**
 * @author User
 *
 */
public class DataHandler {
	/**
	 *  reads in the starting info so when was last update and what the version was also the user options
	 * @param fis
	 * @return 
	 */
	public String[] getStartInfo(FileInputStream fis){

		try {
			byte[] data = new byte[fis.available()];
			fis.read(data);
			String info = new String(data);
		//	System.out.println(info);
			String autoUpdate = info.substring(0, 1);
			String hasData = info.substring(1,info.indexOf(':'));
			String version = info.substring(info.indexOf(':')+1);
		//	System.out.println(version);
			version = version.trim();
			return new String[]{autoUpdate, hasData, version};

		} catch (IOException e) {

		}
		return new String[]{"T","F","000"};

	}

	public void createSettings(FileOutputStream FoS) {
		try {
			FoS.write("TF:0000".getBytes());
			FoS.close();
		} catch (IOException e) {

			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unused")
	private void printFile(File file) {

		System.out.println(file.exists());
		System.out.println(file.canRead());
		System.out.println(file.length());

		try {

			FileInputStream in = new FileInputStream(file);
			System.out.println(in.available());
			byte[] data =new byte[in.available()];
			in.read(data, 0, in.available());
			System.out.println(new String(data));	
			in.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}

	public void savingSettings(FileOutputStream FoS, boolean files , boolean update, long version) {	
		try {

			StringBuffer data = new StringBuffer();
			if(files)
				data.append('T');
			else
				data.append('F');

			if(update)
				data.append('T');
			else
				data.append('F');
			data.append(':');
			data.append(version);

			FoS.write(data.toString().getBytes());

			FoS.close();

		} catch (IOException e) { 

			e.printStackTrace();
		}
	}
	
	public Routes[] getRoutesData(File file) {
		return getRoutesData(file,-1);
	}
	

	public Routes[] getRoutesData(File file,int RouteID) {
		RoutesHandler rand = new RoutesHandler(RouteID);
		try { 

			//			printFile(file);

			SAXParserFactory spf = SAXParserFactory.newInstance(); 
			SAXParser sp = spf.newSAXParser(); 

			XMLReader xr = sp.getXMLReader(); 

			xr.setContentHandler(rand);

			InputSource is = new InputSource(new FileInputStream(file.getAbsolutePath()));

			xr.parse(is);

		} catch(SAXException se) { 
			Log.e("SAX XML", "sax error", se); 
			se.printStackTrace();
		} catch(IOException ioe) { 
			Log.e("SAX XML", "sax parse io error", ioe);
			ioe.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		Routes[] r = rand.getNodes();
		return  r;
	}

	public Stops[] getStopsData(File file) {
		return getStopsData(file,-1);
	}
	
	public Stops[] getStopsData(File file,int stopID) {
		StopsHandler stops = new StopsHandler(stopID);
		try { 

			//			printFile(file);

			SAXParserFactory spf = SAXParserFactory.newInstance(); 
			SAXParser sp = spf.newSAXParser(); 

			XMLReader xr = sp.getXMLReader(); 

			xr.setContentHandler(stops);

			InputSource is = new InputSource(new FileInputStream(file.getAbsolutePath()));

			xr.parse(is);

		} catch(SAXException se) { 
			Log.e("SAX XML", "sax error", se); 
			se.printStackTrace();
		} catch(IOException ioe) { 
			Log.e("SAX XML", "sax parse io error", ioe);
			ioe.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		Stops[] s = stops.getNodes();
		return s;
	}
	
	public Trips[] getTripsData(File file, int i) {
		TripsHandler trips = new TripsHandler(i);
		try { 

			//			printFile(file);

			SAXParserFactory spf = SAXParserFactory.newInstance(); 
			SAXParser sp = spf.newSAXParser(); 

			XMLReader xr = sp.getXMLReader(); 

			xr.setContentHandler(trips);

			InputSource is = new InputSource(new FileInputStream(file.getAbsolutePath()));

			xr.parse(is);

		} catch(SAXException se) { 
			Log.e("SAX XML", "sax error", se); 
			se.printStackTrace();
		} catch(IOException ioe) { 
			Log.e("SAX XML", "sax parse io error", ioe);
			ioe.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		Trips[] t = trips.getNodes();
		return t;
	}
	
	public StopTimes[] getStopTimesData(File file, int StopID) {
		StopTimesHandler stopTimes = new StopTimesHandler(StopID);
		try { 

			//			printFile(file);

			SAXParserFactory spf = SAXParserFactory.newInstance(); 
			SAXParser sp = spf.newSAXParser(); 

			XMLReader xr = sp.getXMLReader(); 

			xr.setContentHandler(stopTimes);

			InputSource is = new InputSource(new FileInputStream(file.getAbsolutePath()));

			xr.parse(is);

		} catch(SAXException se) { 
			Log.e("SAX XML", "sax error", se); 
			se.printStackTrace();
		} catch(IOException ioe) { 
			Log.e("SAX XML", "sax parse io error", ioe);
			ioe.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		StopTimes[] st = stopTimes.getNodes(); 
		return  st;
	}

	
}

