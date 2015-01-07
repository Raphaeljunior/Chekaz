package com.chekaz.activity.main;


import org.json.JSONException;
import org.json.JSONObject;

import com.chekaz.connect.ConFriend;
import com.chekaz.connect.Connect;
import com.chekaz.data.main.DbChekaz;
import com.chekaz.data.table.Friend;
import com.chekaz.util.Action;
import com.chekaz.util.ActionJSON;
import com.keysindicet.chekaz.R;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

public class ActProfile extends ActionBarActivity {
	
	private String user_no;	
	private String stranger_no;
	private int status;
	
	public final static int PROFILE_USER = 1;
	public final static int PROFILE_STRANGER = 2;
	
	private FrameLayout llSend;
	private Button btnSend;
	private ProgressBar progress;
	private TextView txtFullname;
	private TextView txtUsername;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);		
		setContentView(R.layout.act_profile);
		
		ActionBar action = getSupportActionBar();
		action.setBackgroundDrawable(getResources().getDrawable(R.drawable.bg_topbar));
		
		llSend = (FrameLayout) findViewById(R.id.llSend);
		btnSend = (Button) findViewById(R.id.btnSend);
		progress = (ProgressBar) findViewById(R.id.loading_spinner);
		txtFullname = (TextView) findViewById(R.id.txtFullName);
		txtUsername = (TextView) findViewById(R.id.txtUsername);
		
		user_no = Act.getUserNo(getApplicationContext());		
		
		Bundle bundle = getIntent().getExtras();
        if(bundle != null){
        	int type = bundle.getInt("type");
        	
        	switch(type){
        		case PROFILE_STRANGER:
        			setupStranger(bundle);
        			break;        		
        	}
        }	
	}
	
	private void setupStranger(Bundle bundle){
		String fullname = bundle.getString("fullname");
		String user_name = bundle.getString("user_name");
		stranger_no = bundle.getString("stranger_no");	
		boolean loading = bundle.getBoolean("loading");
		
		txtFullname.setText(fullname);
		txtUsername.setText(user_name);
		
		DbChekaz db = new DbChekaz(getApplicationContext());
		status = db.friend.getStatus(user_no, stranger_no);
		
		switch(status){
			case Friend.STATUS_STRANGERS:
				if(!loading)
					llSend.setVisibility(View.VISIBLE);
				break;
		}
	}
	
	public void send(View v){
		btnSend.setVisibility(View.INVISIBLE);
		progress.setVisibility(View.VISIBLE);
		
		ConFriend.addFriend(user_no, stranger_no, new ActionJSON() {
			
			@Override
			public void action(JSONObject json) {
				try {
					String success = json.getString("success");
					
					if(success.equals(Connect.RES_SUCCESS)){
						JSONObject res_addfriend = json.getJSONObject("res_addfriend");							
						DbChekaz db = new DbChekaz(getApplicationContext());
						db.friend.add((res_addfriend));
						db.close();
						
						llSend.setVisibility(View.GONE);
					}					
					
				} catch (JSONException e) {
					progress.setVisibility(View.INVISIBLE);
					btnSend.setVisibility(View.VISIBLE);
				}
							
			}
		}, new Action() {
			
			@Override
			public void action() {
				progress.setVisibility(View.INVISIBLE);
				btnSend.setVisibility(View.VISIBLE);				
			}
		});
	}
}
