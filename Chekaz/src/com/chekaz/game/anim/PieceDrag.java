package com.chekaz.game.anim;

import com.chekaz.game.controls.CheckerBoard;
import com.chekaz.game.controls.Piece;

public class PieceDrag {
	private CheckerBoard checker = null;
	
	public PieceDrag(CheckerBoard checker){
		this.checker = checker;
	}
	
	public void drag(Piece piece, int x, int y){	
		
		int newX = x - piece.getWidth()/2;
		int newY = y - piece.getHeight()/2;
		piece.setPos(newX, newY);
		
		//update the screen
		this.checker.repaint();
	}
}
