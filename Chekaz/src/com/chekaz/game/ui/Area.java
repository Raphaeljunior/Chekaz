package com.chekaz.game.ui;

public class Area {
	
	/* Class for object to hold the dimensions
	of a control on the screen */
	
	//Accuracy of point existence check
	private int ACCURACY = 0;
	
	//Dimensions
	private int LEFT,
				TOP,
				WIDTH,
				HEIGHT;
	
	public Area(){
		LEFT = TOP = WIDTH = HEIGHT = 0;
	}
	
	public Area(int left, int top, int width, int height){
		LEFT = left;
		TOP = top;
		WIDTH = width;
		HEIGHT = height;
	}
	
	public void setDim(int left, int top, int width, int height){
		LEFT = left;
		TOP = top;
		WIDTH = width;
		HEIGHT = height;
	}
	
	public void setSize(int width, int height){
		WIDTH = width;
		HEIGHT = height;
	}
	
	public void setPos(int left, int top){
		LEFT = left;
		TOP = top;
	}
	
	public void setLeft(int left){
		LEFT = left;
	}
	
	public void setTop(int top){
		TOP = top;
	}
	
	public void setWidth(int width){
		WIDTH = width;
	}
	
	public void setHeight(int height){
		HEIGHT = height;
	}
	
	public int getLeft(){
		return LEFT;
	}
	
	public int getTop(){
		return TOP;
	}
	
	public int getWidth(){
		return WIDTH;
	}
	
	public int getHeight(){
		return HEIGHT;
	}
	
	public void setAccuracy(int accuracy){
		ACCURACY = accuracy;
	}
	
	//Check if a point exists in specific area
	public boolean pointExists(int x, int y){
		if(x > LEFT-(ACCURACY) && y > TOP-(ACCURACY) && x < (LEFT+WIDTH+ACCURACY*2) && y < (TOP+HEIGHT+ACCURACY*2)){               
            return true;         
		}
		
		return false;
	}
}
