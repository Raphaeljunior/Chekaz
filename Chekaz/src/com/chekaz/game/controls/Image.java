package com.chekaz.game.controls;

import com.chekaz.game.Engine;
import com.chekaz.game.ui.Control;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;


public class Image extends Control {

	Bitmap bmpImage = null;
	
	public Image(Engine engine) {
		super(engine);		
	}
	
	public Image(Engine engine, int id) {
		super(engine);		
		setImage(id);
	}
	
	public Image(Engine engine, Bitmap bmp) {
		super(engine);		
		setImage(bmp);
	}
	
	public void setImage(int id){
		bmpImage = BitmapFactory.decodeResource(engine.getMain().getResources(), id);
		setSize(bmpImage.getWidth(), bmpImage.getHeight());
	}
	
	public void setImage(int id, int width, int height){
		bmpImage = Bitmap.createScaledBitmap(
				BitmapFactory.decodeResource(engine.getMain().getResources(), id),
				width, height, false);
		
		setSize(bmpImage.getWidth(), bmpImage.getHeight());
	}
	
	public void setImage(Bitmap bmp){
		bmpImage = bmp;
		setSize(bmpImage.getWidth(), bmpImage.getHeight());
	}

	@Override
	public void draw(Canvas canvas) {
		canvas.drawBitmap(bmpImage, getLeft(), getTop(), paint);
	}	
	

	@Override
	public void pressed(int x, int y) {		
		clicked();
	}

	@Override
	public void released(int x, int y) {
		// TODO Auto-generated method stub

	}

	@Override
	public void dragged(int x, int y) {
		// TODO Auto-generated method stub

	}

	@Override
	public void pointerUp(int x, int y) {
		// TODO Auto-generated method stub
		
	}

}
