package com.chekaz.activity.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.chekaz.activity.friends.ActSearch;
import com.chekaz.data.main.DbChekaz;
import com.chekaz.data.structure.StInfo;
import com.chekaz.view.RobotoTextView;
import com.keysindicet.chekaz.R;

public class ActHome extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_home);	
		
		ActionBar action = getSupportActionBar();
		action.setBackgroundDrawable(getResources().getDrawable(R.drawable.bg_topbar));
		
		
		//Check login status
		boolean infoStatusOK = Act.infoStatusOK(getApplicationContext());
		if(infoStatusOK){		
		
			RobotoTextView txtInfo = (RobotoTextView) findViewById(R.id.txtInfo);
			DbChekaz db = new DbChekaz(getApplicationContext());
			
			StInfo data = db.info.get();
			String strInfo =	"\nuser_no: " + data.USER_NO +
								"\nuser_name: " + data.USER_NAME +
								"\nfullname: " + data.FULLNAME +
								"\nIMEI: " + data.IMEI +
								"\ncountry: " + data.COUNTRY +
								"\ninvite_message: " + data.INVITE_TEXT +
								"\ndate_added: " + data.DATE_ADDED +
								"\ndate_updated: " + data.DATE_UPDATED;
			
			txtInfo.setText(strInfo);
			db.close();
		}	
		
	}
	
	@Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
		MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.act_home, menu);	    
	    return true;
    }
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		switch(item.getItemId()){
			case R.id.menu_home_findFriends:	
				startActivity(new Intent(getApplicationContext(), ActSearch.class));
				return true;
				
			case R.id.menu_home_logout:				
				Act.logout(this);
				return true;
		}
		return false;
	}

}
