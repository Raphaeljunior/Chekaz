package com.chekaz.game.controls;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import com.chekaz.game.Engine;
import com.chekaz.game.ui.Area;
import com.chekaz.game.ui.Control;
import com.chekaz.util.Action;
import com.chekaz.util.TimerRepeat;

public class Box extends Control{	
	
	//Box type		
	public final static int TYPE_BLACK = 1;
	public final static int TYPE_WHITE = 2;	
	
							
	//Box info
	public Area area;
	private int type;
	private int row;
	private int col;
	private boolean selected = false;
	private boolean flashing = false;
	private boolean isFlashing = false;
	
	//Piece
	private Piece piece = null;
	
	
	public Box(Engine engine, int type){
		super(engine);
		this.area  = new Area();
		this.type = type;
	}
	
	public Box(Engine engine){
		super(engine);
		this.area  = new Area();		
	}
	
	public void setType(int type){
		this.type = type;
	}
	
	public int getType(){
		return this.type;
	}
	
	public void setPiece(Piece piece){
		this.piece = piece;
	}
	
	public Piece getPiece(){
		return this.piece;
	}
	
	public void setSelected(boolean selected){
		this.selected = selected;
	}
	
	public boolean isSelected(){
		return this.selected;
	}
	
	public void setFlashing(boolean flashing){
		this.flashing = flashing;
	}
	
	public boolean isFlashing(){
		return this.flashing;
	}
	
	public void setRowCol(int row, int col){
		this.row = row;
		this.col = col;
	}
	
	public int getRow(){
		return this.row;
	}
	
	public int getCol(){
		return this.col;
	}
	
	public void flash(){
		if(!isFlashing){
			isFlashing = true;
			TimerRepeat r = new TimerRepeat(4, 400, new Action() {			
				@Override
				public void action() {
					if(isFlashing())
						setFlashing(false);
					else
						setFlashing(true);
					
					repaint();
				}
			}, new Action() {
				
				@Override
				public void action() {
					isFlashing = false;					
				}
			});
		}
	}

	@Override
	public void draw(Canvas canvas) {
			
		Bitmap bmp = null;
		
		if(isSelected() || isFlashing()){
			bmp = engine.getBmpBoxSel();
		}
		else{
		
			switch(type){				
				
				case TYPE_WHITE:
					bmp = engine.getBmpWhite();break;
					
				case TYPE_BLACK:			
					bmp = engine.getBmpBlack();break;
			}
		}
		
		canvas.drawBitmap(bmp, getLeft(), getTop(), paint);
	}

	@Override
	public void pressed(int x, int y) {
				
		//If the box is not empty
		if(getPiece() != null){
			if(engine.play.isTurn(getPiece())){
				engine.logic.setSelPiece(getPiece());
			}
		}
		else if(getType() == TYPE_BLACK && engine.logic.getSelBox1() != null){			
			engine.logic.setSelBox2(this);
			engine.logic.movePiece();			
		}	
		
	}
	
	public boolean isEmpty(){
		return (getPiece() == null ? true : false);
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
