package com.chekaz.device;

import com.keysindicet.chekaz.R;

import android.content.Context;
import android.telephony.TelephonyManager;

public class Device {
	public static String getIMEI(Context ctx){
		TelephonyManager telephonyManager = (TelephonyManager)ctx.getSystemService(Context.TELEPHONY_SERVICE);		
		return telephonyManager.getDeviceId();
	}
		
	public static String getCountryZipCode(Context ctx){

        String CountryID = null;
        String CountryZipCode = null;

        TelephonyManager manager = (TelephonyManager) ctx.getSystemService(Context.TELEPHONY_SERVICE);
              //getNetworkCountryIso
        CountryID= manager.getSimCountryIso().toUpperCase();
        String[] rl = ctx.getResources().getStringArray(R.array.countryCodes);
        for(int i=0;i<rl.length;i++){
            String[] g=rl[i].split(",");
            if(g[1].trim().equals(CountryID.trim())){
                 CountryZipCode=g[0];
                 break;  
            }
        }
        
        return CountryZipCode;
	}    
}
