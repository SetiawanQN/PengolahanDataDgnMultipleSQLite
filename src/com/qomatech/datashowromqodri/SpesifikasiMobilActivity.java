package com.qomatech.datashowromqodri;

import java.util.ArrayList;
import java.util.List;

import com.qomatech.datashowromqodri.helper.DatabaseHelper;
import com.qomatech.datashowromqodri.model.SpesifikasiMobil;
import com.qomatech.datashowromqodri.model.ListAdapterSpesifikasiMobil;
import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;


public class SpesifikasiMobilActivity extends Activity{
	
	ActionBar actionBar;
	DatabaseHelper db;
	//TableLayout tabelData;
	ListView listView;
	EditText SpesifikasiMobilText, noDel, noUp, SpesifikasiMobilUp;
	Button add, delAll, del, update;
	ListAdapterSpesifikasiMobil adp;
	List<SpesifikasiMobil> list;
	
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_spesifikasimobil);
		
		//tabelData = (TableLayout) findViewById(R.id.tblDataSpesifikasiMobil);
		listView = (ListView) findViewById(R.id.listSpesifikasiMobil);
		SpesifikasiMobilText = (EditText) findViewById(R.id.SpesifikasiMobil);
		add = (Button) findViewById(R.id.addSpesifikasiMobilBtn);
		delAll = (Button) findViewById(R.id.delAllBtn);
		noDel = (EditText) findViewById(R.id.noDel);
		del = (Button) findViewById(R.id.delBtn);
		SpesifikasiMobilUp = (EditText) findViewById(R.id.SpesifikasiMobilUpdate);
		noUp = (EditText) findViewById(R.id.noUpdate);
		update = (Button) findViewById(R.id.updateBtn);
		
		actionBar = getActionBar();
		actionBar.setHomeButtonEnabled(true);
		
		db = new DatabaseHelper(getApplicationContext());
		
		//db.insertSpesifikasiMobil(new SpesifikasiMobil("SpesifikasiMobil 1"));
		//db.insertSpesifikasiMobil(new SpesifikasiMobil("SpesifikasiMobil 2"));
		//db.insertSpesifikasiMobil(new SpesifikasiMobil("SpesifikasiMobil 3"));
		
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
				db.updateSpesifikasiMobil(new SpesifikasiMobil(Integer.parseInt(noUp.getText().toString()), SpesifikasiMobilUp.getText().toString()));
				kosongkanField();
				tampilkanData();
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
				db.deleteAllSpesifikasiMobil();
				tampilkanData();
			}
		});
		
		tampilkanData();
		
		
		db.closeDB();
	}
	
	private void simpanData() {
		// TODO Auto-generated method stub
		try{
			db.insertSpesifikasiMobil(new SpesifikasiMobil(SpesifikasiMobilText.getText().toString()));
			Toast.makeText(getBaseContext(), "Data berhasil disimpan", 
					Toast.LENGTH_SHORT).show();
			tampilkanData();
			kosongkanField();
		}catch(Exception e){
			e.printStackTrace();
			Toast.makeText(getBaseContext(), "Gagal disimpan", Toast.LENGTH_LONG).show();
		}
	}

	private void tampilkanData() {
		// TODO Auto-generated method stub
		
		SpesifikasiMobil fkt = null;
		list = new ArrayList<SpesifikasiMobil>();
		List<SpesifikasiMobil> allSpesifikasiMobil = db.getAllSpesifikasiMobil();
		for (SpesifikasiMobil SpesifikasiMobil : allSpesifikasiMobil) {
			Log.d("Tag Name", SpesifikasiMobil.getSpesifikasiMobil());
			
			fkt = new SpesifikasiMobil();
			fkt.setId(SpesifikasiMobil.getId());
			fkt.setSpesifikasiMobil(SpesifikasiMobil.getSpesifikasiMobil());
			list.add(fkt);
		}
		adp = new ListAdapterSpesifikasiMobil(this, list);
		listView.setAdapter(adp);
		
		/*while(tabelData.getChildCount()>1){
			tabelData.removeViewAt(1);
		}
		List<SpesifikasiMobil> allSpesifikasiMobil = db.getAllSpesifikasiMobil();
		for (SpesifikasiMobil SpesifikasiMobil : allSpesifikasiMobil) {
			Log.d("Tag Name", SpesifikasiMobil.getSpesifikasiMobil());
			
			TableRow tabelBaris = new TableRow(this);
			//tabelBaris.setId(10);
			tabelBaris.setBackgroundColor(color.black);
			tabelBaris.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
			
			TextView idText = new TextView(this);
			idText.setText(SpesifikasiMobil.getId().toString());
			tabelBaris.addView(idText);
			
			TextView SpesifikasiMobilTxt = new TextView(this);
			SpesifikasiMobilTxt.setText(SpesifikasiMobil.getSpesifikasiMobil().toString());
			tabelBaris.addView(SpesifikasiMobilTxt);
			
			tabelData.addView(tabelBaris);
		}*/
	}
	
	private void deleteData(){
		try{
			db.deleteSpesifikasiMobil(Long.parseLong(noDel.getText().toString()));
			Toast.makeText(getBaseContext(), "Data nomor " +noDel.getText().toString()+" berhasil dihapus", 
					Toast.LENGTH_SHORT).show();
			tampilkanData();
			kosongkanField();
		}catch(Exception e){
			e.printStackTrace();
			Toast.makeText(getBaseContext(), "Gagal dihapus", Toast.LENGTH_LONG).show();
		}
	}
	
	private void kosongkanField() {
		// TODO Auto-generated method stub
		SpesifikasiMobilText.setText("");
		noDel.setText("");
		noUp.setText("");
		SpesifikasiMobilUp.setText("");
	}
	
}
