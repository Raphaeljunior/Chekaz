package com.chekaz.data.table;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.chekaz.data.main.DbChekaz;
import com.chekaz.data.main.Table;
import com.chekaz.data.structure.StUser;

public class User extends Table {
	
	// Columns
    private static final String USER_NO = "user_no",
    							COUNTRY = "country",
    							USER_NAME = "user_name",
    							FULLNAME = "fullname",
    							DATE_ADDED = "date_added";							
    							  							
    
    
	public User(String name, DbChekaz dbChekaz) {
		super(name, dbChekaz);
	}

	@Override
	public void create(SQLiteDatabase db) {
		String CREATE_TABLE = "CREATE TABLE " + TBL_NAME + "("				
                + USER_NO + " INTEGER PRIMARY KEY,"                 
				+ COUNTRY + " INTEGER NOT NULL," 
				+ USER_NAME + " TEXT NOT NULL," 
				+ FULLNAME + " TEXT NOT NULL,"				
				+ DATE_ADDED + " TEXT NOT NULL)";
				
							
		
        db.execSQL(CREATE_TABLE);		
	}
	
	public void add(StUser data) {
		
	    SQLiteDatabase db = dbChekaz.getWritableDatabase();
	 
	    ContentValues values = new ContentValues();
	    values.put(USER_NO, data.USER_NO); 
	    values.put(COUNTRY, data.COUNTRY);
	    values.put(USER_NAME, data.USER_NAME);
	    values.put(FULLNAME, data.FULLNAME);	    
	    values.put(DATE_ADDED, data.DATE_ADDED);	       
	 
	    // Inserting Row
	    db.insert(TBL_NAME, null, values);
	    db.close(); 
		
	}	
	
	public int update(StUser data) {
		
	    SQLiteDatabase db = dbChekaz.getWritableDatabase();
	 
	    ContentValues values = new ContentValues();
	    values.put(USER_NO, data.USER_NO); 
	    values.put(COUNTRY, data.COUNTRY);
	    values.put(USER_NAME, data.USER_NAME);
	    values.put(FULLNAME, data.FULLNAME);	    
	    values.put(DATE_ADDED, data.DATE_ADDED);
	 
	    // updating row
	    return db.update(TBL_NAME, values, USER_NO + " = ?",
	            new String[] { String.valueOf(data.USER_NO) });
	    
	}
	
	public StUser get(String user_no) {
		
		SQLiteDatabase db = dbChekaz.getWritableDatabase();
		Cursor cursor = db.rawQuery("SELECT * FROM " + TBL_NAME + " WHERE user_no = ?", new String[] {user_no});
	    
	    StUser data = null;
	    
	    if (cursor.getCount()>0){
	        cursor.moveToFirst();
	        data = new StUser(cursor.getString(0), 
	        					cursor.getString(1), 
	        					cursor.getString(2), 
	        					cursor.getString(3), 	        						        					
	        					cursor.getString(4));
	    }
	    
	    return data;
	    
	}	
	
	
	public List<StUser> getAll(){
		List<StUser> list = new ArrayList<StUser>();
	    // Select All Query
	    String selectQuery = "SELECT  * FROM " + TBL_NAME;
	 
	    SQLiteDatabase db = dbChekaz.getWritableDatabase();
	    Cursor cursor = db.rawQuery(selectQuery, null);
	 
	    // looping through all rows and adding to list
	    if (cursor.moveToFirst()) {
	        do {
	            StUser data = new StUser(cursor.getString(0), 
					    					cursor.getString(1), 
					    					cursor.getString(2), 
					    					cursor.getString(3), 					    					
					    					cursor.getString(4));
					    					
	            
	            list.add(data);
	        } while (cursor.moveToNext());
	    }	    
	   
	    return list;		
	}
	
	public void delete(int user_no) {
	    SQLiteDatabase db = dbChekaz.getWritableDatabase();
	    db.delete(TBL_NAME, USER_NO + " = ?",
	            new String[] { String.valueOf(user_no) });
	    db.close();
	}
}
