package com.chekaz.game.ui;

import com.chekaz.game.Engine;
import com.chekaz.game.listener.DrawListener;
import com.chekaz.game.listener.TouchListener;
import com.chekaz.util.Action;

import android.graphics.Paint;
public abstract class Control implements DrawListener, TouchListener {
	
	
	protected Engine engine= null;	
	private Area area = null;
	protected Paint paint = null;
	
	//When the control is clicked
	private Action onClicked = null;
	
	
	int MASTER_WIDTH = 0,
		MASTER_HEIGHT = 0;
	
	//Layout
    int LAYOUT = 0;
    public static final int LAYOUT_FREE = 0;
    public static final int LAYOUT_TOP = 1;
    public static final int LAYOUT_TOP_RIGHT = 2;
    public static final int LAYOUT_TOP_LEFT = 3;
    public static final int LAYOUT_CENTRE = 4;
    public static final int LAYOUT_CENTRE_RIGHT = 5;
    public static final int LAYOUT_CENTRE_LEFT = 6;
    public static final int LAYOUT_CENTRE_TOP = 7;
    public static final int LAYOUT_CENTRE_BOTTOM = 8;
    public static final int LAYOUT_BOTTOM = 9;
    public static final int LAYOUT_BOTTOM_LEFT = 10;
    public static final int LAYOUT_BOTTOM_RIGHT = 11;
    public static final int LAYOUT_LEFT = 12;
    public static final int LAYOUT_RIGHT = 13;
    public static final int LAYOUT_CENTER_VERTICAL = 14;
    public static final int LAYOUT_CENTER_HORIZONTAL = 15;
	
	//Superclass constructor
	public Control(Engine engine){
		this.engine = engine;
		area = new Area();
		
		//Get dimensions of the main display
		MASTER_WIDTH = engine.getWidth();
		MASTER_HEIGHT = engine.getHeight();
		
		//set up the paint for the control
		paint = new Paint();
		paint.setFlags(Paint.FILTER_BITMAP_FLAG);
	}
	
	//Set the layout of control in the form
	public void setLayout(int layout){
        this.LAYOUT = layout;   
        position();
    } 
	
	public void position(){
        //Set the layouts
        switch(LAYOUT){
        
            case LAYOUT_TOP: 
                area.setTop(0); 
                break;
                
            case LAYOUT_TOP_LEFT:                
                area.setPos(0, 0);
                break;
                
            case LAYOUT_TOP_RIGHT:                
                area.setPos(MASTER_WIDTH - area.getWidth(), 0);
                break;
                
            case LAYOUT_CENTRE:               
                area.setPos(MASTER_WIDTH/2 - (area.getWidth()/2), MASTER_HEIGHT/2 - (area.getHeight()/2));
                break;
                
            case LAYOUT_CENTRE_LEFT:                
                area.setPos(0, MASTER_HEIGHT/2 - (area.getHeight()/2));
                break; 
                
            case LAYOUT_CENTRE_RIGHT:
            	area.setPos(MASTER_HEIGHT/2 - (area.getHeight()/2), MASTER_WIDTH - area.getWidth());                
                break;
                
            case LAYOUT_CENTRE_BOTTOM:
            	area.setPos(MASTER_WIDTH/2 - (area.getWidth()/2), MASTER_HEIGHT - area.getHeight());
                break; 
            	
            case LAYOUT_CENTRE_TOP:
            	area.setPos(MASTER_WIDTH/2 - (area.getWidth()/2), 0);
                break;
                
            case LAYOUT_BOTTOM:
            	area.setTop(MASTER_HEIGHT - area.getHeight());              
                break; 
                
            case LAYOUT_BOTTOM_LEFT:
            	area.setPos(0, MASTER_HEIGHT - area.getHeight());
                break;
                
            case LAYOUT_BOTTOM_RIGHT:
            	area.setPos( MASTER_WIDTH - area.getWidth(), MASTER_HEIGHT - area.getHeight());
                break;   
                
            case LAYOUT_LEFT:               
                area.setLeft(0);
                break;
                
            case LAYOUT_RIGHT:                
                area.setLeft(MASTER_WIDTH - area.getWidth());
                break;  
                
            case LAYOUT_CENTER_HORIZONTAL:                
                area.setLeft(MASTER_WIDTH/2 - (area.getWidth()/2));
                break;  
                
            case LAYOUT_CENTER_VERTICAL:                
            	area.setLeft(MASTER_HEIGHT/2 - (area.getHeight()/2));
                break;    
        }        
        
    }
	
	//Set the dimensions of the control
	public void setDim(int left, int top, int width, int height){
		area.setDim(left, top, width, height);
	}
	
	//Set the size of the control
	public void setSize(int width, int height){
		area.setSize(width, height);
	}
	
	public void repaint(){
		if(engine!=null)
			engine.repaint();
	}
	
	//Set the position of the control on the screen
	public void setPos(int left, int top){
		area.setPos(left, top);
	}
	
	public int getLeft(){
		return area.getLeft();
	}
	
	public Engine getEngine(){
		return this.engine;
	}
	
	public int getTop(){
		return area.getTop();
	}
	
	public int getWidth(){
		return area.getWidth();
	}
	
	public int getHeight(){
		return area.getHeight();
	}
	
	public void setLeft(int left){
		area.setLeft(left);
	}
	
	public void setTop(int top){
		area.setTop(top);
	}
	
	public void setWidth(int width){
		area.setWidth(width);
	}
	
	public void setHeight(int height){
		area.setHeight(height);
	}
	
	public void setAccuracy(int accuracy){
		area.setAccuracy(accuracy);
	}
	
	public Area getArea(){
		return area;
	}
	
	public Paint getPaint(){
		return paint;
	}
	
	//Check if control has been clicked
	public boolean isClicked(int x, int y){
		return area.pointExists(x, y);
	}
	
	public void clicked(){
		if(onClicked!=null)
			onClicked.action();
	}
	
	
	public void setOnClick(Action onClicked){
		this.onClicked = onClicked;
	}
	
	//if the pointer is released in the all application
	public abstract void pointerUp(int x, int y);
}
