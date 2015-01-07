package com.chekaz.activity.startup;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.keysindicet.chekaz.R;
import com.chekaz.activity.main.ActHome;
import com.chekaz.connect.ConUser;
import com.chekaz.connect.Connect;
import com.chekaz.data.main.DataUpdate;
import com.chekaz.util.Action;
import com.chekaz.util.ActionJSON;

public class actLogin extends ActionBarActivity {
	
	private EditText txtUsername = null;
	private EditText txtPassword = null;	
	private TextView txtError = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);		
		setContentView(R.layout.act_login);
		ActionBar action = getSupportActionBar();
		action.setBackgroundDrawable(getResources().getDrawable(R.drawable.bg_topbar));
		
		txtUsername = (EditText) findViewById(R.id.txtUsername);
		txtPassword = (EditText) findViewById(R.id.txtPassword);		
		txtError = (TextView) findViewById(R.id.txtError);
		
		action.setIcon(R.drawable.ic_action_accounts);
	}
	
	public void createAccount(View v){
		Intent i = new Intent(getApplicationContext(), actCreateAccount.class);
		startActivity(i);
	}
	
	public void login(View v){
		
		//Clear the error text view
		txtError.setText("");
		
		if(fieldsNotEmpty()){
			if(usernameLengthOK()){
				if(passwordLengthOK()){					
					login();					
				}
				else
					txtError.setText("Password must be 6 letters or more.");
			}
			else
				txtError.setText("Username must be 4 letters or more.");
		}
		else
			txtError.setText("Some fields are missing.");
		
	}
	
	private boolean fieldsNotEmpty(){		
		
		if(		txtUsername.getText().toString().trim().equals("")
			|| 	txtPassword.getText().toString().trim().equals("")
			){
			
			return false;
		}
		else{
			return true;
		}		
	}
	
	private boolean usernameLengthOK(){
		if(txtUsername.getText().toString().length() < 4){
			return false;
		}
		else{
			return true;
		}
	}
	
	private boolean passwordLengthOK(){
		if(txtPassword.getText().toString().length() < 6){
			return false;
		}
		else{
			return true;
		}
	}	
	
	private void login(){
		
		String user_name = txtUsername.getText().toString();
		String user_pass = txtPassword.getText().toString();
		
		ConUser.login(user_name, user_pass, this, new ActionJSON() {
			
			@Override
			public void action(JSONObject json) {
				try {
					String success = json.getString("success");	
					
					if(success.equals(Connect.RES_SUCCESS)){
						JSONObject user = json.getJSONObject("message");									
						DataUpdate.addInfo(user, getApplicationContext());
						Toast.makeText(getApplicationContext(), "Setup successful", Toast.LENGTH_SHORT).show();
						
						Intent i = new Intent(getApplicationContext(), ActHome.class);
						i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
						startActivity(i);
					}
					else{
						txtError.setText("Wrong username/password combination.");
					}
					
				} catch (JSONException e) {
					Log.d("err", e.getMessage());
					Toast.makeText(getApplicationContext(), "Unable to connect", Toast.LENGTH_SHORT).show();
				}
			}
		}, new Action() {
			
			@Override
			public void action() {
				Toast.makeText(getApplicationContext(), "Unable to connect", Toast.LENGTH_SHORT).show();				
			}
		});
	}
	
	
}
