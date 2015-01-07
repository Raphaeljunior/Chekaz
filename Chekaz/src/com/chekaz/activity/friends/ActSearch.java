package com.chekaz.activity.friends;

import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.chekaz.activity.main.ActProfile;
import com.chekaz.connect.ConFriend;
import com.chekaz.connect.Connect;
import com.chekaz.data.main.DbChekaz;
import com.chekaz.layout.SearchItem;
import com.chekaz.layout.SearchItemAdapter;
import com.chekaz.util.Action;
import com.chekaz.util.ActionJSON;
import com.keysindicet.chekaz.R;


public class ActSearch extends ActionBarActivity {		
	
	private ListView lstSearch = null;
	private ArrayList<SearchItem> items = null;
	private SearchItemAdapter adapter = null;	    
    private ImageView imgSearch;
    private EditText txtSearch;
    private TextView txtMessage;
    private ProgressBar progress; 
    private View tint;
    
    //Search parameters
    String keyword;
    String user_no;
    public final static int limit = 2;
    int lower = 0; 
    
    SearchItem search = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);		
		setContentView(R.layout.act_search);	
		lstSearch = (ListView) findViewById(R.id.list);
		progress = (ProgressBar)findViewById(R.id.loading_spinner);
        imgSearch = (ImageView) findViewById(R.id.imgSearch);        
        txtSearch = (EditText) findViewById(R.id.txtSearch);
        txtMessage = (TextView) findViewById(R.id.txtMessage);
        tint = findViewById(R.id.tint);
        
		items = new ArrayList<SearchItem>();				
		adapter = new SearchItemAdapter(this, items);
		lstSearch.setAdapter(adapter);				
		
        
		//Set the user no
		DbChekaz db = new DbChekaz(getApplicationContext());
		user_no = db.info.get().USER_NO;
		db.close();
        
                
        //Get the keyword
        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
        	String keyword = bundle.getString("keyword");
        	if(keyword != null){
        		if(!keyword.trim().equals("")){    		       	
        			txtSearch.setText(keyword);
        			search(imgSearch);
        		}      		
        	}
        } 	
        
        lstSearch.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int sel,
					long arg3) {				
				
				SearchItem item = items.get(sel);
				
				switch(item.getType()){
					case SearchItem.TYPE_NORMAL:
						String strange_no = item.getUserNo();
						String fullname = item.getFullname();
						String user_name = item.getUserName();
						boolean loading = item.isLoading();
						
						Intent i = new Intent(getApplicationContext(), ActProfile.class);
						i.putExtra("type", ActProfile.PROFILE_STRANGER);
						i.putExtra("stranger_no", strange_no);
						i.putExtra("fullname", fullname);
						i.putExtra("user_name", user_name);
						i.putExtra("loading", loading);
						
						startActivity(i);
						break;
				}
				
			}
		});
        
	}
	
	public void cancel(){
		
		progress.setVisibility(View.INVISIBLE);
		imgSearch.setVisibility(View.VISIBLE);
			
		tint.setVisibility(View.GONE);
		
		if(items.size() <= 0){
			txtMessage.setVisibility(View.VISIBLE);						
		}		
		
		txtSearch.setEnabled(true);
	}
	
	public void search(View v){		
		if(keywordOk()){

			txtSearch.setEnabled(false);
			
			imgSearch.setVisibility(View.INVISIBLE);			
			progress.setVisibility(View.VISIBLE);
			tint.setVisibility(View.VISIBLE);
			txtMessage.setVisibility(View.GONE);
			
			lower = 0;
			
			keyword = txtSearch.getText().toString();
			find();
		}
		else{
			Toast.makeText(getApplicationContext(), "Enter username or email.", Toast.LENGTH_SHORT).show();
		}
	}	
	
	public void find(){
		
			String strLower = String.valueOf(lower);
			String strLimit = String.valueOf(limit);
			ConFriend.find(user_no, keyword, strLower, strLimit, new ActionJSON() {
				
				@Override
				public void action(JSONObject json) {						
					
					try {
						String success = json.getString("success");
						
						if(success.equals(Connect.RES_SUCCESS)){
							JSONObject user = json.getJSONObject("user");							
							updateItems(user);
						}
						else{
							txtMessage.setText("Unable to connect");
							lstSearch.setVisibility(View.GONE);
						}
						
					} catch (JSONException e) {
						txtMessage.setText("Unable to connect");
						lstSearch.setVisibility(View.GONE);
					}
					
					cancel();
				}
			}, new Action() {
				
				@Override
				public void action() {
					cancel();
					lstSearch.setVisibility(View.GONE);
					txtMessage.setText("Unable to connect");
				}
			});		
		
	}
		
	public boolean keywordOk(){
		String keyword = txtSearch.getText().toString();
		if(keyword.trim().equals("")){
			return false;
		}
		
		return true;
	}
	
	public void updateItems(JSONObject user){
		
		try {
			
			if(search != null)
				items.remove(search);
			
			search = null;
						
			String count = user.getString("count").toString();
			int counter = Integer.parseInt(count);		
			
			if(lower == 0){
				items = new ArrayList<SearchItem>();				
				adapter = new SearchItemAdapter(this , items);
				lstSearch.setAdapter(adapter);			
			}			
			
			for(int i=0; i<counter ; ++i){	
				
				JSONObject record = user.getJSONObject(String.valueOf(i));				
				
				String no = record.getString("user_no").toString();
				String user_name = record.getString("user_name").toString();
				String fullname = record.getString("fullname").toString();	
								
				SearchItem item = new SearchItem(no, user_name, fullname);	
				items.add(item);				
			}
			
			updateStatus();		
			lstSearch.setVisibility(View.VISIBLE);
			
			if(items.size() == 0){
				txtMessage.setVisibility(View.VISIBLE);
				txtMessage.setText("No results were found.");
			}
			else if(counter >= limit){
				search = new SearchItem(null, null, null, SearchItem.TYPE_SEARCH);
				items.add(search);
			}
			
			adapter.notifyDataSetChanged();
						
		} catch (JSONException e) {
			
		}
	}
	
	public void loadMore(){
		imgSearch.setVisibility(View.INVISIBLE);
		progress.setVisibility(View.VISIBLE);
		txtMessage.setVisibility(View.GONE);
		lower += limit;
		find();
	}
	
	public void updateStatus(){
		DbChekaz db = new DbChekaz(getApplicationContext());
		
		for(int i = 0; i < items.size() ; ++i){			
			int status = db.friend.getStatus(user_no, items.get(i).getUserNo());
			items.get(i).setStatus(status);
		}
		
		db.close();
	}
	
	@Override
	protected void onRestart() {
		updateStatus();
		adapter.notifyDataSetChanged();
		super.onRestart();
	}
}

