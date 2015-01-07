package com.chekaz.data.structure;

public class StGame {
	//members	
		public  String 	GAME_NO = null,
						USER_TO = null,
						USER_FROM = null,	
						RECEIVED = null,
						DATE_STARTED = null,
						DATE_UPDATED = null,
						DATE_ENDED = null;
								
		public StGame(String game_no,
						String user_to,
						String user_from,
						String received,
						String date_started,
						String date_updated,
						String date_ended					
						){		
			
						GAME_NO = game_no;
						USER_TO = user_to;
						USER_FROM = user_from;	
						RECEIVED = received;
						DATE_STARTED = date_started;
						DATE_UPDATED = date_updated;
						DATE_ENDED = date_ended;
						
		}
}
