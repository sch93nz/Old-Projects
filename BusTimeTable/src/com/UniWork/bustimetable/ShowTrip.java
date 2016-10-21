package com.UniWork.bustimetable;

import systemManagment.DataHandler;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import busInfo.StopTimes;
import busInfo.Stops;
import busInfo.Trips;

public class ShowTrip extends ListActivity {

	private String info;
	private ArrayAdapter<String> adapter;
	private Trips[] trip = null;
	private StopTimes[] times = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_show_trip);
		Intent intent = getIntent();

		String StopMessage = intent.getStringExtra(StopsActivity.EXTRA_MESSAGE);
		String Stops = intent.getStringExtra(StopsActivity.STOP);

		String RoutesMessage = intent.getStringExtra(RoutesActivity.EXTRA_MESSAGE);
		String Routes = intent.getStringExtra(RoutesActivity.ROUTE);

		if(Stops==null)	info = Routes+ RoutesMessage; 
		else			info = Stops + StopMessage;

		load();

	}

	private void load() {
		
		int start = info.indexOf(':')+1;
		int end = info.indexOf(',');

		String id =info.substring(start,end);

		int ID = Integer.parseInt(id);

		if (info.startsWith("R")){
			trip = new DataHandler().getTripsData(this.getFileStreamPath("trips.xml"), ID);
			ListView list = (ListView) this.findViewById(android.R.id.list);
			String[] values = new String[trip.length];
			for(int i=0 ; i<trip.length;i++){
				values[i] = String.valueOf(trip[i].getTrip_id());
			}

			adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,
					android.R.id.text1,values);
			list.setAdapter(adapter);
			list.setOnItemClickListener(new OnItemClickListener(){
				@Override
				public void onItemClick(AdapterView<?> parent, View v, int position,
						long id) {

				}
			});

		} else {

			showStops(ID);


		}
	}


	private void showStops(int iD) {
		StopTimes[] st = new DataHandler().getStopTimesData(this.getFileStreamPath("stop_times.xml"), iD);
		Stops[] stop = new DataHandler().getStopsData(this.getFileStreamPath("stops.xml"), iD);
		ListView list = (ListView) this.findViewById(android.R.id.list);
		String[] values = new String[st.length];

		for(int i=0 ; i<st.length;i++){
			st[i].setStop(stop[0]);
			values[i] = st[i].getStop().getStop_name()+"\r\n"+st[i].getDepTimeString();
		}

		adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,
				android.R.id.text1,values);
		list.setAdapter(adapter);
		list.setOnItemClickListener(new OnItemClickListener(){
			@Override
			public void onItemClick(AdapterView<?> parent, View v, int position,
					long id) {

			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.show_trip, menu);
		return true;
	}

}
