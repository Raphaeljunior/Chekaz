package com.chekaz.activity.startup;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.View;

import com.keysindicet.chekaz.R;
import com.chekaz.fragment.StartupDialog;

public class ActStartup extends ActionBarActivity{		
			
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);		
		setContentView(R.layout.act_startup);	
		ActionBar action = getSupportActionBar();
		action.setBackgroundDrawable(getResources().getDrawable(R.drawable.bg_topbar));		
	}
	
	public void login(View v){
		Intent i = new Intent(getApplicationContext(), actLogin.class);
		startActivity(i);
	}
	
	public void showTry(View v){
		StartupDialog dialog = new StartupDialog();
		dialog.show(getSupportFragmentManager(), "TryNotice");
	}
	
	public void showCreate(View v){
		startActivity(new Intent(getApplicationContext(),actCreateAccount.class));
	}
}

