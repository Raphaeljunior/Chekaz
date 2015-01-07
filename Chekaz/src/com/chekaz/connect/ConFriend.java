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

public class ConFriend {
	
	public final static String ERR_USER_NO_MISSING = "-1";
	
	public static void find(final String user_no, final String keyword, final String lower, final String limit, final ActionJSON success, final Action err){		
			
			String tag_request = "find";
			String url = Connect.URL_FRIEND;
			
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
				        }
				    }, 
				    new Response.ErrorListener() 
				    {
				         @Override
				         public void onErrorResponse(VolleyError error) {				        	 				        	 
				             err.action();
				         }
				    }
				) {     
				    @Override
				    protected Map<String, String> getParams() 
				    {  
				            Map<String, String>  params = new HashMap<String, String>();  
				            
				            params.put("Type", "find");
				            params.put("User_no", user_no); 				            		            
				            params.put("Keyword", keyword);
				            params.put("Lower", lower); 				            		            
				            params.put("Limit", limit);
				            
				            return params;  
				    }
				};
	        
	        // Adding request to request queue
	    	AppController.getInstance().addToRequestQueue(postRequest, tag_request);
		
		
	}
	
	public static void addFriend(final String user_no, final String user_to, final ActionJSON success, final Action err){		
		
		String tag_request = "addfriend";
		String url = Connect.URL_FRIEND;
		
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
			        }
			    }, 
			    new Response.ErrorListener() 
			    {
			         @Override
			         public void onErrorResponse(VolleyError error) {				        	 				        	 
			             err.action();
			         }
			    }
			) {     
			    @Override
			    protected Map<String, String> getParams() 
			    {  
			            Map<String, String>  params = new HashMap<String, String>();  
			            
			            params.put("Type", "addfriend");
			            params.put("User_to", user_to); 				            		            
			            params.put("User_from", user_no);			            
			            
			            return params;  
			    }
			};
        
        // Adding request to request queue
    	AppController.getInstance().addToRequestQueue(postRequest, tag_request);
	
	
}
}
