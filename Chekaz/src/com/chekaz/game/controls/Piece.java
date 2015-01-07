package com.chekaz.game.controls;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import com.chekaz.game.Engine;
import com.chekaz.game.ui.Area;
import com.chekaz.game.ui.Control;

public class Piece extends Control{	
	
	//Piece type		
	public final static int TYPE_EMPTY = 0;
	public final static int TYPE_PLAYER1 = 1;
	public final static int TYPE_PLAYER2 = 2;
	public final static int TYPE_PLAYER1_KING = 3;
	public final static int TYPE_PLAYER2_KING = 4;	
							
	//Piece info
	public Area area;
	private int type;
	private boolean selected = false;
	
	Box box = null;
	
	public Piece(Engine engine, int type, Box box){
		super(engine);
		this.area  = new Area();
		this.type = type;
		setSize(engine.getBmpPlayer1().getWidth(), engine.getBmpPlayer1().getHeight());
		setBox(box);
	}
	
	public void setType(int type){
		
		if(type == TYPE_EMPTY){
			setDim(0, 0, 0, 0);
		}
		
		this.type = type;
	}
	
	public void setBox(Box box){
		this.box = box;
		setPos(	box.getLeft()+(engine.getBmpBlack().getWidth()/2 - engine.getBmpPlayer1().getWidth()/2), 
				box.getTop()+(engine.getBmpBlack().getWidth()/2 - engine.getBmpPlayer1().getWidth()/2));
		
		box.setPiece(this);
	}
		
	public Box getBox(){
		return this.box;
	}
		
	public int getType(){
		return this.type;
	}

	@Override
	public void draw(Canvas canvas) {
		
		if(getType() == TYPE_EMPTY)
			return;
		
		Bitmap bmp = null;	
		
		
		switch(type){				
			case TYPE_PLAYER1:
				bmp = engine.getBmpPlayer1();break;
			case TYPE_PLAYER2:					
				bmp = engine.getBmpPlayer2();break;
			case TYPE_PLAYER1_KING:
				bmp = engine.getBmpPlayer1King();break;
			case TYPE_PLAYER2_KING:					
				bmp = engine.getBmpPlayer2King();break;	
		}		
		
		canvas.drawBitmap(bmp, getLeft(), getTop(), paint);
	}

	@Override
	public void pressed(int x, int y) {
		if(engine.logic.getCurPiece() == null)
			engine.logic.setCurPiece(this);	
		
		if(engine.play.isTurn(this)){
			engine.logic.setSelPiece(this);
		}
				
	}

	@Override
	public void released(int x, int y) {
			
	}
	
	@Override
	public void dragged(int x, int y) {	
		
	}
	
	@Override
	public void pointerUp(int x, int y) {
				
	}
}
