package com.chekaz.game.anim;

import java.util.Timer;
import java.util.TimerTask;

import com.chekaz.game.Engine;
import com.chekaz.game.controls.Box;
import com.chekaz.game.controls.Piece;
import com.chekaz.util.Action;

public class PieceMove {
	private Engine engine = null;
	public PieceMove(Engine engine){
		this.engine = engine;
	}
	
	public void movePiece(Piece piece, int destX, int destY, Action onFinish){	
		
		int frameCount = 10;		
		TimerMove m = new TimerMove(piece, frameCount,destX, destY, onFinish);
	}
	
	public Engine getEngine(){
		return this.engine;
	}
	
	public void movePiece(final Box curBox, final Box destBox, final Action action){
		
		engine.logic.destroyAll();
		
		curBox.setSelected(true);
		final Piece piece = curBox.getPiece();	
		
		int destX = destBox.getLeft() + engine.getBmpBlack().getWidth()/2 - engine.getBmpPlayer1().getWidth()/2;
		int destY = destBox.getTop() + engine.getBmpBlack().getWidth()/2 - engine.getBmpPlayer1().getWidth()/2;
		
		movePiece(piece, destX, destY, new Action() {
			
			@Override
			public void action() {
				piece.setBox(destBox);
				curBox.setPiece(null);				
				destBox.setSelected(true);		
				engine.repaint();
				
				if(action != null)
					action.action();
				
			}
		});
	}
}

class TimerMove extends TimerTask{
	private int frameCount;
	private int count = 0;
	private Piece piece = null;
		
	private int destX = 0;
	private int destY = 0;
	
	private Action onFinish = null;
	
	public TimerMove(Piece piece, int frameCount,int destX, int destY, Action onFinish){
		
		piece.getEngine().lockInput(true);
		
		this.piece = piece;
		this.frameCount = frameCount;
				
		this.destX = destX;
		this.destY = destY;
		
		this.onFinish = onFinish;
		
		Timer t = new Timer();
		t.schedule(this, 0, 20);
	}

	@Override
	public void run() {
		
		int difX = destX - piece.getLeft();
		int difY = destY - piece.getTop();
		
		int distX = difX/(frameCount - count);
		int distY = difY/(frameCount - count);
		
		int newX = piece.getLeft() + distX;
		int newY = piece.getTop() + distY;
				
		piece.setPos(newX, newY);
		piece.getEngine().repaint();
		
		++ count;
		
		if(count == frameCount){	
			if(onFinish != null)
				onFinish.action();
			
			piece.getEngine().lockInput(false);			
			this.cancel();
		}			
	}	
}
