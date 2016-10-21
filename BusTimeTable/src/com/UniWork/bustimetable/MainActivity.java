package com.UniWork.bustimetable;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TabHost;

public class MainActivity extends TabActivity  {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// Show the Up button in the action bar.
		
		Resources res = getResources(); // Resource object to get Drawables
		TabHost tabHost = getTabHost();  // The activity TabHost
		TabHost.TabSpec spec;  // Resusable TabSpec for each tab
		Intent intent;  // Reusable Intent for each tab

		// Create an Intent to launch an Activity for the tab (to be reused)
		intent = new Intent().setClass(this, RoutesActivity.class);// RoutesActivity.class

		// Initialize a TabSpec for each tab and add it to the TabHost
		spec = tabHost.newTabSpec("Routes").setIndicator("",
				res.getDrawable(R.drawable.routes))
				.setContent(intent);
		tabHost.addTab(spec);

		// Do the same for the other tabs
		intent = new Intent().setClass(this, StopsActivity.class);//StopsActivity.class
		spec = tabHost.newTabSpec("stops").setIndicator("",
				res.getDrawable(R.drawable.stops))
				.setContent(intent);
		tabHost.addTab(spec);

		intent = new Intent().setClass(this, SettingsActivity.class);//SettingsActivity.class
		spec = tabHost.newTabSpec("settings").setIndicator("",
				res.getDrawable(R.drawable.settings))
				.setContent(intent);
		tabHost.addTab(spec);

		tabHost.setCurrentTab(0);

	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}


}
