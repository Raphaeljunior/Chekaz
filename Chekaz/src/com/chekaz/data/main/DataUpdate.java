package com.chekaz.data.main;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;

import com.chekaz.data.structure.StInfo;

public class DataUpdate {
	public final static void addInfo(JSONObject json, Context ctx){			
		try {			
			
			StInfo data = new StInfo(null, null, null, null, null, null, null, null);
			data.USER_NO = json.getString("user_no");
			data.IMEI = json.getString("IMEI");
			data.COUNTRY = json.getString("country");			
			data.USER_NAME = json.getString("user_name");
			data.FULLNAME = json.getString("fullname");
			data.INVITE_TEXT = json.getString("invite_text");
			data.DATE_ADDED = json.getString("date_added");
			data.DATE_UPDATED = json.getString("date_updated");
			
			DbChekaz db = new DbChekaz(ctx);
			
			//Reset the database first
			db.reset(db.getWritableDatabase());			
			db.info.add(data);
			db.close();
						
		} catch (JSONException e) {
			
		}
	}
	
}
