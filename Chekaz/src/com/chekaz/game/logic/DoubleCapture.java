package com.chekaz.game.logic;

import com.chekaz.game.Engine;
import com.chekaz.game.controls.Box;
import com.chekaz.game.controls.Piece;
import com.chekaz.util.Action;

public class DoubleCapture {
	
	private Engine engine = null;
	public DoubleCapture(Engine engine){
		this.engine = engine;
	}
	
	public void checkDoubleCapture(Box box){
		Piece piece = box.getPiece();
		
		switch(piece.getType()){
			case Piece.TYPE_PLAYER1:
				checkP1(box);
				break;
				
			case Piece.TYPE_PLAYER2:
				checkP2(box);
				break;
			
			case Piece.TYPE_PLAYER1_KING:
			case Piece.TYPE_PLAYER2_KING:	
				checkKing(box);
				break;	
		}
		
	}
	
	public void checkP1(Box box){
		
		if(engine.check.canCaptureBottomLeft(box)){
			Box bottomLeft2 = engine.pos.getBottomLeft(box, 2);
			final Box bottomLeft1 = engine.pos.getBottomLeft(box, 1);
			
			engine.pieceMove.movePiece(box, bottomLeft2, new Action() {
				
				@Override
				public void action() {
					engine.play.capturePiece(bottomLeft1);
				}
			});
		}
		else if(engine.check.canCaptureBottomRight(box)){
			Box bottomRight2 = engine.pos.getBottomRight(box, 2);
			final Box bottomRight1 = engine.pos.getBottomRight(box, 1);
			
			engine.pieceMove.movePiece(box, bottomRight2, new Action() {
				
				@Override
				public void action() {
					engine.play.capturePiece(bottomRight1);
				}
			});
		}
	}
	
	public void checkP2(Box box){
		
		if(engine.check.canCaptureTopLeft(box)){
			Box topLeft2 = engine.pos.getTopLeft(box, 2);
			final Box topLeft1 = engine.pos.getTopLeft(box, 1);
			
			engine.pieceMove.movePiece(box, topLeft2, new Action() {
				
				@Override
				public void action() {
					engine.play.capturePiece(topLeft1);
				}
			});
		}
		else if(engine.check.canCaptureTopRight(box)){
			Box topRight2 = engine.pos.getTopRight(box, 2);
			final Box topRight1 = engine.pos.getTopRight(box, 1);
			
			engine.pieceMove.movePiece(box, topRight2, new Action() {
				
				@Override
				public void action() {
					engine.play.capturePiece(topRight1);
				}
			});
		}
	}
	
	public void checkKing(Box box){
		
		if(engine.check.canCaptureBottomLeft(box)){
			Box bottomLeft2 = engine.pos.getBottomLeft(box, 2);
			final Box bottomLeft1 = engine.pos.getBottomLeft(box, 1);
			
			engine.pieceMove.movePiece(box, bottomLeft2, new Action() {
				
				@Override
				public void action() {
					engine.play.capturePiece(bottomLeft1);
				}
			});
		}
		else if(engine.check.canCaptureBottomRight(box)){
			Box bottomRight2 = engine.pos.getBottomRight(box, 2);
			final Box bottomRight1 = engine.pos.getBottomRight(box, 1);
			
			engine.pieceMove.movePiece(box, bottomRight2, new Action() {
				
				@Override
				public void action() {
					engine.play.capturePiece(bottomRight1);
				}
			});
		}
		else if(engine.check.canCaptureTopLeft(box)){
			Box topLeft2 = engine.pos.getTopLeft(box, 2);
			final Box topLeft1 = engine.pos.getTopLeft(box, 1);
			
			engine.pieceMove.movePiece(box, topLeft2, new Action() {
				
				@Override
				public void action() {
					engine.play.capturePiece(topLeft1);
				}
			});
		}
		else if(engine.check.canCaptureTopRight(box)){
			Box topRight2 = engine.pos.getTopRight(box, 2);
			final Box topRight1 = engine.pos.getTopRight(box, 1);
			
			engine.pieceMove.movePiece(box, topRight2, new Action() {
				
				@Override
				public void action() {
					engine.play.capturePiece(topRight1);
				}
			});
		}
	}
}
