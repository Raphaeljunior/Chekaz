package com.chekaz.data.structure;

public class StInfo {
	//members	
		public  String 	USER_NO = null,
						IMEI = null,
						COUNTRY = null,	
						USER_NAME = null,
						FULLNAME = null,
						INVITE_TEXT = null,
						DATE_ADDED = null,
						DATE_UPDATED = null;
		
		public StInfo(	String user_no,
						String imei,
						String country,
						String user_name,
						String fullname,
						String invite_text,
						String date_added,
						String date_updated
						){		
			
						USER_NO = user_no;
						IMEI = imei;
						COUNTRY = country;
						USER_NAME = user_name;
						FULLNAME = fullname;
						INVITE_TEXT = invite_text;
						DATE_ADDED = date_added;
						DATE_UPDATED = date_updated;
						
		}
}
