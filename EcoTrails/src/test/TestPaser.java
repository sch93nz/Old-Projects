/**
 * 
 */
package test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import location.Location;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

/**
 * @author User
 *
 */
public class TestPaser {

	public TestPaser(){}

	public static List<Location> getHistory(String f) throws ParserConfigurationException, IOException, SAXException{

		ArrayList<Location> loc = new ArrayList<Location>();
		File file = new File(f);
		DocumentBuilderFactory Factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = Factory.newDocumentBuilder();
		Document doc = builder.parse(file);

		doc.getDocumentElement().normalize();

		NodeList coord = doc.getElementsByTagName("gx:coord");
		NodeList when = doc.getElementsByTagName("when");

		for(int temp =0 ; temp<coord.getLength(); temp++){
			Node nCoord =coord.item(temp);
			
			double[] location = null;
			
			if(nCoord.getNodeType() == Node.ELEMENT_NODE){
				location = getLocation(nCoord.getTextContent());
			}
			
			String s = null;
			
			Node nWhen = when.item(temp);
			
			if(nWhen.getNodeType()==Node.ELEMENT_NODE){
				 s = getString(nWhen.getTextContent());
			}
			
			loc.add(new Location(location[0],location[1],s));
		}
		
		return loc;
	}

	private static String getString(String s) {
		
		String t=s.replace('T' , ' ');
		t=t.substring(0,t.lastIndexOf('.'));
		//System.out.println(t);
		return t;
	}

	private static double[] getLocation(String string) {
		String[] coord = string.split(" "); 
		double[] X_Y_Z = new double[coord.length];
		for (int i =0; i<coord.length;i++){
			X_Y_Z[i] = Double.parseDouble(coord[i]);
		}
		return X_Y_Z;
	}

}
