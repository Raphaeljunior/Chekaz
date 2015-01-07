package com.chekaz.data.table;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.chekaz.data.main.DbChekaz;
import com.chekaz.data.main.Table;
import com.chekaz.data.structure.StInfo;

public class Info extends Table {
	
	// Columns
    private static final String USER_NO = "user_no",
    							IMEI = "imei",
    							COUNTRY = "country",
    							USER_NAME = "user_name",
    							FULLNAME = "fullname",
    							INVITE_TEXT = "invite_text",
    							DATE_ADDED = "date_added",
    							DATE_UPDATED = "date_updated";
    							
    
    
	public Info(String name, DbChekaz dbChekaz) {
		super(name, dbChekaz);
	}

	@Override
	public void create(SQLiteDatabase db) {
		String CREATE_TABLE = "CREATE TABLE " + TBL_NAME + "("				
                + USER_NO + " INTEGER PRIMARY KEY,"                 
				+ IMEI + " TEXT," 
				+ COUNTRY + " INTEGER NOT NULL," 
				+ USER_NAME + " TEXT,"
				+ FULLNAME + " TEXT,"
				+ INVITE_TEXT + " TEXT,"
				+ DATE_ADDED + " TEXT NOT NULL,"
				+ DATE_UPDATED + " TEXT NOT NULL)";
							
		
        db.execSQL(CREATE_TABLE);		
	}
	
	public void add(StInfo data) {
		
	    SQLiteDatabase db = dbChekaz.getWritableDatabase();
	 
	    ContentValues values = new ContentValues();
	    values.put(USER_NO, data.USER_NO); 
	    values.put(IMEI, data.IMEI);
	    values.put(COUNTRY, data.COUNTRY);
	    values.put(USER_NAME, data.USER_NAME);	
	    values.put(FULLNAME, data.FULLNAME);
	    values.put(INVITE_TEXT, data.INVITE_TEXT);
	    values.put(DATE_ADDED, data.DATE_ADDED);
	    values.put(DATE_UPDATED, data.DATE_UPDATED);
	 
	    // Inserting Row
	    db.insert(TBL_NAME, null, values);
	    db.close(); 
		
	}	
	
	public int update(StInfo data) {
		
	    SQLiteDatabase db = dbChekaz.getWritableDatabase();
	 
	    ContentValues values = new ContentValues();
	    values.put(USER_NO, data.USER_NO); 
	    values.put(IMEI, data.IMEI);
	    values.put(COUNTRY, data.COUNTRY);
	    values.put(USER_NAME, data.USER_NAME);	
	    values.put(FULLNAME, data.FULLNAME);
	    values.put(INVITE_TEXT, data.INVITE_TEXT);
	    values.put(DATE_ADDED, data.DATE_ADDED);
	    values.put(DATE_UPDATED, data.DATE_UPDATED);
	 
	    // updating row
	    return db.update(TBL_NAME, values, USER_NO + " = ?",
	            new String[] { String.valueOf(data.USER_NO) });
	    
	}
	
	public StInfo get() {
		
	    SQLiteDatabase db = dbChekaz.getReadableDatabase();
	 
	    String selectQuery = "SELECT  * FROM " + TBL_NAME;	 
	    Cursor cursor = db.rawQuery(selectQuery, null);
	    StInfo data = null;
	    
	    if(cursor.moveToFirst()){
	    	
	    	data = new StInfo(	cursor.getString(0), 
	    						cursor.getString(1), 
	    						cursor.getString(2), 
	    						cursor.getString(3), 
	    						cursor.getString(4),
	    						cursor.getString(5),
	    						cursor.getString(6),
	    						cursor.getString(7));	    	
	    }
	    
	    return data;
	    
	}		
	
	public void updateDate(String dateUpdated){	
		
		StInfo data = get();
		data.DATE_UPDATED = dateUpdated;
		
		update(data);		
	}
}
