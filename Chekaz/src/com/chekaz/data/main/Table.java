/*
 * Parent class for table manipulation
 * contains common methods for table classes
 * 
 */


package com.chekaz.data.main;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public abstract class Table {
	// table name
    protected String TBL_NAME = null;
    protected DbChekaz dbChekaz = null;
    
    public Table(String name, DbChekaz dbChekaz){
    	TBL_NAME = name;
    	this.dbChekaz = dbChekaz;  	
    }
    
    abstract public void create(SQLiteDatabase db);
    
    //get total count
    public int getCount() {
        String countQuery = "SELECT  * FROM " + TBL_NAME;
        SQLiteDatabase db = dbChekaz.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();
 
        // return count
        return count;
    }
    
    //Delete table
    public void delete(SQLiteDatabase db){    	
        db.execSQL("DROP TABLE IF EXISTS " + TBL_NAME);
    } 
  
    
    
}
