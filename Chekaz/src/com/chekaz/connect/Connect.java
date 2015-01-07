

package com.chekaz.connect;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class Connect {
	
	private final static String SERVER_LOCAL = "10.0.2.2";
	private final static String SERVER_ONLINE = "www.keysindicet.com.";
	
	public final static String URL_SERVER = "http://" + SERVER_LOCAL  + "/chekaz/app/android/";
	
	//Response constants
	public final static String RES_SUCCESS = "1";
	public final static String RES_FAIL = "0";
	
	//Action URLs
	public final static String URL_USER = URL_SERVER  + "req_user.php";
	public final static String URL_FRIEND = URL_SERVER  + "req_friend.php";
	
	public static boolean isNetworkAvailable(Context context) {
	    ConnectivityManager connectivityManager 
	          = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
	    NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
	    return activeNetworkInfo != null && activeNetworkInfo.isConnected();
	}
}
 