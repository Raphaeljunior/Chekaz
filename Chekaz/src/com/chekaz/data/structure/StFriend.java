package com.chekaz.data.structure;

public class StFriend {
	//members	
		public  String 	FRIEND_NO = null,
						USER_TO = null,
						USER_FROM = null,	
						ACCEPTED = null,
						SEEN_ACCEPTED = null,
						RECEIVED = null,
						DATE_SENT = null;
								
		public StFriend(String friend_no,
						String user_to,
						String user_from,
						String accepted,
						String seen_accepted,
						String received,
						String date_sent					
						){		
			
						FRIEND_NO = friend_no;
						USER_TO = user_to;
						USER_FROM = user_from;	
						ACCEPTED = accepted;
						SEEN_ACCEPTED = seen_accepted;
						RECEIVED = received;
						DATE_SENT = date_sent;
						
		}
}
