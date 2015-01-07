package com.chekaz.game.logic;

import android.util.Log;

import com.chekaz.game.Engine;
import com.chekaz.game.controls.Box;
import com.chekaz.game.controls.Piece;

public class MoveCheck {
	
	private Engine engine;
	
	public final int DIR_TOP_LEFT = 1;
	public final int DIR_TOP_RIGHT = 2;
	public final int DIR_BOTTOM_RIGHT = 3;
	public final int DIR_BOTTOM_LEFT = 4;
		
	public MoveCheck(Engine engine){
		this.engine = engine;
	}
	
	public void flashBoxesMove(Box box){
		
		Piece piece = box.getPiece();
		
		Box topLeft = engine.pos.getTopLeft(box, 1);
		Box topRight = engine.pos.getTopRight(box, 1);
		Box bottomLeft = engine.pos.getBottomLeft(box, 1);
		Box bottomRight = engine.pos.getBottomRight(box, 1);		
		
		switch(piece.getType()){
		
			case Piece.TYPE_PLAYER1:				
				flashIfEmpty(bottomRight);
				flashIfEmpty(bottomLeft);
				break;
			
			case Piece.TYPE_PLAYER2:				
				flashIfEmpty(topRight);
				flashIfEmpty(topLeft);
				break;
			
			case Piece.TYPE_PLAYER1_KING:	
			case Piece.TYPE_PLAYER2_KING:	
				flashIfEmpty(topRight);
				flashIfEmpty(topLeft);
				flashIfEmpty(bottomRight);
				flashIfEmpty(bottomLeft);
				break;	
		}
		
	}
	
	public void flashIfEmpty(Box box){
		if(box != null){
			if(box.getPiece() == null)
				box.flash();
		}
	}
	
	public boolean canCaptureTopLeft(Box boxCur){	
		
		boolean response = false;
		
		Box boxDest = engine.pos.getTopLeft(boxCur, 2);
		Box boxCapture = engine.pos.getTopLeft(boxCur, 1);
		Piece pieceCur = boxCur.getPiece();
		
		if(boxDest != null	&&	boxCapture != null	&&	pieceCur != null){
			
			Piece pieceCapture = boxCapture.getPiece();
			Piece pieceDest = boxDest.getPiece();
			
			if(pieceCapture != null && pieceDest == null){
				
				int pieceCurType = pieceCur.getType();
				int pieceCaptureType = pieceCapture.getType();
				
				switch(pieceCurType){
				
					case Piece.TYPE_PLAYER2:
					case Piece.TYPE_PLAYER2_KING:	
						if(pieceCaptureType == Piece.TYPE_PLAYER1 || pieceCaptureType == Piece.TYPE_PLAYER1_KING)
							response = true;
						break;
					
					case Piece.TYPE_PLAYER1_KING:	
						if(pieceCaptureType == Piece.TYPE_PLAYER2 || pieceCaptureType == Piece.TYPE_PLAYER2_KING)
							response = true;
						break;	
				}
			}
		}
		
		if(response)
			boxCapture.flash();
		
		return response;
	}
	
	public boolean canCaptureTopRight(Box boxCur){	
		
		boolean response = false;
		
		Box boxDest = engine.pos.getTopRight(boxCur, 2);
		Box boxCapture = engine.pos.getTopRight(boxCur, 1);
		Piece pieceCur = boxCur.getPiece();
		
		if(boxDest != null	&&	boxCapture != null	&&	pieceCur != null){
			
			Piece pieceCapture = boxCapture.getPiece();
			Piece pieceDest = boxDest.getPiece();
			
			if(pieceCapture != null && pieceDest == null){
				
				int pieceCurType = pieceCur.getType();
				int pieceCaptureType = pieceCapture.getType();
				
				switch(pieceCurType){
				
					case Piece.TYPE_PLAYER2:
					case Piece.TYPE_PLAYER2_KING:	
						if(pieceCaptureType == Piece.TYPE_PLAYER1 || pieceCaptureType == Piece.TYPE_PLAYER1_KING)
							response = true;
						break;
					
					case Piece.TYPE_PLAYER1_KING:	
						if(pieceCaptureType == Piece.TYPE_PLAYER2 || pieceCaptureType == Piece.TYPE_PLAYER2_KING)
							response = true;
						break;	
				}
			}
		}
		
		if(response)
			boxCapture.flash();
		
		return response;
	}	
	
	public boolean canCaptureBottomLeft(Box boxCur){	
		
		boolean response = false;
		
		Box boxDest = engine.pos.getBottomLeft(boxCur, 2);
		Box boxCapture = engine.pos.getBottomLeft(boxCur, 1);
		Piece pieceCur = boxCur.getPiece();
		
		if(boxDest != null	&&	boxCapture != null	&&	pieceCur != null){
			
			Piece pieceCapture = boxCapture.getPiece();
			Piece pieceDest = boxDest.getPiece();
			
			if(pieceCapture != null && pieceDest == null){
				
				int pieceCurType = pieceCur.getType();
				int pieceCaptureType = pieceCapture.getType();
				
				switch(pieceCurType){
				
					case Piece.TYPE_PLAYER1:
					case Piece.TYPE_PLAYER1_KING:	
						if(pieceCaptureType == Piece.TYPE_PLAYER2 || pieceCaptureType == Piece.TYPE_PLAYER2_KING)
							response = true;
						break;
					
					case Piece.TYPE_PLAYER2_KING:	
						if(pieceCaptureType == Piece.TYPE_PLAYER1 || pieceCaptureType == Piece.TYPE_PLAYER1_KING)
							response = true;
						break;	
				}
			}
		}
		
		if(response)
			boxCapture.flash();
		
		return response;
	}	
	
	public boolean canCaptureBottomRight(Box boxCur){	
		
		boolean response = false;
		
		Box boxDest = engine.pos.getBottomRight(boxCur, 2);
		Box boxCapture = engine.pos.getBottomRight(boxCur, 1);
		Piece pieceCur = boxCur.getPiece();
		
		if(boxDest != null	&&	boxCapture != null	&&	pieceCur != null){
			
			Piece pieceCapture = boxCapture.getPiece();
			Piece pieceDest = boxDest.getPiece();
			
			if(pieceCapture != null && pieceDest == null){
				
				int pieceCurType = pieceCur.getType();
				int pieceCaptureType = pieceCapture.getType();
				
				switch(pieceCurType){
				
					case Piece.TYPE_PLAYER1:
					case Piece.TYPE_PLAYER1_KING:	
						if(pieceCaptureType == Piece.TYPE_PLAYER2 || pieceCaptureType == Piece.TYPE_PLAYER2_KING)
							response = true;
						break;
					
					case Piece.TYPE_PLAYER2_KING:	
						if(pieceCaptureType == Piece.TYPE_PLAYER1 || pieceCaptureType == Piece.TYPE_PLAYER1_KING)
							response = true;
						break;	
				}
			}
		}
		
		if(response)
			boxCapture.flash();
		
		return response;
	}	
	
	public boolean canCapture(Box box){
				
		boolean response = false;
		
		boolean blTopLeft	  = false;
		boolean blTopRight	  = false;
		boolean blBottomLeft  = false;	
		boolean blBottomRight = false;
		
		
		switch(box.getPiece().getType()){
			case Piece.TYPE_PLAYER1:
				blBottomLeft  = canCaptureBottomLeft(box);	
				blBottomRight = canCaptureBottomRight(box);
				break;
			
			case Piece.TYPE_PLAYER2:
				blTopLeft	  = canCaptureTopLeft(box);	
				blTopRight	  = canCaptureTopRight(box);
				break;	
			
			case Piece.TYPE_PLAYER1_KING:
			case Piece.TYPE_PLAYER2_KING:
				blTopLeft	  = canCaptureTopLeft(box);	
				blTopRight	  = canCaptureTopRight(box);
				blBottomLeft  = canCaptureBottomLeft(box);	
				blBottomRight = canCaptureBottomRight(box);
				break;				
		}
		
				
		if(blTopLeft || blTopRight || blBottomLeft || blBottomRight)
			response = true;
		
		return response;
	} 
	
	public boolean canCaptureP1(){
		
		boolean response = false;
		Piece pieces[] = engine.checker.getPieces();
		
		for(int i=0 ; i<24 ; ++i){			
			Piece piece = pieces[i];
			
			if(piece.getType() == Piece.TYPE_PLAYER1 || piece.getType() == Piece.TYPE_PLAYER1_KING)
				if(canCapture(piece.getBox()))
					response = true;
		}
		
		
		return response;
	}
	
	public boolean canCaptureP2(){
		
		boolean response = false;
		Piece pieces[] = engine.checker.getPieces();
		
		for(int i=0 ; i<24 ; ++i){			
			Piece piece = pieces[i];
			
			if(piece.getType() == Piece.TYPE_PLAYER2 || piece.getType() == Piece.TYPE_PLAYER2_KING)
				if(canCapture(piece.getBox())){
					response = true;
				
				}	
		}
		
		return response;
	}
	
	public boolean canCapture(int turn){
		
		boolean response = false;
		
		switch(turn){
			case CheckerPlay.PLAYER_1:
				response = canCaptureP1();
				break;
				
			case CheckerPlay.PLAYER_2:	
				response = canCaptureP2();
				break;
		}	
		
		return response;
	}
	
	
}
