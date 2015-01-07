package com.chekaz.data.table;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.chekaz.data.main.DbChekaz;
import com.chekaz.data.main.Table;
import com.chekaz.data.structure.StGame;

public class Game extends Table {
	
	// Columns
    private static final String GAME_NO = "game_no",
    							USER_TO = "user_to",
    							USER_FROM = "user_from",
    							RECEIVED = "received",
    							DATE_STARTED = "date_started",
    							DATE_UPDATED = "date_updated",
    							DATE_ENDED = "date_ended";  							
    
    
	public Game(String name, DbChekaz dbChekaz) {
		super(name, dbChekaz);
	}

	@Override
	public void create(SQLiteDatabase db) {
		String CREATE_TABLE = "CREATE TABLE " + TBL_NAME + "("				
                + GAME_NO + " INTEGER PRIMARY KEY,"                 
				+ USER_TO + " INTEGER NOT NULL," 
				+ USER_FROM + " INTEGER NOT NULL," 
				+ RECEIVED + " INTEGER NOT NULL,"
				+ DATE_STARTED + " TEXT NOT NULL,"
				+ DATE_UPDATED + " TEXT NOT NULL,"
				+ DATE_ENDED + " TEXT NOT NULL)";
				
							
		
        db.execSQL(CREATE_TABLE);		
	}
	
	public void add(StGame data) {
		
	    SQLiteDatabase db = dbChekaz.getWritableDatabase();
	 
	    ContentValues values = new ContentValues();
	    values.put(GAME_NO, data.GAME_NO); 
	    values.put(USER_TO, data.USER_TO);
	    values.put(USER_FROM, data.USER_FROM);
	    values.put(RECEIVED, data.RECEIVED);	
	    values.put(DATE_STARTED, data.DATE_STARTED);
	    values.put(DATE_UPDATED, data.DATE_UPDATED);
	    values.put(DATE_ENDED, data.DATE_ENDED);	    
	 
	    // Inserting Row
	    db.insert(TBL_NAME, null, values);
	    db.close(); 
		
	}	
	
	public int update(StGame data) {
		
	    SQLiteDatabase db = dbChekaz.getWritableDatabase();
	 
	    ContentValues values = new ContentValues();
	    values.put(GAME_NO, data.GAME_NO); 
	    values.put(USER_TO, data.USER_TO);
	    values.put(USER_FROM, data.USER_FROM);
	    values.put(RECEIVED, data.RECEIVED);	
	    values.put(DATE_STARTED, data.DATE_STARTED);
	    values.put(DATE_UPDATED, data.DATE_UPDATED);
	    values.put(DATE_ENDED, data.DATE_ENDED);
	 
	    // updating row
	    return db.update(TBL_NAME, values, GAME_NO + " = ?",
	            new String[] { String.valueOf(data.GAME_NO) });
	    
	}
	
	public StGame get(String game_no) {
		
		SQLiteDatabase db = dbChekaz.getWritableDatabase();
		Cursor cursor = db.rawQuery("SELECT * FROM " + TBL_NAME + " WHERE game_no = ?", new String[] {game_no});
	    
	    StGame data = null;
	    
	    if (cursor.getCount()>0){
	        cursor.moveToFirst();
	        data = new StGame(	cursor.getString(0), 
	        					cursor.getString(1), 
	        					cursor.getString(2), 
	        					cursor.getString(3), 
	        					cursor.getString(4),
	        					cursor.getString(5),
	        					cursor.getString(6));
	    }
	    
	    return data;
	    
	}	
	
	
	public List<StGame> getAll(){
		List<StGame> list = new ArrayList<StGame>();
	    // Select All Query
	    String selectQuery = "SELECT  * FROM " + TBL_NAME;
	 
	    SQLiteDatabase db = dbChekaz.getWritableDatabase();
	    Cursor cursor = db.rawQuery(selectQuery, null);
	 
	    // looping through all rows and adding to list
	    if (cursor.moveToFirst()) {
	        do {
	            StGame data = new StGame(cursor.getString(0), 
					    					cursor.getString(1), 
					    					cursor.getString(2), 
					    					cursor.getString(3), 
					    					cursor.getString(4),
					    					cursor.getString(5),
					    					cursor.getString(6));
	            
	            list.add(data);
	        } while (cursor.moveToNext());
	    }	    
	   
	    return list;		
	}
	
	public void delete(int game_no) {
	    SQLiteDatabase db = dbChekaz.getWritableDatabase();
	    db.delete(TBL_NAME, GAME_NO + " = ?",
	            new String[] { String.valueOf(GAME_NO) });
	    db.close();
	}
}
