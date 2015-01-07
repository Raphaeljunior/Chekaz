package com.chekaz.layout;

public class SearchItem {
	
    private String 	user_no,
    				user_name,
    				fullname;
    
    private int status;
    private boolean loading;
    private int type = 1;
    
    public static final int TYPE_NORMAL = 1;
    public static final int TYPE_SEARCH = 2;
     
    public SearchItem(){} 
     
    public SearchItem(String user_no, String user_name, String fullname, int type){
        this.user_no = user_no;      
        this.user_name = user_name;
        this.fullname = fullname;
        this.type = type;
        this.status = 1;
    }
    
    public SearchItem(String user_no, String user_name, String fullname){
        this.user_no = user_no;      
        this.user_name = user_name;
        this.fullname = fullname;        
    }
    
    public String getUserNo(){
        return this.user_no;
    } 
    
    public String getUserName(){
        return this.user_name;
    }
    
    public String getFullname(){
        return this.fullname;
    }
    
    public int getStatus(){
        return this.status;
    }
    
    public int getType(){
        return this.type;
    }
    
    public boolean isLoading(){
    	return this.loading;
    }
        
    public void setUserNo(String user_no){
    	this.user_no = user_no;
    }
    
    public void setLoading(boolean loading){
    	this.loading = loading;
    }
    
    public void setUserName(String user_name){
    	this.user_name = user_name;
    }
   
    public void setFullName(String fullname){
    	this.fullname = fullname;
    }
    
    public void setStatus(int status){
    	this.status = status;
    }
    
}
