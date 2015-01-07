package com.chekaz.game.logic;

import com.chekaz.game.Engine;
import com.chekaz.game.controls.Box;
import com.chekaz.game.controls.Piece;

public class CheckerPlay {
	
	private Engine engine = null;
	
	private int turn = 0;
	public final static int PLAYER_1 = 1;
	public final static int PLAYER_2 = 2;
	
	public final static int NOERR = 0;
	public final static int ERR_TURN = 1;
	public final static int ERR_DEST = 2;
	public final static int ERR_MISSCAPTURE = 3;
	
	
	
	private Box[][] boxes = null;
	
	public CheckerPlay(Engine engine){
		this.engine = engine;
		this.boxes = engine.checker.getBoxes();
		//Initialize the game turn
		this.turn = PLAYER_1;
	}
	
	public void setTurn(){
		this.turn = (turn == PLAYER_1 ? PLAYER_2 : PLAYER_1);
	}
	
	public void setTurn(int turn){
		this.turn = turn;
	}
	
	public int getTurn(){
		return this.turn;
	}
	
	public boolean isTurn(Piece piece){
		
		if(this.turn == PLAYER_1){
			if(		piece.getType() == Piece.TYPE_PLAYER1 
				||	piece.getType() == Piece.TYPE_PLAYER1_KING){
				
				return true;
			}
			
			return false;				
		}
		else if(this.turn == PLAYER_2){
			if(		piece.getType() == Piece.TYPE_PLAYER2
					||	piece.getType() == Piece.TYPE_PLAYER2_KING){
					
				return true;
			}
			
			return false;
		}
		else{
			return false;
		}
		
	}
	
	public int play(Box curBox, Box destBox){
		Piece curPiece = curBox.getPiece();
		
		switch(curPiece.getType()){
			case Piece.TYPE_PLAYER1:
				return playP1(curBox, destBox);
			
			case Piece.TYPE_PLAYER2:
				return playP2(curBox, destBox);	
				
			case Piece.TYPE_PLAYER1_KING:
				return playP1King(curBox, destBox);
			
			case Piece.TYPE_PLAYER2_KING:
				return playP2King(curBox, destBox);	
			
			default:
				return -1;
			
		}
		
	}
		
	public int playP1(Box curBox, Box destBox){
		
		if(engine.pos.getBottom(curBox, 1, destBox) != null){
			
			if(engine.check.canCaptureP1()){
				return ERR_MISSCAPTURE;
			}
			else{
				if(destBox.getRow() == 7){
					curBox.getPiece().setType(Piece.TYPE_PLAYER1_KING);
					engine.repaint();
				}
				return NOERR;
			}
		}
		else{
			Box box = engine.pos.getBottom(curBox, 2, destBox);
			
			if(box != null){
				Piece piece = box.getPiece();
				
				if(piece != null){
					
					if(piece.getType() == Piece.TYPE_PLAYER2 || piece.getType() == Piece.TYPE_PLAYER2_KING){
						
						capturePiece(box);
						
						if(destBox.getRow() == 7){
							curBox.getPiece().setType(Piece.TYPE_PLAYER1_KING);						
						}
						
						engine.repaint();
						return NOERR;
					}
					else{
						return ERR_DEST;
					}
						
					
				}	
				else{
					return ERR_DEST;
				}	
			}
			else{
				return ERR_DEST;
			}			
		}		
	}
	
	public int playP2(Box curBox, Box destBox){
		
		if(engine.pos.getTop(curBox, 1, destBox) != null){
			if(engine.check.canCaptureP2()){
				return ERR_MISSCAPTURE;
			}
			else{
				if(destBox.getRow() == 0){
					curBox.getPiece().setType(Piece.TYPE_PLAYER2_KING);
					engine.repaint();
				}			
				return NOERR;
			}
		}
		else{
			Box box = engine.pos.getTop(curBox, 2, destBox);
			
			if(box != null){
				Piece piece = box.getPiece();
				
				if(piece != null){
					
					if(piece.getType() == Piece.TYPE_PLAYER1 || piece.getType() == Piece.TYPE_PLAYER1_KING){
						capturePiece(box);
						
						if(destBox.getRow() == 0){
							curBox.getPiece().setType(Piece.TYPE_PLAYER2_KING);						
						}
						
						engine.repaint();
						return NOERR;
					}
					else{
						return ERR_DEST;
					}
					
				}	
				else{
					return ERR_DEST;
				}	
			}
			else{
				return ERR_DEST;
			}			
		}
		
	}	
	
	public int playP1King(Box curBox, Box destBox){
		
		if(engine.pos.getKing(curBox, 1, destBox) != null){		
			if(engine.check.canCaptureP1())
				return ERR_MISSCAPTURE;			
			else
				return NOERR;
		}
		else{
			Box box = engine.pos.getKing(curBox, 2, destBox);
			
			if(box != null){
				Piece piece = box.getPiece();
				
				if(piece != null){
					
					if(piece.getType() == Piece.TYPE_PLAYER2 || piece.getType() == Piece.TYPE_PLAYER2_KING){					
						capturePiece(box);
						return NOERR;
					}
					else{
						return ERR_DEST;
					}
					
				}	
				else{
					return ERR_DEST;
				}	
			}
			else{
				return ERR_DEST;
			}			
		}		
	}
	
	public int playP2King(Box curBox, Box destBox){
		
		if(engine.pos.getKing(curBox, 1, destBox) != null){	
			if(engine.check.canCaptureP2())
				return ERR_MISSCAPTURE;
			else			
				return NOERR;
		}
		else{
			Box box = engine.pos.getKing(curBox, 2, destBox);
			
			if(box != null){
				Piece piece = box.getPiece();
				
				if(piece != null){
					
					if(piece.getType() == Piece.TYPE_PLAYER1 || piece.getType() == Piece.TYPE_PLAYER1_KING){					
						capturePiece(box);
						return NOERR;
					}
					else{
						return ERR_DEST;
					}
					
				}	
				else{
					return ERR_DEST;
				}	
			}
			else{
				return ERR_DEST;
			}			
		}		
	}
	
	public void capturePiece(Box box){
		box.getPiece().setType(Piece.TYPE_EMPTY);
		box.setPiece(null);										
		engine.repaint();
	}
}
