package com.UniWork.bustimetable;

import systemManagment.DataHandler;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import busInfo.Stops;

public class StopsActivity extends ListActivity {

	public static final String EXTRA_MESSAGE = "com.UniWork.bustimetable.RoutesActivity.Direction";
	public static final String STOP = "com.UniWork.bustimetable.RoutesActivity.Stop";
	private Stops[] stops;
	private ArrayAdapter<String> adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_stops);
		ListView list = (ListView) this.findViewById( android.R.id.list);
		DataHandler dh = new DataHandler();
		stops =	dh.getStopsData(this.getFileStreamPath("stops.xml"));

		String[] values = new String[stops.length];

		for(int i = 0 ; i<stops.length;i++){
			values[i] = stops[i].getStop_name();
		}

		adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,
				android.R.id.text1,values);
		list.setAdapter(adapter);
		list.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> parent, View v, int position,
					long id) {
				showPopup(StopsActivity.this,getStops(position));
			}
		});
	}

	protected Stops getStops(int position){
		return stops[position];
	}

	protected CharSequence getRouteName(int position) {
		return adapter.getItem(position);
	}

	// The method that displays the popup.
	private void showPopup(Activity c,final Stops s) {
		int popupWidth = 200;
		int popupHeight = 150;

		// Inflate the popup_layout.xml

		LayoutInflater layoutInflater = (LayoutInflater) c
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View layout = layoutInflater.inflate(R.layout.popup_layout, null);
		LinearLayout viewGroup = (LinearLayout) layout.findViewById(R.id.popup);

		// Creating the PopupWindow
		PopupWindow popup = new PopupWindow(c);
		popup.setContentView(layout);
		popup.setWidth(popupWidth);
		popup.setHeight(popupHeight);
		popup.setFocusable(true);

		// Displaying the popup at the specified location, + offsets.
		popup.showAtLocation(viewGroup, Gravity.CENTER, 0, 0);

		// Getting a reference to Close button, and close the popup when clicked.
		Button but = (Button) layout.findViewById(R.id.inbound);

		but.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				inboundButton(s);
			}
		});

		but = (Button) layout.findViewById(R.id.outbound);

		but.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				outBoundButton(s);
			}
		});


	}

	protected void outBoundButton(Stops s) {
		Intent intent = new Intent(this, ShowTrip.class);
		intent.putExtra(EXTRA_MESSAGE, "outbound");
		intent.putExtra(STOP, "Stop id:"+s.getStop_id()+",");
		this.startActivity(intent);
	}

	protected void inboundButton(Stops s) {
		Intent intent = new Intent(this, ShowTrip.class);
		intent.putExtra(EXTRA_MESSAGE, "outbound");
		intent.putExtra(STOP, "Stop id:"+s.getStop_id()+",");
		this.startActivity(intent);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.stops, menu);
		return true;
	}

}
