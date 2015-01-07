package com.chekaz.activity.main;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.DisplayMetrics;

import com.chekaz.activity.startup.ActWelcome;
import com.chekaz.data.main.DbChekaz;
import com.chekaz.data.structure.StInfo;
import com.chekaz.game.ui.Area;
import com.chekaz.util.Action;
import com.chekaz.util.ActionSync;

public class Act {
	public static Area getDimensions(Activity act){
	
		DisplayMetrics metrics = new DisplayMetrics();
		act.getWindowManager().getDefaultDisplay().getMetrics(metrics);
		
		int height = metrics.heightPixels;
		int width = metrics.widthPixels;		
		
		return new Area(0, 0, width, height);
	}
	
	public static void logout(final Context ctx){
		ActionSync actionSync = new ActionSync(ctx, "Logging out...", new Action() {
			
			@Override
			public void action() {
				DbChekaz db = new DbChekaz(ctx);
				db.reset(db.getWritableDatabase());
				db.close();				
			}
		}, new Action() {
			
			@Override
			public void action() {
				Intent i = new Intent(ctx, ActWelcome.class);
				i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
				ctx.startActivity(i);
			}
		}); 
		
		actionSync.execute();
	}
	
	public static boolean infoStatusOK(Context ctx){
		
		//Check if the user has not yet logged in
		DbChekaz db = new DbChekaz(ctx);
		StInfo data = db.info.get();
		
		if(data == null){
			Intent i = new Intent(ctx, ActWelcome.class);
			i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
			ctx.startActivity(i);
			
			return false;
		}		
		
		db.close();
		
		return true;
	}	
	
	public static String getUserNo(Context ctx){
		DbChekaz db = new DbChekaz(ctx);
		String user_no = db.info.get().USER_NO;
		db.close();
		
		return user_no;
	}
}
