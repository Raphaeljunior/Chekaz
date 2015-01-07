package com.chekaz.fragment;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.chekaz.activity.main.ActHome;
import com.chekaz.connect.ConUser;
import com.chekaz.connect.Connect;
import com.chekaz.data.main.DataUpdate;
import com.chekaz.device.Device;
import com.chekaz.util.Action;
import com.chekaz.util.ActionJSON;
import com.keysindicet.chekaz.R;


public class StartupDialog extends DialogFragment {
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		
		  	AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		    // Get the layout inflater
		    LayoutInflater inflater = getActivity().getLayoutInflater();
		    
		    View layout = inflater.inflate(R.layout.act_startup_dialog, null);			    
		    AlertDialog dialog  = builder.create();
		    dialog.setView(layout, 0, 0, 0, 0);
		    
		    Button btnContinue = (Button) layout.findViewById(R.id.btnContinue);
		    btnContinue.setOnClickListener(new OnClickListener() {				
				@Override
				public void onClick(View arg0) {
					String strIMEI = Device.getIMEI(getActivity());
					String strCountryZipCode = Device.getCountryZipCode(getActivity());
					
					//Add a user using the IMEI
					ConUser.addUserIMEI(strIMEI, strCountryZipCode, getActivity(), new ActionJSON() {						
						@Override
						public void action(JSONObject json) {
							try {
								String success = json.getString("success");
								if(success.equals(Connect.RES_SUCCESS)){
									JSONObject user = json.getJSONObject("message");									
									DataUpdate.addInfo(user, getActivity());									
									Toast.makeText(getActivity(), "Setup complete", Toast.LENGTH_SHORT).show();
									
									Intent i = new Intent(getActivity(), ActHome.class);
									i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
									startActivity(i);
								}
								else{
									Toast.makeText(getActivity(), "Unable to connect.", Toast.LENGTH_SHORT).show();
								}
							} catch (JSONException e) {
								Toast.makeText(getActivity(), "Unable to connect.", Toast.LENGTH_SHORT).show();
							}							
						}
					}, new Action() {
						
						@Override
						public void action() {
							Toast.makeText(getActivity(), "Unable to connect.", Toast.LENGTH_SHORT).show();						
						}
					});					
				}
			});
		    
		    return dialog;
	}
}
