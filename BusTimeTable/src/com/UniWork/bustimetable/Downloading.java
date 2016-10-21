package com.UniWork.bustimetable;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import systemManagment.DataHandler;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

public class Downloading extends Activity {

	private boolean Data = false;
	private boolean update = false; // weather it should bother to auto update
	private boolean updated = false; // tells you weather it has done a update of the files on bored
	private boolean download = false;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_downloading);

		CheckBox box = (CheckBox)findViewById(R.id.checkBox1);
		if (update) box.setChecked(true);
		if (box.isChecked()) update =true;
		else update=false;

		Button next = (Button)findViewById(R.id.button1);
		next.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				next(Data);
			}
		});

		load();
	}



	private void load() {

		DataHandler getter = new DataHandler();
		try {

			String[] settings =getter.getStartInfo(this.openFileInput("settings.dat"));

			if (settings[0].equalsIgnoreCase("t")){ // auto update
				update =true;
			}
			if (hasFiles()){// has data
				Data = true;
				next(true);
			} else {
				download=true;
			}

		} catch (FileNotFoundException e) {
			try {
				
				getter.createSettings(this.openFileOutput("settings.dat", Context.MODE_PRIVATE));
				download = true;
				
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
		}
		if (download) download();

	}

	private void download() {
		ConnectivityManager connMgr = (ConnectivityManager) 
				getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

		if (networkInfo != null && networkInfo.isConnected()) {
			try {
				System.out.println("downloading the files");
				Download();
				System.out.println("finished downloading the files");
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("Failed to Download");
				next(false);
			} catch (IOException e) {
				System.out.println("Failed to Download");
				e.printStackTrace();
				next(false);
			}
		} else {
			next(false);
		}

	}

	private void Download() throws IOException {

		Toast.makeText(this,"Downloading", Toast.LENGTH_LONG).show();
		URL url = new URL("http://ecs.victoria.ac.nz/~schmidmatt/busTimeTables/routes.zip");
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setReadTimeout(10000);
		conn.setConnectTimeout(15000);
		conn.setRequestMethod("GET");
		conn.setDoInput(true);
		conn.connect();
		int responce = conn.getResponseCode();
		Log.d("Downloading", "The responce is: "+responce);
		InputStream in = conn.getInputStream();
		BufferedInputStream  read = new BufferedInputStream(in);
		OutputStream os = this.openFileOutput("routes.zip", MODE_PRIVATE); 
		int i;
		while ((i = read.read()) != -1 ){
			os.write(i);
		}
		in.close();
		read.close();
		os.close();
		File file = this.getFileStreamPath("routes.zip");

		if (unpackZip(file)){
			System.out.println("The download has finished");
			Data = true;
			updated = true;
			next(true);
		}else {
			System.out.println("Something went wrong run. Try again");
			next(false);
		}

	}

	private boolean unpackZip(File f)
	{
		System.out.println(f.length());
		InputStream is;
		ZipInputStream zis;
		String path = f.getParentFile().getPath()+"/";
		System.out.println(path);
		try 
		{
			String filename;

			is = new FileInputStream(f);
			zis = new ZipInputStream(new BufferedInputStream(is));          
			ZipEntry ze;
			byte[] buffer = new byte[70000];
			int count;

			while ((ze = zis.getNextEntry()) != null) 
			{
				// zapis do souboru
				filename = ze.getName();

				// Need to create directories if not exists, or
				// it will generate an Exception...
				if (ze.isDirectory()) {
					File fmd = new File(path + filename);
					fmd.mkdirs();
					continue;
				}

				FileOutputStream fout = new FileOutputStream(path + filename);

				// cteni zipu a zapis
				while ((count = zis.read(buffer)) != -1) {
					fout.write(buffer, 0, count);             
				}

				fout.close();

				System.out.println(new File(path+filename).length());
				System.out.println(path+filename);

				zis.closeEntry();
			}

			zis.close();
		} 
		catch(IOException e)
		{
			e.printStackTrace();
			return false;
		}

		return true;
	}

	private void next(boolean t) {
		//System.out.println(check());
		if (updated){
			try {
				new DataHandler().savingSettings(this.openFileOutput("settings.dat", MODE_PRIVATE), Data, update, System.currentTimeMillis());
			} catch (FileNotFoundException e) {

			}
		}
		if (t){

			// go to next activity with the data on application
			Intent intent = new Intent(this,MainActivity.class);
			this.startActivity(intent);
		} else{
			// GO TO NEXT ACTIVITY WITH THE DATA ON THE NET 
		}
	}

	/**
	 * just checks to see if all the files were downloaded
	 */
	private String check() {
		StringBuffer buff = new StringBuffer();
		if (this.getFileStreamPath("routes.xml").exists() && this.getFileStreamPath("routes.xml").length() > 0 ){
			buff.append("This devise has routes.xml\r\n");
		}	else {
			buff.append("This devise has not got routes.xml\r\n");
		}
		if (this.getFileStreamPath("stop_times.xml").exists() && this.getFileStreamPath("stop_times.xml").length() > 0){
			buff.append("This devise has stop_times.xml\r\n");
		} else {
			buff.append("This devise has not got stop_times.xml\r\n");
		}
		if (this.getFileStreamPath("stops.xml").exists() && this.getFileStreamPath("stops.xml").length() > 0){
			buff.append("This devise has stops.xml\r\n");
		} else {
			buff.append("This devise has not got stops.xml\r\n");
		}
		if (this.getFileStreamPath("trips.xml").exists() && this.getFileStreamPath("trips.xml").length() > 0){
			buff.append("This devise has trips.xml\r");
		} else {
			buff.append("This devise has not got trips.xml\r\n");
		}
		return (buff.toString());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_downloading, menu);
		return true;
	}


	private boolean hasFiles(){
		return (this.getFileStreamPath("routes.xml").exists() && this.getFileStreamPath("routes.xml").length() > 0 ) &&
				(this.getFileStreamPath("stop_times.xml").exists() && this.getFileStreamPath("stop_times.xml").length() > 0) &&
				(this.getFileStreamPath("stops.xml").exists() && this.getFileStreamPath("stops.xml").length() > 0) &&
				(this.getFileStreamPath("trips.xml").exists() && this.getFileStreamPath("trips.xml").length() > 0);
	}


}
