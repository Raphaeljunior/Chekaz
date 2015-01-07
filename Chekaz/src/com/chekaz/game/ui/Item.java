package com.chekaz.game.ui;

import com.chekaz.game.listener.DrawListener;

import android.graphics.Canvas;

public class Item implements DrawListener{
	
	//Store all form controls
	private Control[] controls = null;
	
	private int count = 0;
	
	public Item(int size){
		controls = new Control[size];		
	}
	
	public void addControl(Control control){
		controls[count] = control;
		++count;
	}
	
	public int getSize(){
		return controls.length;
	}
	
	public void bringToTop(Control control){
		for(int i=count-1; i>=0 ; --i){
			if(controls[i].equals(control)){
				controls[i] = controls[count - 1];
				controls[count - 1] = control;
			}				
		}
	}
	
	//call pointer up in all controls
	public void pointerUp(int x, int y){
		for(int i=0; i<count; ++i)
			controls[i].pointerUp(x, y);
	}
	
	public Control getClicked(int x, int y){
		for(int i=count-1; i>=0 ; --i){
			if(controls[i].isClicked(x, y))
				return controls[i];
		}
		return null;
	}
	
	public Control getdroppedBox(int x, int y){
		for(int i=count-1; i>=0 ; --i){
			if(controls[i].isClicked(x, y) && controls[i].getClass().getName().equals("com.chekaz.game.controls.Box"))
				return controls[i];
		}
		return null;
	}

	//Draw all controls
	@Override
	public void draw(Canvas canvas) {		
		for(int i=0; i<count ; ++i)
			controls[i].draw(canvas);
	}
}
