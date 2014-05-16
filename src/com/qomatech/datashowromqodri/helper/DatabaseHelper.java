package com.qomatech.datashowromqodri.helper;
	
	import java.util.ArrayList;
	import java.util.List;

	import com.qomatech.datashowromqodri.model.SpesifikasiMobil;
	import com.qomatech.datashowromqodri.model.HargaMobil;
	import com.qomatech.datashowromqodri.model.DaftarHargaMobil;
	import android.content.ContentValues;
	import android.content.Context;
	import android.database.Cursor;
	import android.database.sqlite.SQLiteDatabase;
	import android.database.sqlite.SQLiteOpenHelper;
	import android.util.Log;

	public class DatabaseHelper extends SQLiteOpenHelper{
		
		private static final String LOG = "DatabaseHelper"; // Logcat tag
		private static final int DATABASE_VERSION = 1; // Database version
		private static final String DATABASE_NAME = "questions"; // Database Name
		
		// Tables Name
		private static final String TABLE_SPESIFIKASIMOBIL = "spesifikasimobil";
		private static final String TABLE_HARGAMOBIL = "hargamobil";
		private static final String TABLE_DAFTARHARGAMOBIL = "daftarhargamobil";
		
		// FAKTA Table - column names
		private static final String KEY_SPESIFIKASIMOBIL_ID = "id_spesifikasimobil";
		private static final String KEY_SPESIFIKASIMOBIL = "spesifikasimobil";
		
		// JAWABAN Table - column names
		private static final String KEY_HARGAMOBIL_ID = "id_hargamobil";
		private static final String KEY_HARGAMOBIL = "hargamobil";
		
		// PERTANYAAN Table - column names
		private static final String KEY_DAFTARHARGAMOBIL_ID = "id_daftarhargamobil";	
		
		// Table Create Statements
		// Fakta table create statement
		private static final String CREATE_TABLE_SPESIFIKASIMOBIL = "CREATE TABLE " + TABLE_SPESIFIKASIMOBIL + 
				"(" + KEY_SPESIFIKASIMOBIL_ID + " INTEGER PRIMARY KEY," + 
				KEY_SPESIFIKASIMOBIL + " TEXT" +")";
		
		//Jawaban table create statement
		private static final String CREATE_TABLE_HARGAMOBIL = "CREATE TABLE " + TABLE_HARGAMOBIL + 
				"(" + KEY_HARGAMOBIL_ID + " INTEGER PRIMARY KEY," + 
				KEY_HARGAMOBIL + " TEXT" + ")";
		
		private static final String CREATE_TABLE_DAFTARHARGAMOBIL = "CREATE TABLE " +TABLE_DAFTARHARGAMOBIL + 
				"(" + KEY_DAFTARHARGAMOBIL_ID + " INTEGER PRIMARY KEY," + 
				KEY_SPESIFIKASIMOBIL_ID + " INTEGER," + 
				KEY_HARGAMOBIL_ID + " INTEGER" + ")";

		public DatabaseHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			// creating required tebles
			db.execSQL(CREATE_TABLE_SPESIFIKASIMOBIL);
			db.execSQL(CREATE_TABLE_HARGAMOBIL);
			db.execSQL(CREATE_TABLE_DAFTARHARGAMOBIL);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// on upgrade drop older tables
			db.execSQL("DROP TABLE IF EXISTS " + TABLE_SPESIFIKASIMOBIL);
			db.execSQL("DROP TABLE IF EXISTS " + TABLE_HARGAMOBIL);
			db.execSQL("DROP TABLE IF EXISTS " + TABLE_DAFTARHARGAMOBIL);
			
			// create new tables
			onCreate(db);
		}
		
		/* Fakta method */
		public void insertSpesifikasiMobil(SpesifikasiMobil spesifikasimobil){
			SQLiteDatabase db = this.getWritableDatabase();
			
			ContentValues values = new ContentValues();
			values.put(KEY_SPESIFIKASIMOBIL_ID, spesifikasimobil.getId());
			values.put(KEY_SPESIFIKASIMOBIL, spesifikasimobil.getSpesifikasiMobil());
			
			//insert row
			try{
				db.insert(TABLE_SPESIFIKASIMOBIL, null, values);
			}catch(Exception e){
				Log.e("DB ERROR", e.toString());
				e.printStackTrace();
			}
		}
		
		public Integer[] getAllIdSpesifikasiMobil(){
			String query = "SELECT * FROM " + TABLE_SPESIFIKASIMOBIL;
			
			Log.e(LOG, query);
			
			SQLiteDatabase db = this.getReadableDatabase();
			Cursor c = db.rawQuery(query, null);
			
			if(c.getCount() >0)
	        {
	            Integer[] str = new Integer[c.getCount()];
	            int i = 0;
	 
	            while (c.moveToNext())
	            {
	                 str[i] = c.getInt(c.getColumnIndex(KEY_SPESIFIKASIMOBIL_ID));
	                 i++;
	             }
	            return str;
	        }
	        else
	        {
	            return new Integer[] {};
	        }
		}
		
		public List<SpesifikasiMobil> getAllSpesifikasiMobil(){
			List<SpesifikasiMobil> spesifikasimobil = new ArrayList<SpesifikasiMobil>();
			String query = "SELECT * FROM " + TABLE_SPESIFIKASIMOBIL;
			
			Log.e(LOG, query);
			
			SQLiteDatabase db = this.getReadableDatabase();
			Cursor c = db.rawQuery(query, null);
			
			if(c.moveToFirst()){
				do{
					SpesifikasiMobil f = new SpesifikasiMobil();
					f.setId(c.getInt(c.getColumnIndex(KEY_SPESIFIKASIMOBIL_ID)));
					f.setSpesifikasiMobil(c.getString(c.getColumnIndex(KEY_SPESIFIKASIMOBIL)));
					
					spesifikasimobil.add(f);
				}while(c.moveToNext());
			}
			return spesifikasimobil;
		}
		
		public int updateSpesifikasiMobil(SpesifikasiMobil spesifikasimobil){
			SQLiteDatabase db = this.getWritableDatabase();
			
			ContentValues values = new ContentValues();
			values.put(KEY_SPESIFIKASIMOBIL, spesifikasimobil.getSpesifikasiMobil());
			
			//updating row
			return db.update(TABLE_SPESIFIKASIMOBIL, values, KEY_SPESIFIKASIMOBIL_ID + " = ?", new String[] {String.valueOf(spesifikasimobil.getId())});
		}
		
		public void deleteSpesifikasiMobil(long id_spesifikasimobil){
			SQLiteDatabase db = this.getWritableDatabase();
			db.delete(TABLE_SPESIFIKASIMOBIL, KEY_SPESIFIKASIMOBIL_ID + " = ?", new String[] {String.valueOf(id_spesifikasimobil)});
		}
		
		public void deleteAllSpesifikasiMobil()
		{
		    SQLiteDatabase db = this.getWritableDatabase();
		   // db.delete(TABLE_NAME,null,null);
		    db.execSQL("delete from "+ TABLE_SPESIFIKASIMOBIL);
		    //db.execSQL("TRUNCATE table" + TABLE_NAME);
		    closeDB();
		}
		
		/* Jawaban method */
		public void insertHargaMobil(HargaMobil hm){
			SQLiteDatabase db = this.getWritableDatabase();
			
			ContentValues values = new ContentValues();
			values.put(KEY_HARGAMOBIL_ID, hm.getId());
			values.put(KEY_HARGAMOBIL, hm.getHargaMobil());
			
			//insert row
			try{
				db.insert(TABLE_HARGAMOBIL, null, values);
			}catch(Exception e){
				Log.e("DB ERROR", e.toString());
				e.printStackTrace();
			}
		}
		
		public Integer[] getAllIdHargaMobil(){
			String query = "SELECT * FROM " + TABLE_HARGAMOBIL;
			
			Log.e(LOG, query);
			
			SQLiteDatabase db = this.getReadableDatabase();
			Cursor c = db.rawQuery(query, null);
			
			if(c.getCount() >0)
	        {
	            Integer[] str = new Integer[c.getCount()];
	            int i = 0;
	 
	            while (c.moveToNext())
	            {
	                 str[i] = c.getInt(c.getColumnIndex(KEY_HARGAMOBIL_ID));
	                 i++;
	             }
	            return str;
	        }
	        else
	        {
	            return new Integer[] {};
	        }
		}
		
		public List<HargaMobil> getAllHargaMobil(){
			List<HargaMobil> hargamobil = new ArrayList<HargaMobil>();
			String query = "SELECT * FROM " + TABLE_HARGAMOBIL;
			
			Log.e(LOG, query);
			
			SQLiteDatabase db = this.getReadableDatabase();
			Cursor c = db.rawQuery(query, null);
			
			if(c.moveToFirst()){
				do{
					HargaMobil hm = new HargaMobil();
					hm.setId(c.getInt(c.getColumnIndex(KEY_HARGAMOBIL_ID)));
					hm.setHargaMobil(c.getString(c.getColumnIndex(KEY_HARGAMOBIL)));
					
					hargamobil.add(hm);
				}while(c.moveToNext());
			}
			return hargamobil;
		}
		
		public int updateHargaMobil(HargaMobil hm){
			SQLiteDatabase db = this.getWritableDatabase();
			
			ContentValues values = new ContentValues();
			values.put(KEY_HARGAMOBIL, hm.getHargaMobil());
			
			//updating row
			return db.update(TABLE_HARGAMOBIL, values, KEY_HARGAMOBIL_ID + " = ?", new String[] {String.valueOf(hm.getId())});
		}
		
		public void deleteHargaMobil(long id){
			SQLiteDatabase db = this.getWritableDatabase();
			db.delete(TABLE_HARGAMOBIL, KEY_HARGAMOBIL_ID + " = ?", new String[] {String.valueOf(id)});
		}
		
		public void deleteAllHargaMobil()
		{
		    SQLiteDatabase db = this.getWritableDatabase();
		   // db.delete(TABLE_NAME,null,null);
		    db.execSQL("delete from "+ TABLE_HARGAMOBIL);
		    //db.execSQL("TRUNCATE table" + TABLE_NAME);
		    closeDB();
		}
		
		/* Pertanyaan Method */
		
		public void insertDaftarHargaMobil(DaftarHargaMobil dhm){
			SQLiteDatabase db = this.getWritableDatabase();
			
			ContentValues values = new ContentValues();
			values.put(KEY_SPESIFIKASIMOBIL_ID, dhm.getDaftarHargaMobilId());
			values.put(KEY_HARGAMOBIL_ID, dhm.getHargaMobilId());
			
			//insert row
			try{
				db.insert(TABLE_DAFTARHARGAMOBIL, null, values);
			}catch(Exception e){
				Log.e("DB ERROR", e.toString());
				e.printStackTrace();
			}
		}
		
		public List<DaftarHargaMobil> getAllDaftarHargaMobil(){
			List<DaftarHargaMobil> list = new ArrayList<DaftarHargaMobil>();
			String query = "SELECT * FROM " + TABLE_SPESIFIKASIMOBIL + " a, "
					+ TABLE_HARGAMOBIL + " b, " + TABLE_DAFTARHARGAMOBIL + " c WHERE c."
					+ KEY_SPESIFIKASIMOBIL_ID + " = a." + KEY_SPESIFIKASIMOBIL_ID + " AND c."
					+ KEY_HARGAMOBIL_ID + " = b." + KEY_HARGAMOBIL_ID;
			Log.e(LOG, query);
			
			SQLiteDatabase db = this.getReadableDatabase();
			Cursor c = db.rawQuery(query, null);
			
			if(c.moveToFirst()){
				do{
					DaftarHargaMobil per = new DaftarHargaMobil();
					per.setId(c.getInt(c.getColumnIndex(KEY_DAFTARHARGAMOBIL_ID)));
					per.setDaftarHargaMobil(c.getString(c.getColumnIndex(KEY_SPESIFIKASIMOBIL)));
					per.setHargaMobil(c.getString(c.getColumnIndex(KEY_HARGAMOBIL)));
					
					list.add(per);
				} while(c.moveToNext());
			}
			
			return list;
			
		}
		
		// closing database
			public void closeDB() {
				SQLiteDatabase db = this.getReadableDatabase();
				if (db != null && db.isOpen())
					db.close();
			}

}
