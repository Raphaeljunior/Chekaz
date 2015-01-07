package com.chekaz.layout;

import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.chekaz.activity.friends.ActSearch;
import com.chekaz.connect.ConFriend;
import com.chekaz.connect.Connect;
import com.chekaz.data.main.DbChekaz;
import com.chekaz.data.table.Friend;
import com.chekaz.util.Action;
import com.chekaz.util.ActionJSON;
import com.keysindicet.chekaz.R;


 
public class SearchItemAdapter extends BaseAdapter {
     
    private ActSearch context;
    private ArrayList<SearchItem> searchItems;
    private int limit = ActSearch.limit;
    private int lower = 0;
     
    public SearchItemAdapter(ActSearch context, ArrayList<SearchItem> searchItems){
        this.context = context;
        this.searchItems = searchItems;
    }
 
    @Override
    public int getCount() {
        return searchItems.size();
    }
 
    @Override
    public  SearchItem getItem(int position) {      
        return searchItems.get(position);
    }
 
    @Override
    public long getItemId(int position) {
        return position;
    }
 
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {        
    	
    	SearchItem item = searchItems.get(position);
    	
        LayoutInflater mInflater = (LayoutInflater)
                context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        
        switch(item.getType()){
	        case SearchItem.TYPE_NORMAL:
	        	convertView = mInflater.inflate(R.layout.list_search, null);
	        	setupNormal(convertView, item);
	        	break;
	        	
	        case SearchItem.TYPE_SEARCH:
	        	convertView = mInflater.inflate(R.layout.list_search_more, null);
	        	setupSearch(convertView, item);
	        	break;	
        }     
         
        return convertView;
    }
    
    
    public void addFriend(final SearchItem item){
    	DbChekaz db = new DbChekaz(context);
    	String user_no = db.info.get().USER_NO;
    	db.close();
    	
    	String user_to = item.getUserNo();
    	
    	ConFriend.addFriend(user_no, user_to, new ActionJSON() {
			
			@Override
			public void action(JSONObject json) {				
				
				
				try {
					String success = json.getString("success");
					
					if(success.equals(Connect.RES_SUCCESS)){
						JSONObject res_addfriend = json.getJSONObject("res_addfriend");							
						DbChekaz db = new DbChekaz(context);
						db.friend.add((res_addfriend));
						db.close();
						
						item.setStatus(Friend.STATUS_SENT_TO);
					}					
					
				} catch (JSONException e) {
					
				}
				
				item.setLoading(false);
				notifyDataSetChanged();
			}
		}, new Action() {
			
			@Override
			public void action() {
				item.setLoading(false);
				notifyDataSetChanged();
			}
		});
    }
    
    private void setupNormal(View convertView, final SearchItem item){
    	
    	TextView txtFullName = (TextView) convertView.findViewById(R.id.txtFullName);
        TextView txtUsername = (TextView) convertView.findViewById(R.id.txtUsername);
        ImageView imgProfile = (ImageView) convertView.findViewById(R.id.imgProfile);
        final ImageView imgAdd = (ImageView) convertView.findViewById(R.id.imgAdd);
        final ProgressBar progress = (ProgressBar) convertView.findViewById(R.id.loading_spinner);
        LinearLayout llAdd = (LinearLayout) convertView.findViewById(R.id.llAdd);        
        
        
		txtFullName.setText(item.getFullname());
		txtUsername.setText(item.getUserName());
		imgProfile.setImageResource(R.drawable.profile);
        
        int status = item.getStatus();
        if(item.isLoading()){
        	imgAdd.setVisibility(View.INVISIBLE);
			progress.setVisibility(View.VISIBLE);
        }
        
        switch(status){
        	case Friend.STATUS_FRIENDS:
        		
        		txtUsername.setText("friends");
        		txtUsername.setTextColor(Color.BLACK);
        		txtUsername.setTypeface(Typeface.DEFAULT_BOLD);
        		llAdd.setVisibility(View.GONE);
        		break;
        	
        	case Friend.STATUS_SENT_FROM:        		
        		txtUsername.setText("sent you a request");
        		txtUsername.setTextColor(Color.BLACK);
        		txtUsername.setTypeface(Typeface.DEFAULT_BOLD);
        		llAdd.setVisibility(View.GONE);
        		break;
        	
        	case Friend.STATUS_SENT_TO:        		
        		txtUsername.setText("request sent");
        		txtUsername.setTextColor(Color.BLACK);
        		txtUsername.setTypeface(Typeface.DEFAULT_BOLD);
        		llAdd.setVisibility(View.GONE);
        		break;	
        }
               
        imgAdd.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View arg0) {
				imgAdd.setVisibility(View.INVISIBLE);
				progress.setVisibility(View.VISIBLE);
				item.setLoading(true);
				addFriend(item);
			}
		});
    }
    
    private void setupSearch(View convertView, final SearchItem item){
    	
        final Button btnLoadMore = (Button) convertView.findViewById(R.id.btnLoadMore);
        final ProgressBar progress = (ProgressBar) convertView.findViewById(R.id.loading_spinner);
        FrameLayout llLoadMore = (FrameLayout) convertView.findViewById(R.id.llLoadMore); 
         
        if(item.isLoading()){
        	btnLoadMore.setVisibility(View.INVISIBLE);
			progress.setVisibility(View.VISIBLE);
        } 
        
        if(item.getStatus() == 0){
        	llLoadMore.setVisibility(View.GONE);
        }
               
        btnLoadMore.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View arg0) {
				btnLoadMore.setVisibility(View.INVISIBLE);
				progress.setVisibility(View.VISIBLE);
				item.setLoading(true);
				
				context.loadMore();
			}
		});
    }
    
    
}

