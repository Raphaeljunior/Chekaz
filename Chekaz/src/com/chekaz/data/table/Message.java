package com.chekaz.data.table;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.chekaz.data.main.DbChekaz;
import com.chekaz.data.main.Table;
import com.chekaz.data.structure.StMessage;

public class Message extends Table {
	
	// Columns
    private static final String MSG_NO = "msg_no",
    							USER_TO = "user_to",
    							USER_FROM = "user_from",
    							MSG = "msg",
    							RECEIVED = "received",    							
    							DATE_SENT = "date_sent";  							
    
    
	public Message(String name, DbChekaz dbChekaz) {
		super(name, dbChekaz);
	}

	@Override
	public void create(SQLiteDatabase db) {
		String CREATE_TABLE = "CREATE TABLE " + TBL_NAME + "("				
                + MSG_NO + " INTEGER PRIMARY KEY,"                 
				+ USER_TO + " INTEGER NOT NULL," 
				+ USER_FROM + " INTEGER NOT NULL," 
				+ MSG + " TEXT NOT NULL,"				
				+ RECEIVED + " INTEGER NOT NULL,"
				+ DATE_SENT + " TEXT NOT NULL)";
				
							
		
        db.execSQL(CREATE_TABLE);		
	}
	
	public void add(StMessage data) {
		
	    SQLiteDatabase db = dbChekaz.getWritableDatabase();
	 
	    ContentValues values = new ContentValues();
	    values.put(MSG_NO, data.MSG_NO); 
	    values.put(USER_TO, data.USER_TO);
	    values.put(USER_FROM, data.USER_FROM);
	    values.put(MSG, data.MSG);	    
	    values.put(RECEIVED, data.RECEIVED);
	    values.put(DATE_SENT, data.DATE_SENT);	    
	 
	    // Inserting Row
	    db.insert(TBL_NAME, null, values);
	    db.close(); 
		
	}	
	
	public int update(StMessage data) {
		
	    SQLiteDatabase db = dbChekaz.getWritableDatabase();
	 
	    ContentValues values = new ContentValues();
	    values.put(MSG_NO, data.MSG_NO); 
	    values.put(USER_TO, data.USER_TO);
	    values.put(USER_FROM, data.USER_FROM);
	    values.put(MSG, data.MSG);	    
	    values.put(RECEIVED, data.RECEIVED);
	    values.put(DATE_SENT, data.DATE_SENT);
	 
	    // updating row
	    return db.update(TBL_NAME, values, MSG_NO + " = ?",
	            new String[] { String.valueOf(data.MSG_NO) });
	    
	}
	
	public StMessage get(String msg_no) {
		
		SQLiteDatabase db = dbChekaz.getWritableDatabase();
		Cursor cursor = db.rawQuery("SELECT * FROM " + TBL_NAME + " WHERE msg_no = ?", new String[] {msg_no});
	    
	    StMessage data = null;
	    
	    if (cursor.getCount()>0){
	        cursor.moveToFirst();
	        data = new StMessage(cursor.getString(0), 
	        					cursor.getString(1), 
	        					cursor.getString(2), 
	        					cursor.getString(3), 
	        					cursor.getString(4),	        					
	        					cursor.getString(5));
	    }
	    
	    return data;
	    
	}	
	
	
	public List<StMessage> getAll(){
		List<StMessage> list = new ArrayList<StMessage>();
	    // Select All Query
	    String selectQuery = "SELECT  * FROM " + TBL_NAME;
	 
	    SQLiteDatabase db = dbChekaz.getWritableDatabase();
	    Cursor cursor = db.rawQuery(selectQuery, null);
	 
	    // looping through all rows and adding to list
	    if (cursor.moveToFirst()) {
	        do {
	            StMessage data = new StMessage(cursor.getString(0), 
					    					cursor.getString(1), 
					    					cursor.getString(2), 
					    					cursor.getString(3), 
					    					cursor.getString(4),
					    					cursor.getString(5));
					    					
	            
	            list.add(data);
	        } while (cursor.moveToNext());
	    }	    
	   
	    return list;		
	}
	
	public void delete(int msg_no) {
	    SQLiteDatabase db = dbChekaz.getWritableDatabase();
	    db.delete(TBL_NAME, MSG_NO + " = ?",
	            new String[] { String.valueOf(msg_no) });
	    db.close();
	}
}
