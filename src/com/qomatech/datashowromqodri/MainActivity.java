package com.qomatech.datashowromqodri;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends ListActivity {
	ListView listView;
	private String[] menuItems = {"Menu CRUD SpesifikasiMobil","Menu CRUD HargaMobil","Menu CRUD DaftarHargaMobil"};
	private String[] menuClassNames = {SpesifikasiMobilActivity.class.getName(), HargaMobilActivity.class.getName(), DaftarHargaMobilActivity.class.getName()};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.fragment_main);
		setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, menuItems));
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id)
	   {
	       super.onListItemClick(l, v, position, id);
	       try
	       {
	           Intent intent = new Intent(this, Class.forName(menuClassNames[position]));
	           startActivity(intent);
	       }
	       catch (ClassNotFoundException e)
	       {
	           e.printStackTrace();
	       }
	   }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
