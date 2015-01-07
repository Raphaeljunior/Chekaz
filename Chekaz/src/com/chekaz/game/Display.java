package com.chekaz.game;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.Canvas;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/*
 * Actual canvas for drawing and input from the user
 */

public class Display extends SurfaceView implements SurfaceHolder.Callback{
	
	private Engine engine;	
	private Context main;	
	boolean isCreated = false;
	
	public Display(Context main) {
		super(main);
		this.main = main;	
	    getHolder().addCallback(this);	    
 		setFocusable(true);		
 		setClickable(true);
 		setFocusableInTouchMode(true); 		
 		engine = new Engine(this);		
	}
	
	//Get the Main context reference
	public Context getMain(){
		return main;
	}
	
	//Get the Engine reference
	public Engine getEngine(){
		return engine;
	}
	
	@Override
	public void surfaceChanged(SurfaceHolder arg0, int arg1, int width, int height) {	
		//Update the display height and width
	    engine.setSize(width, height);
	    engine.repaint();
	}

	@Override
	public void surfaceCreated(SurfaceHolder arg0) {		
		((Activity) main).setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);			
		isCreated = true;
		engine.setSize(getWidth(), getHeight());	
		engine.initUI();
	    engine.repaint();
	    
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder arg0) {		
		isCreated = false;
		
	}	
	
	public boolean isCreated(){
		return isCreated;
	}
	
	//Get KeyPress Events
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		
		if (keyCode == KeyEvent.KEYCODE_BACK) {	 
			engine.back();
	        return true;
	    }
		
		return super.onKeyDown(keyCode, event);
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch(event.getAction()){
			case MotionEvent.ACTION_DOWN: 
				engine.pressed((int)event.getX(), (int)event.getY()); 
				break;
			case MotionEvent.ACTION_UP: 
				engine.released((int)event.getX(), (int)event.getY()); 
				break;	
			case MotionEvent.ACTION_MOVE: 
				engine.dragged((int)event.getX(), (int)event.getY()); 
				break;	
		}
		return super.onTouchEvent(event);
	}	
	
	private void repaint(Canvas canvas){		
		if(engine!=null) 
			engine.draw(canvas);
	}
	
	public void repaint(){
		Canvas canvas = getHolder().lockCanvas();
		repaint(canvas);
		getHolder().unlockCanvasAndPost(canvas);
	}
}
