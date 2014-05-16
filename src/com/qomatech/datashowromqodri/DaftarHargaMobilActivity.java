package com.qomatech.datashowromqodri;

import java.util.ArrayList;
import java.util.List;

import com.qomatech.datashowromqodri.helper.DatabaseHelper;
import com.qomatech.datashowromqodri.model.SpesifikasiMobil;
import com.qomatech.datashowromqodri.model.ListAdapterDaftarHargaMobil;
import com.qomatech.datashowromqodri.model.DaftarHargaMobil;
import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

@SuppressLint("NewApi")
public class DaftarHargaMobilActivity extends Activity{
	
	ActionBar actionBar;
	DatabaseHelper db;
	ListAdapterDaftarHargaMobil adp;
	List<DaftarHargaMobil> list;
	ListView listView;
	AutoCompleteTextView autoSpesifikasiMobil, autoHargaMobil;
	Button add;
	
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_daftarhargamobil);
		
		listView = (ListView) findViewById(R.id.listDaftarHargaMobil);
		autoSpesifikasiMobil = (AutoCompleteTextView) findViewById(R.id.autoSpesifikasiMobil);
		autoHargaMobil = (AutoCompleteTextView) findViewById(R.id.autoHargaMobil);
		add = (Button) findViewById(R.id.addDaftarHargaMobil);
		
		actionBar = getActionBar();
		actionBar.setHomeButtonEnabled(true);
		
		db = new DatabaseHelper(getApplicationContext());
		
		//db.insertDaftarHargaMobil(new DaftarHargaMobil(1,1));
		
		Integer[] id_SpesifikasiMobil = db.getAllIdSpesifikasiMobil();
		ArrayAdapter<Integer> adapter1 = new ArrayAdapter<Integer>(this, android.R.layout.simple_dropdown_item_1line, id_SpesifikasiMobil);
		autoSpesifikasiMobil.setAdapter(adapter1);
		
		Integer[] id_HargaMobil = db.getAllIdHargaMobil();
		ArrayAdapter<Integer> adapter2 = new ArrayAdapter<Integer>(this, android.R.layout.simple_dropdown_item_1line, id_HargaMobil);
		autoHargaMobil.setAdapter(adapter2);
		
		tampilkanData();
		
		add.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				simpanData();
			}
		});
	}
	
	private void tampilkanData() {
		// TODO Auto-generated method stub
		
		DaftarHargaMobil per = null;
		list = new ArrayList<DaftarHargaMobil>();
		List<DaftarHargaMobil> all = db.getAllDaftarHargaMobil();
		for (DaftarHargaMobil daftarhargamobil : all) {
			Log.d("Tag Name", daftarhargamobil.getHargaMobil());
			
			per = new DaftarHargaMobil();
			per.setId(daftarhargamobil.getId());
			per.setDaftarHargaMobil(daftarhargamobil.getDaftarHargaMobil());
			per.setHargaMobil(daftarhargamobil.getHargaMobil());
			list.add(per);
		}
		adp = new ListAdapterDaftarHargaMobil(this, list);
		listView.setAdapter(adp);
	}
	
	private void simpanData() {
		// TODO Auto-generated method stub
		try{
			db.insertDaftarHargaMobil(new DaftarHargaMobil(Integer.parseInt(autoSpesifikasiMobil.getText().toString()), Integer.parseInt(autoHargaMobil.getText().toString())));
			Toast.makeText(getBaseContext(), "Data berhasil disimpan", 
					Toast.LENGTH_SHORT).show();
			tampilkanData();
			kosongkanField();
		}catch(Exception e){
			e.printStackTrace();
			Toast.makeText(getBaseContext(), "Gagal disimpan", Toast.LENGTH_LONG).show();
		}
	}
	
	private void kosongkanField() {
		// TODO Auto-generated method stub
		autoSpesifikasiMobil.setText("");
		autoHargaMobil.setText("");
	}
	
}
