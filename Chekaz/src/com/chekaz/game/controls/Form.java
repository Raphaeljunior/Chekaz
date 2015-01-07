package com.chekaz.game.controls;


import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.text.method.MovementMethod;

import com.chekaz.game.Engine;
import com.chekaz.game.ui.Control;
import com.chekaz.game.ui.Item;
import com.chekaz.util.Action;
import com.chekaz.util.ActionInput;


public class Form extends Control {
	
	//Form's drawing bitmap and canvas
	private Bitmap bmpForm = null;
	private Canvas canvasForm = null;
	
	//Store all form controls
	private Item controls;
	
	//When the back button is pressed
	private Action onBack = null;
	
	// Color
	private int bgColor = Color.WHITE;
	public Form(Engine engine, int size) {
		super(engine);
		
	    controls = new Item(size);
		
		//update the size with main display size
		setSize(engine.getWidth(), engine.getHeight());
		
		//Create the  form bitmap
		bmpForm = Bitmap.createBitmap(getWidth(), getHeight(), Config.ARGB_8888);
		canvasForm = new Canvas(bmpForm);
	}
	
	//Add controls to the form
	public void addcontrol(Control control){
		controls.addControl(control);
	}	
	
	public void setbgColor(int bgColor){
		this.bgColor = bgColor;
	}
	
	public void setOnBack(Action onBack){
		this.onBack = onBack;
	}
	
	public void bringToTop(Control control){
		controls.bringToTop(control);
	}
	
	public void back(){
		if(onBack!=null) 
			onBack.action();
	}	
	
	public Item getControls(){
		return this.controls;
	}
	
	@Override
	public void draw(Canvas canvas) {
		canvasForm.drawColor(bgColor);
		controls.draw(canvasForm);
		canvas.drawBitmap(bmpForm, getLeft(), getTop(), paint);
	}
	

	@Override
	public void pressed(int x, int y) {
		// TODO Auto-generated method stub
		Control control = controls.getClicked(x, y);
		if(control!=null)
			control.pressed(x, y);
		else
			if(formPressed != null) formPressed.action(x, y); 
	}

	@Override
	public void released(int x, int y) {
		
		if(engine.logic.getCurPiece() != null){
			engine.logic.dropPiece();
		}
		
		engine.logic.setCurPiece(null);
		
		//Register pointer up in all controls to update interface
		controls.pointerUp(x, y);
		
		Control control = controls.getClicked(x, y);
		if(control!=null)
			control.released(x, y);
		else 
			if(formReleased != null) formReleased.action(x, y);		
		
			
	}

	@Override
	public void dragged(int x, int y) {
		
		if(engine.logic.getCurPiece() != null)
			engine.pieceDrag.drag(engine.logic.getCurPiece(), x, y);			
		
	}

	
	public Bitmap getShadowBitmap(){
		
		paint.setColor(Color.BLACK);
		paint.setAlpha(100);		
		
		canvasForm.drawRect(0, 0, getWidth(), getHeight(), paint);
		paint.setAlpha(255);
		return bmpForm;
	}
	
	public Canvas getCanvas(){
		return canvasForm;
	}
	
	//Catch events for form
	private ActionInput formDragged,
						formPressed,
						formReleased;
	
	public void setFormDragged(ActionInput formDragged){
		this.formDragged = formDragged;
	}
	
	public void setFormPressed(ActionInput formPressed){
		this.formPressed = formPressed;
	}
	
	public void setFormReleased(ActionInput formReleased){
		this.formReleased = formReleased;
	}

	@Override
	public void pointerUp(int x, int y) {
		// TODO Auto-generated method stub
		
	}
	
	
}
