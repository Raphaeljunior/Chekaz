package com.chekaz.util;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

public class ActionSync extends AsyncTask<String, String, String> {	
	
	private Context ctx = null;	
	private String msg = null;
	private Action action = null;
	private Action response = null;
	private ProgressDialog dialog = null;
	
	public  ActionSync(Context ctx, String msg, Action action, Action response){
		this.ctx = ctx;
		this.action =action;
		this.response = response;
		this.msg = msg;
		
		dialog = new ProgressDialog(ctx);
		dialog.setCancelable(false);
		dialog.setMessage(msg);
	}
	
	
	@Override
	protected void onPreExecute() {		
		super.onPreExecute();      
        dialog.show();
	}
		
	@Override
	protected String doInBackground(String... params) {		
		action.action();		
		return null;
	}	
	
	@Override
	protected void onPostExecute(String result) {		
		super.onPostExecute(result);	
		dialog.dismiss();
		response.action();
	}
}	
