package com.chekaz.connect;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.Request.Method;
import com.android.volley.toolbox.StringRequest;
import com.chekaz.util.Action;
import com.chekaz.util.ActionJSON;
import com.chekaz.util.volley.AppController;

public class ConUser {
	
	public final static String ERR_USERNAME_EXISTS = "-1";
	
	public static void addUserIMEI(final String IMEI, final String country, Context ctx, final ActionJSON success, final Action err){
		
			final ProgressDialog dialog = new ProgressDialog(ctx);
			dialog.setMessage("Setting up... ");
			dialog.setCancelable(false);
			dialog.show();
			
			String tag_request = "addUserIMEI";
			String url = Connect.URL_USER;
			
			StringRequest postRequest = new StringRequest(Method.POST, url, 
				    new Response.Listener<String>() 
				    {
				        @Override
				        public void onResponse(String response) {				        	
				        	
				            // response
				        	try{
				        		success.action(new JSONObject(response.toString()));				        		
				        	}
				        	catch(Exception e){	
				        		Log.d("msg", response);
				        		err.action();
				        	}
				        	
				        	dialog.dismiss();
				        }
				    }, 
				    new Response.ErrorListener() 
				    {
				         @Override
				         public void onErrorResponse(VolleyError error) {
				        	 
				        	 dialog.dismiss();
				        	 
				             err.action();
				         }
				    }
				) {     
				    @Override
				    protected Map<String, String> getParams() 
				    {  
				            Map<String, String>  params = new HashMap<String, String>();  
				            
				            params.put("IMEI", IMEI);
				            params.put("Type", "addUserIMEI"); 				            		            
				            params.put("Country", country);
				            
				            return params;  
				    }
				};
	        
	        // Adding request to request queue
	    	AppController.getInstance().addToRequestQueue(postRequest, tag_request);
		
		
	}
	
	public static void addUserNormal(final String user_name, final String user_pass, final String country, Context ctx, final ActionJSON success, final Action err){
		
			final ProgressDialog dialog = new ProgressDialog(ctx);
			dialog.setMessage("Creating account... ");
			dialog.setCancelable(false);
			dialog.show();
			
			String tag_request = "addUserNormal";
			String url = Connect.URL_USER;
			
			StringRequest postRequest = new StringRequest(Method.POST, url, 
				    new Response.Listener<String>() 
				    {
				        @Override
				        public void onResponse(String response) {				        	
				        	
				            // response
				        	try{
				        		success.action(new JSONObject(response.toString()));				        		
				        	}
				        	catch(Exception e){	
				        		Log.d("msg", response);
				        		err.action();
				        	}
				        	
				        	dialog.dismiss();
				        }
				    }, 
				    new Response.ErrorListener() 
				    {
				         @Override
				         public void onErrorResponse(VolleyError error) {
				        	 
				        	 dialog.dismiss();
				        	 
				             err.action();
				         }
				    }
				) {     
				    @Override
				    protected Map<String, String> getParams() 
				    {  
				            Map<String, String>  params = new HashMap<String, String>();  		            
				             
				            params.put("User_name", user_name);				            				            
				            params.put("Type", "addUserNormal");
				            params.put("User_pass", user_pass);
				            params.put("Country", country);
				            
				            return params;  
				    }
				};
	        
	        // Adding request to request queue
	    	AppController.getInstance().addToRequestQueue(postRequest, tag_request);
		
		
	}
	
	public static void login(final String user_name, final String user_pass, Context ctx, final ActionJSON success, final Action err){
		
			final ProgressDialog dialog = new ProgressDialog(ctx);
			dialog.setMessage("Logging in... ");
			dialog.setCancelable(false);
			dialog.show();
			
			String tag_request = "login";
			String url = Connect.URL_USER;
			
			StringRequest postRequest = new StringRequest(Method.POST, url, 
				    new Response.Listener<String>() 
				    {
				        @Override
				        public void onResponse(String response) {				        	
				        	
				            // response
				        	try{
				        		success.action(new JSONObject(response.toString()));				        		
				        	}
				        	catch(Exception e){	
				        		Log.d("msg", response);
				        		err.action();
				        	}
				        	
				        	dialog.dismiss();
				        }
				    }, 
				    new Response.ErrorListener() 
				    {
				         @Override
				         public void onErrorResponse(VolleyError error) {
				        	 
				        	 dialog.dismiss();
				        	 
				             err.action();
				         }
				    }
				) {     
				    @Override
				    protected Map<String, String> getParams() 
				    {  
				            Map<String, String>  params = new HashMap<String, String>();  		            
				           
				            params.put("Type", "login");
				            params.put("User_name", user_name);				           
				            params.put("User_pass", user_pass);				            
				            
				            return params;  
				    }
				};
	        
	        // Adding request to request queue
	    	AppController.getInstance().addToRequestQueue(postRequest, tag_request);
		
	}
}
