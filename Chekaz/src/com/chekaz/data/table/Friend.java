package com.chekaz.data.table;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.chekaz.data.main.DbChekaz;
import com.chekaz.data.main.Table;
import com.chekaz.data.structure.StFriend;

public class Friend extends Table {
	
	// Columns
    private static final String FRIEND_NO = "friend_no",
    							USER_TO = "user_to",
    							USER_FROM = "user_from",
    							ACCEPTED = "accepted",
    							SEEN_ACCEPTED = "seen_accepted",
    							RECEIVED = "received",
    							DATE_SENT = "date_sent";  							
    
    //Friend status
    public static final int STATUS_FRIENDS = 1;
    public static final int STATUS_SENT_TO = 2;
    public static final int STATUS_SENT_FROM = 3;
    public static final int STATUS_STRANGERS = 4;
   
	public Friend(String name, DbChekaz dbChekaz) {
		super(name, dbChekaz);
	}

	@Override
	public void create(SQLiteDatabase db) {
		String CREATE_TABLE = "CREATE TABLE " + TBL_NAME + "("				
                + FRIEND_NO + " INTEGER PRIMARY KEY,"                 
				+ USER_TO + " INTEGER NOT NULL," 
				+ USER_FROM + " INTEGER NOT NULL," 
				+ ACCEPTED + " INTEGER NOT NULL,"
				+ SEEN_ACCEPTED + " INTEGER NOT NULL,"
				+ RECEIVED + " INTEGER NOT NULL,"
				+ DATE_SENT + " TEXT NOT NULL)";
				
							
		
        db.execSQL(CREATE_TABLE);		
	}
	
	public void add(StFriend data) {
		
	    SQLiteDatabase db = dbChekaz.getWritableDatabase();
	 
	    ContentValues values = new ContentValues();
	    values.put(FRIEND_NO, data.FRIEND_NO); 
	    values.put(USER_TO, data.USER_TO);
	    values.put(USER_FROM, data.USER_FROM);
	    values.put(ACCEPTED, data.ACCEPTED);	
	    values.put(SEEN_ACCEPTED, data.SEEN_ACCEPTED);
	    values.put(RECEIVED, data.RECEIVED);
	    values.put(DATE_SENT, data.DATE_SENT);	    
	 
	    // Inserting Row
	    db.insert(TBL_NAME, null, values);
	    db.close(); 
		
	}	
	
	public int update(StFriend data) {
		
	    SQLiteDatabase db = dbChekaz.getWritableDatabase();
	 
	    ContentValues values = new ContentValues();
	    values.put(FRIEND_NO, data.FRIEND_NO); 
	    values.put(USER_TO, data.USER_TO);
	    values.put(USER_FROM, data.USER_FROM);
	    values.put(ACCEPTED, data.ACCEPTED);	
	    values.put(SEEN_ACCEPTED, data.SEEN_ACCEPTED);
	    values.put(RECEIVED, data.RECEIVED);
	    values.put(DATE_SENT, data.DATE_SENT);
	 
	    // updating row
	    return db.update(TBL_NAME, values, FRIEND_NO + " = ?",
	            new String[] { String.valueOf(data.FRIEND_NO) });
	    
	}
	
	public StFriend get(String friend_no) {
		
		SQLiteDatabase db = dbChekaz.getWritableDatabase();
		Cursor cursor = db.rawQuery("SELECT * FROM " + TBL_NAME + " WHERE friend_no = ?", new String[] {friend_no});
	    
	    StFriend data = null;
	    
	    if (cursor.getCount()>0){
	        cursor.moveToFirst();
	        data = new StFriend(cursor.getString(0), 
	        					cursor.getString(1), 
	        					cursor.getString(2), 
	        					cursor.getString(3), 
	        					cursor.getString(4),
	        					cursor.getString(5),
	        					cursor.getString(6));
	    }
	    
	    return data;
	    
	}	
	
	
	public List<StFriend> getAll(){
		List<StFriend> list = new ArrayList<StFriend>();
	    // Select All Query
	    String selectQuery = "SELECT  * FROM " + TBL_NAME;
	 
	    SQLiteDatabase db = dbChekaz.getWritableDatabase();
	    Cursor cursor = db.rawQuery(selectQuery, null);
	 
	    // looping through all rows and adding to list
	    if (cursor.moveToFirst()) {
	        do {
	            StFriend data = new StFriend(cursor.getString(0), 
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
	
	public void delete(int friend_no) {
	    SQLiteDatabase db = dbChekaz.getWritableDatabase();
	    db.delete(TBL_NAME, FRIEND_NO + " = ?",
	            new String[] { String.valueOf(friend_no) });
	    db.close();
	}
	
	public int getStatus(String user_no, String user_no2){
		
		int status = STATUS_STRANGERS;
		List<StFriend> friends = getAll();
		
		for(int i = 0; i < friends.size() ; ++i){			
			StFriend data = friends.get(i);
			
			if( (data.USER_FROM.equals(user_no) && data.USER_TO.equals(user_no2))){
				if(data.ACCEPTED.equals("1")){
					status = STATUS_FRIENDS;
				}
				else{
					status = STATUS_SENT_TO;
				}
				
				break;
			}
			if( (data.USER_FROM.equals(user_no2) && data.USER_TO.equals(user_no))){
				if(data.ACCEPTED.equals("1")){
					status = STATUS_FRIENDS;
				}
				else{
					status = STATUS_SENT_FROM;
				}
				
				break;
			}
		}
		
		return status;
	}
	
	public void add(JSONObject record){
		
		try {			
		
				StFriend data = new StFriend(null, null, null, null, null, null, null);
				
				data.FRIEND_NO = record.getString("friend_no").toString();
				data.USER_TO = record.getString("user_to").toString();
				data.USER_FROM = record.getString("user_from").toString();
				data.ACCEPTED = record.getString("accepted").toString();
				data.SEEN_ACCEPTED = record.getString("seen_accepted").toString();
				data.RECEIVED = record.getString("received").toString();
				data.DATE_SENT = record.getString("date_sent").toString();
				
				if(!exists(data.FRIEND_NO)){
					dbChekaz.friend.add(data);
				}
			
									
		} 
		catch (JSONException e) {
			
		}
	}
	
	public boolean exists(String friend_no){		
		SQLiteDatabase db = dbChekaz.getWritableDatabase();
		Cursor cursor = db.rawQuery("SELECT * FROM " + TBL_NAME + " WHERE friend_no = ?", new String[] {friend_no});
		
		if(cursor.moveToFirst()){
			return true;
		}
		
		return false;
	}
	
}
