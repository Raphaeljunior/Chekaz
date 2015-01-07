package com.chekaz.activity.startup;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.View;

import com.keysindicet.chekaz.R;

public class ActWelcome extends ActionBarActivity{		
			
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);		
		setContentView(R.layout.act_welcome);	
		
		ActionBar action = getSupportActionBar();
		action.setBackgroundDrawable(getResources().getDrawable(R.drawable.bg_topbar));
		
	}
	
	public void start(View v){
		startActivity(new Intent(getApplicationContext(), ActStartup.class));
	}
}

