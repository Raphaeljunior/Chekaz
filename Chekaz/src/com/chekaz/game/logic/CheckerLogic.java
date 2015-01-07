package com.chekaz.game.logic;

import com.chekaz.game.Engine;
import com.chekaz.game.controls.Box;
import com.chekaz.game.controls.Piece;
import com.chekaz.game.ui.Control;
import com.chekaz.util.Action;

/*
 * Class to take care of all logic in the game
 */

public class CheckerLogic {
	
	private Engine engine = null;
	private Piece curPiece = null; 
	private Piece selPiece = null;
	private Box selBox1 = null;	
	private Box selBox2 = null;
	
	
	public CheckerLogic(Engine engine) {
		this.engine = engine;
	}

	public void setCurPiece(Piece piece){
		this.curPiece = piece;		
		engine.frmMain.bringToTop(piece);			
	}
	
	public Piece getCurPiece(){
		return this.curPiece;
	}
	
	public void setSelPiece(Piece piece){
		
		engine.checker.unselectAllBoxes();
		
		
		this.selPiece = piece;
		this.selBox1 = piece.getBox();		
		this.selBox1.setSelected(true);
		
		engine.repaint();		
	}
	
	public void destroyAll(){
		this.curPiece = null; 
		this.selPiece = null;
		this.selBox1 = null;	
		this.selBox2 = null;
	}
	
	public Piece getSelPiece(){
		return this.selPiece;
	}
	
	public Box getSelBox1(){
		return this.selBox1;
	}
	
	public void setSelBox2(Box box){
		if(this.selBox2 != null){
			this.selBox2.setSelected(false);
		}
		
		this.selBox2 = box;
		this.selBox2.setSelected(true);
		
		engine.repaint();
	}
	
	public Box getSelBox2(){
		return this.selBox2;
	}
	
	public void movePiece(){
		
		int playResponse = engine.play.play(selBox1, selBox2); 
		
		if(playResponse == CheckerPlay.NOERR){	
			
			Piece selPiece = getSelPiece();
			int destX = selBox2.getLeft() + engine.getBmpBlack().getWidth()/2 - engine.getBmpPlayer1().getWidth()/2;
			int destY = selBox2.getTop() + engine.getBmpBlack().getWidth()/2 - engine.getBmpPlayer1().getWidth()/2;
			
			engine.pieceMove.movePiece(selPiece, destX, destY, new Action() {
				
				@Override
				public void action() {
					updateLogic();
					engine.play.setTurn();
				}
			});
		}
		else{
			
			selBox2.setSelected(false);
			
			if(playResponse != CheckerPlay.ERR_MISSCAPTURE){				
				if(!engine.check.canCapture(engine.play.getTurn())){
					engine.check.flashBoxesMove(selBox1);
				}				
			}
			
			engine.repaint();
		}
	}
	
	public void updateLogic(){
				
		getSelBox1().setPiece(null);		
		getSelPiece().setBox(selBox2);		
		
		this.selBox1 = null;
		this.selPiece = null;		
		engine.repaint();
		
		/*
		 * Remember to check first before double eating if their was a first capture
		 * Change the code below 
		 */
		
		engine.doubleCapture.checkDoubleCapture(selBox2);		
		
	}
	
	public void returnPiece(Piece piece){		
		Box box = piece.getBox();
		
		int destX = box.getLeft() + engine.getBmpBlack().getWidth()/2 - engine.getBmpPlayer1().getWidth()/2;
		int destY = box.getTop() + engine.getBmpBlack().getWidth()/2 - engine.getBmpPlayer1().getWidth()/2;
		
		engine.pieceMove.movePiece(piece, destX, destY, null);
	}
	
	public void movePiece(Piece piece, Box box, Action action){			
		
		int destX = box.getLeft() + engine.getBmpBlack().getWidth()/2 - engine.getBmpPlayer1().getWidth()/2;
		int destY = box.getTop() + engine.getBmpBlack().getWidth()/2 - engine.getBmpPlayer1().getWidth()/2;
		
		engine.pieceMove.movePiece(piece, destX, destY, action);
	}
	
	public void dropPiece(){
		Piece piece = getCurPiece();
		Box box = piece.getBox();
		
		int midX = piece.getLeft() + engine.getBmpPlayer1().getWidth()/2;
		int midY = piece.getTop() + engine.getBmpPlayer1().getHeight()/2;
		
		Control control = engine.frmMain.getControls().getdroppedBox(midX, midY);
		
		if(control == null || !engine.play.isTurn(piece)){
			returnPiece(piece);
		}	
		else{			
			final Box box2 = (Box) control;
			if(box2.equals(box) || box2.getType() == Box.TYPE_WHITE || box2.getPiece() != null){
				if(!engine.check.canCapture(engine.play.getTurn())){
					engine.check.flashBoxesMove(selBox1);
				}
				returnPiece(piece);
			}			
			else{ 
				int playResponse = engine.play.play(box, box2);
				if( playResponse == CheckerPlay.NOERR){				
				
					movePiece(piece, box2, new Action() {
						
						@Override
						public void action() {
							setSelBox2(box2);
							updateLogic();	
							engine.play.setTurn();
						}
					});				
				}
				else{		
					
					if(playResponse != CheckerPlay.ERR_MISSCAPTURE){				
						if(!engine.check.canCapture(engine.play.getTurn())){
							engine.check.flashBoxesMove(selBox1);
						}				
					}
					
					returnPiece(piece);
				}
			}	
		}
	}
	
	
}
