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
import com.chekaz.device.Device;
import com.chekaz.util.Action;
import com.chekaz.util.ActionJSON;

public class actCreateAccount extends ActionBarActivity{
	
	private EditText txtUsername = null;
	private EditText txtPassword = null;
	private EditText txtConfirmPassword = null;
	private TextView txtError = null;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);			
		setContentView(R.layout.act_create_account);
		ActionBar action = getSupportActionBar();
		action.setBackgroundDrawable(getResources().getDrawable(R.drawable.bg_topbar));
		
		txtUsername = (EditText) findViewById(R.id.txtUsername);
		txtPassword = (EditText) findViewById(R.id.txtPassword);
		txtConfirmPassword = (EditText) findViewById(R.id.txtConfirmPassword);
		txtError = (TextView) findViewById(R.id.txtError);
		
		action.setIcon(R.drawable.ic_action_add_person);
		
	}
	
	public void login(View v){
		Intent i = new Intent(getApplicationContext(), actLogin.class);
		startActivity(i);
	}
	
	public void createAccount(View v){
		
		//Clear the error text view
		txtError.setText("");
		
		if(fieldsNotEmpty()){
			if(usernameOK()){
				if(usernameLengthOK()){
					if(passwordLengthOK()){
						if(passwordsMatch()){
							addUserNormal();
						}
						else
							txtError.setText("Passwords don't match");
					}
					else
						txtError.setText("Password must be 6 letters or more.");
				}
				else			
					txtError.setText("Username must be 4 letters or more.");
			}
			else
				txtError.setText("Username cannot contain spaces.");
		}
		else
			txtError.setText("Some fields are missing.");
		
	}
	
	private boolean fieldsNotEmpty(){		
		
		if(		txtUsername.getText().toString().trim().equals("")
			|| 	txtPassword.getText().toString().trim().equals("")
			|| 	txtConfirmPassword.getText().toString().trim().equals("")){
			
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
	
	private boolean usernameOK(){
		String user_name = txtUsername.getText().toString();
		
		for(int i = 0 ; i < user_name.length(); ++i){
			if(user_name.charAt(i) == ' ')
				return false;
		}
		
		return true;
	}
	
	private boolean passwordLengthOK(){
		if(txtPassword.getText().toString().length() < 6){
			return false;
		}
		else{
			return true;
		}
	}
	
	private boolean passwordsMatch(){
		if(txtPassword.getText().toString().equals(txtConfirmPassword.getText().toString())){
			return true;
		}
		else{
			return false;
		}
	}
	
	private void addUserNormal(){
		
		String user_name = txtUsername.getText().toString();
		String user_pass = txtPassword.getText().toString();
		String country = Device.getCountryZipCode(this);
		
		ConUser.addUserNormal(user_name, user_pass, country, this, new ActionJSON() {
			
			@Override
			public void action(JSONObject json) {
				try {
					String success = json.getString("success");
					String message = json.getString("message");
					
					if(success.equals(Connect.RES_SUCCESS)){
						JSONObject user = json.getJSONObject("message");									
						DataUpdate.addInfo(user, getApplicationContext());
						Toast.makeText(getApplicationContext(), "Setup successful", Toast.LENGTH_SHORT).show();
						
						Intent i = new Intent(getApplicationContext(), ActHome.class);
						i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
						startActivity(i);
						
					}
					else if(message.equals(ConUser.ERR_USERNAME_EXISTS)){
						txtError.setText("Username already exists.");
					}
					else{
						Toast.makeText(getApplicationContext(), "Unable to connect", Toast.LENGTH_SHORT).show();
						Log.d("err", message);
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
