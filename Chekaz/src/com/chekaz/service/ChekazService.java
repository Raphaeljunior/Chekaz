package com.chekaz.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class ChekazService extends Service {
	
	private int mStartMode = Service.START_STICKY;
	
	@Override
	public void onCreate() {		
		
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		
		return mStartMode;
	}

	@Override
	public IBinder onBind(Intent arg0) {
		
		return null;
	}

}
