package com.qomatech.datashowromqodri;

import java.util.ArrayList;
import java.util.List;

import com.qomatech.datashowromqodri.helper.DatabaseHelper;
import com.qomatech.datashowromqodri.model.SpesifikasiMobil;
import com.qomatech.datashowromqodri.model.HargaMobil;
import com.qomatech.datashowromqodri.model.ListAdapterHargaMobil;
import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.Toast;

@SuppressLint("NewApi")
public class HargaMobilActivity extends Activity{
	
	ActionBar actionBar;
	DatabaseHelper db;
	//TableLayout tabelData;
	EditText HargaMobilText, HargaMobilUp, noUp, noDel;
	Button add, update, del, delAll;
	ListView listView;
	ListAdapterHargaMobil adp;
	List<HargaMobil> list;
	
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_hargamobil);
		
		HargaMobilText = (EditText) findViewById(R.id.HargaMobil);
		add = (Button) findViewById(R.id.addHargaMobilBtn);
		noUp = (EditText) findViewById(R.id.noHargaMobilUpdate);
		HargaMobilUp = (EditText) findViewById(R.id.HargaMobilUpdate);
		update = (Button) findViewById(R.id.updateHargaMobilBtn);
		noDel = (EditText) findViewById(R.id.noHargaMobilDel);
		del = (Button) findViewById(R.id.delHargaMobilBtn);
		delAll = (Button) findViewById(R.id.delAllHargaMobilBtn);
		listView = (ListView) findViewById(R.id.listHargaMobil);
		
		db = new DatabaseHelper(getApplicationContext());
		
		actionBar = getActionBar();
		actionBar.setHomeButtonEnabled(true);
		
		//db.insertHargaMobil(new HargaMobil("Benar"));
		//db.insertHargaMobil(new HargaMobil("Salah"));
		
		//db.deleteAllHargaMobil();
		tampilData();
		
		add.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				simpanData();
			}
		});
		
		update.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				updateData();
			}
		});
		
		del.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				deleteData();
			}
		});
		
		delAll.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				deleteAllData();
			}
		});
	}
	
	public void tampilData(){
		
		HargaMobil jwb = null;
		list = new ArrayList<HargaMobil>();
		List<HargaMobil> all = db.getAllHargaMobil();
		for (HargaMobil jawab : all) {
			Log.d("Tag Name", jawab.getHargaMobil());
			
			jwb = new HargaMobil();
			jwb.setId(jawab.getId());
			jwb.setHargaMobil(jawab.getHargaMobil());
			list.add(jwb);
		}
		adp = new ListAdapterHargaMobil(this, list);
		listView.setAdapter(adp);
	}
	
	public void simpanData(){
		try{
			db.insertHargaMobil(new HargaMobil(HargaMobilText.getText().toString()));
			Toast.makeText(getBaseContext(), "Data berhasil disimpan", 
					Toast.LENGTH_SHORT).show();
			tampilData();
			kosongkanField();
		}catch(Exception e){
			e.printStackTrace();
			Toast.makeText(getBaseContext(), "Gagal disimpan", Toast.LENGTH_LONG).show();
		}
	}
	
	private void updateData(){
		try{
			db.updateHargaMobil(new HargaMobil(Integer.parseInt(noUp.getText().toString()), HargaMobilUp.getText().toString()));
			Toast.makeText(getBaseContext(), "Data nomor " +noUp.getText().toString()+" berhasil diupdate", 
					Toast.LENGTH_SHORT).show();
			tampilData();
			kosongkanField();
		}catch(Exception e){
			e.printStackTrace();
			Toast.makeText(getBaseContext(), "Update gagal", Toast.LENGTH_LONG).show();
		}
	}
	
	private void deleteData(){
		try{
			db.deleteHargaMobil(Long.parseLong(noDel.getText().toString()));
			Toast.makeText(getBaseContext(), "Data nomor " +noDel.getText().toString()+" berhasil dihapus", 
					Toast.LENGTH_SHORT).show();
			tampilData();
			kosongkanField();
		}catch(Exception e){
			e.printStackTrace();
			Toast.makeText(getBaseContext(), "Gagal dihapus", Toast.LENGTH_LONG).show();
		}
	}
	
	private void deleteAllData(){
		try{
			db.deleteAllHargaMobil();
			Toast.makeText(getBaseContext(), "Data berhasil dihapus", 
					Toast.LENGTH_SHORT).show();
			tampilData();
			kosongkanField();
		}catch(Exception e){
			e.printStackTrace();
			Toast.makeText(getBaseContext(), "data gagal dihapus", Toast.LENGTH_LONG).show();
		}
	}
	
	private void kosongkanField() {
		// TODO Auto-generated method stub
		HargaMobilText.setText("");
		noDel.setText("");
		noUp.setText("");
		HargaMobilUp.setText("");
	}
}
