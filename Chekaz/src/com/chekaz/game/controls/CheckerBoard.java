package com.chekaz.game.controls;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.chekaz.game.Engine;
import com.chekaz.game.ui.Control;
import com.chekaz.util.Action;
import com.chekaz.util.TimerRepeat;

public class CheckerBoard{
	
	private Engine engine;
	private Form frm;
	
	//An array of all the boxes
	private Piece[] pieces;
	private Box[][] boxes;
	
	private int MASTER_WIDTH = 0;
	private int MASTER_HEIGHT = 0;
	private int WIDTH = 0;
	private int HEIGHT = 0;
	private int TOP = 0;
	private int LEFT = 0;
	private int BOX_WIDTH = 0;
	
	private Bitmap bmpWhite = null;
	private Bitmap bmpBlack = null;	
	
	public CheckerBoard(Engine engine, Form frm){
		this.engine = engine;
		this.frm = frm;
		MASTER_WIDTH = engine.getWidth();
		MASTER_HEIGHT = engine.getHeight();
		
		initDim();
		drawBoxes();
		drawPieces();
	}	
	
	private void initDim(){
		
		bmpWhite = engine.getBmpWhite();
		bmpBlack = engine.getBmpBlack();
		
		BOX_WIDTH = bmpWhite.getWidth();
		WIDTH = HEIGHT = BOX_WIDTH * 8;			
		
		LEFT = (MASTER_WIDTH/2) - (WIDTH/2);
		TOP = (MASTER_HEIGHT/2) - (HEIGHT/2);
	}
	
	private void drawBoxes(){
		
		int curLeft = LEFT;
		int curTop = TOP;
		boolean altBg = true;		
		this.boxes = new Box[8][8];		
		
		//Vertical draw
		for(int t=0 ; t<8 ; ++t){
			
			//Horizontal draw
			for(int l=0 ; l<8 ; ++l){
				
				boxes[t][l] = new Box(engine);
				boxes[t][l].setRowCol(t, l);
				boxes[t][l].setPos(curLeft, curTop);
				boxes[t][l].setSize( engine.getBmpBlack().getWidth(), engine.getBmpBlack().getHeight());
				
				if(altBg)
					boxes[t][l].setType(Box.TYPE_WHITE);
				else
					boxes[t][l].setType(Box.TYPE_BLACK);
				
				frm.addcontrol(boxes[t][l]);
				
				curLeft += BOX_WIDTH;
				altBg = !altBg;		
			}
			
			altBg = !altBg;
			curLeft = LEFT;
			curTop += BOX_WIDTH;
		}
		
		//Repaint
		this.engine.repaint();
	}
	

	private void drawPieces(){
		this.pieces = new Piece[24];		
		
		//	Assign the pieces to their boxes		
		// 	player 1		
		// 	Row 0		 
		
		pieces[0] = new Piece(engine, Piece.TYPE_PLAYER1, boxes[0][1]);		
		frm.addcontrol(pieces[0]);		
		pieces[1] = new Piece(engine, Piece.TYPE_PLAYER1, boxes[0][3]);		
		frm.addcontrol(pieces[1]);
		pieces[2] = new Piece(engine, Piece.TYPE_PLAYER1, boxes[0][5]);		
		frm.addcontrol(pieces[2]);		
		pieces[3] = new Piece(engine, Piece.TYPE_PLAYER1, boxes[0][7]);		
		frm.addcontrol(pieces[3]);
		
		//	Row 1
		
		pieces[4] = new Piece(engine, Piece.TYPE_PLAYER1, boxes[1][0]);		
		frm.addcontrol(pieces[4]);		
		pieces[5] = new Piece(engine, Piece.TYPE_PLAYER1, boxes[1][2]);		
		frm.addcontrol(pieces[5]);
		pieces[6] = new Piece(engine, Piece.TYPE_PLAYER1, boxes[1][4]);		
		frm.addcontrol(pieces[6]);		
		pieces[7] = new Piece(engine, Piece.TYPE_PLAYER1, boxes[1][6]);		
		frm.addcontrol(pieces[7]);
		
		//	Row 2
		
		pieces[8] = new Piece(engine, Piece.TYPE_PLAYER1, boxes[2][1]);		
		frm.addcontrol(pieces[8]);		
		pieces[9] = new Piece(engine, Piece.TYPE_PLAYER1, boxes[2][3]);		
		frm.addcontrol(pieces[9]);
		pieces[10] = new Piece(engine, Piece.TYPE_PLAYER1, boxes[2][5]);		
		frm.addcontrol(pieces[10]);		
		pieces[11] = new Piece(engine, Piece.TYPE_PLAYER1, boxes[2][7]);		
		frm.addcontrol(pieces[11]);
		
		//	player 2		
		// 	Row 5		 
		
		pieces[12] = new Piece(engine, Piece.TYPE_PLAYER2, boxes[5][0]);		
		frm.addcontrol(pieces[12]);		
		pieces[13] = new Piece(engine, Piece.TYPE_PLAYER2, boxes[5][2]);		
		frm.addcontrol(pieces[13]);
		pieces[14] = new Piece(engine, Piece.TYPE_PLAYER2, boxes[5][4]);		
		frm.addcontrol(pieces[14]);		
		pieces[15] = new Piece(engine, Piece.TYPE_PLAYER2, boxes[5][6]);		
		frm.addcontrol(pieces[15]);
		
		//	Row 6		 
		
		pieces[16] = new Piece(engine, Piece.TYPE_PLAYER2, boxes[6][1]);		
		frm.addcontrol(pieces[16]);		
		pieces[17] = new Piece(engine, Piece.TYPE_PLAYER2, boxes[6][3]);		
		frm.addcontrol(pieces[17]);
		pieces[18] = new Piece(engine, Piece.TYPE_PLAYER2, boxes[6][5]);		
		frm.addcontrol(pieces[18]);		
		pieces[19] = new Piece(engine, Piece.TYPE_PLAYER2, boxes[6][7]);		
		frm.addcontrol(pieces[19]);
		
		//	Row 7		 
		
		pieces[20] = new Piece(engine, Piece.TYPE_PLAYER2, boxes[7][0]);		
		frm.addcontrol(pieces[20]);		
		pieces[21] = new Piece(engine, Piece.TYPE_PLAYER2, boxes[7][2]);		
		frm.addcontrol(pieces[21]);
		pieces[22] = new Piece(engine, Piece.TYPE_PLAYER2, boxes[7][4]);		
		frm.addcontrol(pieces[22]);		
		pieces[23] = new Piece(engine, Piece.TYPE_PLAYER2, boxes[7][6]);		
		frm.addcontrol(pieces[23]);		
		this.engine.repaint();
	}
	
	public void repaint(){
		this.engine.repaint();
	}
	
	public void alert(String alert){
		this.engine.alert(alert);
	}
	
	public Box[][] getBoxes(){
		return this.boxes;
	}
	
	public Piece[] getPieces(){
		return this.pieces;
	}
	
	public void unselectAllBoxes(){
		
		for(int t=0 ; t<8 ; ++t){			
			
			for(int l=0 ; l<8 ; ++l){
				if(this.boxes[t][l].getType() == Box.TYPE_BLACK)
					boxes[t][l].setSelected(false);
			}
		}
	}	
	
}
