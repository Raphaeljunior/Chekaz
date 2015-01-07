package com.chekaz.data.main;

import com.chekaz.data.table.Friend;
import com.chekaz.data.table.Game;
import com.chekaz.data.table.Info;
import com.chekaz.data.table.Message;
import com.chekaz.data.table.User;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbChekaz extends SQLiteOpenHelper {
	
	// All Static variables
    // Database Version
    private static final int DB_VERSION = 3;
  
    // Database Name
    private static final String DB_NAME = "dbChekaz";
    
    //Tables
    public Info info = null;
    public Friend friend = null;
    public Game game = null;
    public Message message = null;
    public User user = null; 
    
	public DbChekaz(Context context) {
		super(context, DB_NAME, null,DB_VERSION);
		
		info = new Info("info", this);	
		friend = new Friend("friend", this);
		game = new Game("game", this);
		message = new Message("message", this);
		user = new User("user", this);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {	
		info.create(db);
		friend.create(db);
		game.create(db);
		message.create(db);
		user.create(db);
	}
	
	public void delete(SQLiteDatabase db){		
		info.delete(db);
		friend.delete(db);
		game.delete(db);
		message.delete(db);
		user.delete(db);
	}
	
	public void reset(SQLiteDatabase db){
		delete(db);
		onCreate(db);
	}
	
	//In case of any structure change in the database 
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		reset(db);
	}
}
