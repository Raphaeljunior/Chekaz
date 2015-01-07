package com.chekaz.game;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.widget.Toast;

import com.keysindicet.chekaz.R;
import com.chekaz.game.anim.PieceDrag;
import com.chekaz.game.anim.PieceMove;
import com.chekaz.game.controls.CheckerBoard;
import com.chekaz.game.controls.Form;
import com.chekaz.game.listener.DrawListener;
import com.chekaz.game.listener.TouchListener;
import com.chekaz.game.logic.CheckerLogic;
import com.chekaz.game.logic.CheckerPlay;
import com.chekaz.game.logic.CheckerPos;
import com.chekaz.game.logic.DoubleCapture;
import com.chekaz.game.logic.MoveCheck;
import com.chekaz.util.Pic;


public class Engine implements TouchListener, DrawListener{
	
	
	private int WIDTH = 0,
			   HEIGHT = 0;
	
	private Context main = null;
	private Display display = null;		
	
	//Main form for holding all controls
	public Form frmMain = null;
	
	//Animations
	public PieceDrag pieceDrag = null;
	public PieceMove pieceMove = null;
	
	//Logic
	public CheckerLogic logic = null;
	public CheckerPos pos = null;
	public CheckerPlay play = null;
	public MoveCheck check = null;
	public DoubleCapture doubleCapture = null;
	
	private boolean isLocked = false;
	
	//Bitmaps for the checkers background
	private Bitmap bmpWhite = null;
	private Bitmap bmpBlack = null;
	private Bitmap bmp_player1 = null;
	private Bitmap bmp_player2 = null;	
	private Bitmap bmp_player1_king = null;
	private Bitmap bmp_player2_king = null;	
	private Bitmap bmp_box_sel = null;
	private int boxWidth = 0;	
	private int pieceWidth = 0;
	
	//Checkers game rendering and control
	public CheckerBoard checker = null;
	
	public Engine(Display display){
		this.display = display;		
		this.main = display.getMain();
	}	
	
	public void initUI(){
		
		/*
		 * 64 : Boxes
		 * 24 : Pieces
		 * 88 : total items in the form
		 */
		
		frmMain = new Form(this, 88);
		
		//Calculate width of a single box
		if(getHeight()>getWidth()){
			boxWidth = getWidth()/8;
		}
		else{
			boxWidth = getHeight()/8;
		}		
		
		boxWidth = (int) (0.9f * boxWidth);
		pieceWidth = (int) (0.7f * boxWidth);
		
		
		bmpWhite = Pic.resizeBitmap(this, R.drawable.ic_silver, boxWidth, 500);
		bmpBlack = Pic.resizeBitmap(this, R.drawable.ic_black, boxWidth, 500);
		bmp_box_sel = Pic.resizeBitmap(this, R.drawable.ic_box_sel, boxWidth, 500);
		
		bmp_player1 = Pic.resizeBitmap(this, R.drawable.ic_player1, pieceWidth, 500);
		bmp_player2 = Pic.resizeBitmap(this, R.drawable.ic_player2, pieceWidth, 500);	
		bmp_player1_king = Pic.resizeBitmap(this, R.drawable.ic_player1_king, pieceWidth, 500);
		bmp_player2_king = Pic.resizeBitmap(this, R.drawable.ic_player2_king, pieceWidth, 500);
		
		
		checker = new CheckerBoard(this, frmMain);		
		pieceDrag = new PieceDrag(checker);
		pieceMove = new PieceMove(this);
		logic = new CheckerLogic(this);
		pos = new CheckerPos(checker.getBoxes());
		play = new CheckerPlay(this);
		check = new MoveCheck(this);
		doubleCapture = new DoubleCapture(this);
	}
	
	public void setSize(int width, int height){
		this.WIDTH = width;
		this.HEIGHT = height;
		
	}	
	
	public Bitmap getBmpWhite(){
		return bmpWhite;
	}
	
	public Bitmap getBmpBlack(){
		return bmpBlack;
	}
	
	public Bitmap getBmpPlayer1(){
		return bmp_player1;
	}
	
	public Bitmap getBmpPlayer2(){
		return bmp_player2;
	}
	
	public Bitmap getBmpPlayer1King(){
		return bmp_player1_king;
	}
	
	public Bitmap getBmpPlayer2King(){
		return bmp_player2_king;
	}
	
	public Bitmap getBmpBoxSel(){
		return bmp_box_sel;
	}
	
	
	public int getWidth(){
		return WIDTH;
	}	
	
	public int getHeight(){
		return HEIGHT;
	}
	
	public int getPieceWidth(){
		return this.pieceWidth;
	}
	
	public int getBoxWidth(){
		return this.boxWidth;
	}
	
	public Context getMain(){
		return main;
	}
	
	public PieceDrag getPieceDrag(){
		return this.pieceDrag;
	}
	
	public Display getDisplay(){
		return display;
	}
	
	public void repaint(){
		if(display.isCreated()){
			display.repaint();
		}
	}
	
	public void back(){
		
	}
	
	//Draw the interface on to the display called by SurfaceView(Display)
	public void draw(Canvas canvas){
		if(!frmMain.equals(null))
			frmMain.draw(canvas);
	}

	@Override
	public void pressed(int x, int y) {		
		if(frmMain != null && !isLocked)
			frmMain.pressed(x, y);
	}

	@Override
	public void released(int x, int y) {
		if(frmMain != null && !isLocked)
			frmMain.released(x, y);
	}

	@Override
	public void dragged(int x, int y) {
		if(frmMain != null && !isLocked)
			frmMain.dragged(x, y);		
	}
	
	public void alert(String alert){
		Toast.makeText(getMain(), alert, Toast.LENGTH_SHORT).show();
	}
	
	public void lockInput(boolean isLocked){
		this.isLocked = isLocked;
	}
	
	public boolean isLocked(){
		return this.isLocked;
	}	
}
