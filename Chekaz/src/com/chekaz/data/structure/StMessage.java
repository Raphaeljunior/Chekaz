package com.chekaz.data.structure;

public class StMessage {
	//members	
		public  String 	MSG_NO = null,
						USER_TO = null,
						USER_FROM = null,	
						MSG = null,
						RECEIVED = null,
						DATE_SENT = null;					
								
		public StMessage(String msg_no,
						String user_to,
						String user_from,
						String msg,
						String received,
						String date_sent											
						){		
			
						MSG_NO = msg_no;
						USER_TO = user_to;
						USER_FROM = user_from;	
						MSG = msg;
						RECEIVED = received;
						DATE_SENT = date_sent;
						
		}
}
