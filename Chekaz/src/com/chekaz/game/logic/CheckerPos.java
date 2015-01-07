package com.chekaz.game.logic;

import com.chekaz.game.controls.Box;
import com.chekaz.game.controls.Piece;

public class CheckerPos {
	private Box[][] boxes = null;
	
	public CheckerPos(Box[][] boxes){
		this.boxes = boxes;
	}
	
	public Box getBottomRight(Box box ,int level){
		
		int row = box.getRow();
		int col = box.getCol();
		
		row += level;
		col += level;
		
		if( (row > -1 && row < 8) && (col > -1 && col < 8) ){
			return this.boxes[row][col];
		}
		else{
			return null;
		}
		
	}
	
	public Box getBottomLeft(Box box ,int level){
		
		int row = box.getRow();
		int col = box.getCol();
		
		row += level;
		col -= level;
		
		if( (row > -1 && row < 8) && (col > -1 && col < 8) ){
			return this.boxes[row][col];
		}
		else{
			return null;
		}		
	}
	
	public Box getTopRight(Box box ,int level){
		
		int row = box.getRow();
		int col = box.getCol();
		
		row -= level;
		col += level;
		
		if( (row > -1 && row < 8) && (col > -1 && col < 8) ){
			return this.boxes[row][col];
		}
		else{
			return null;
		}
		
	}
	
	public Box getTopLeft(Box box ,int level){
		
		int row = box.getRow();
		int col = box.getCol();
		
		row -= level;
		col -= level;
		
		if( (row > -1 && row < 8) && (col > -1 && col < 8) ){
			return this.boxes[row][col];
		}
		else{
			return null;
		}		
	}
	
	
	public Box getTop(Box box, int level, Box destBox){
		
		Box boxLeft = getTopLeft(box, level);
		Box boxRight = getTopRight(box, level);
		
		if(boxLeft == destBox)
			return getTopLeft(box, level - 1);
		
		else if(boxRight == destBox)
			return getTopRight(box, level - 1);
		
		else
			return null;
	}
	
	public Box getBottom(Box box, int level, Box destBox){
		
		Box boxLeft = getBottomLeft(box, level);
		Box boxRight = getBottomRight(box, level);
		
		if(boxLeft == destBox)
			return getBottomLeft(box, level - 1);
		
		else if(boxRight == destBox)
			return getBottomRight(box, level - 1);
		
		else
			return null;
	}
	
	public Box getKing(Box box, int level, Box destBox){
		Box boxTop = getTop(box, level, destBox);
		Box boxBottom = getBottom(box, level, destBox);
		
		if(boxTop != null)
			return boxTop;
		
		else if(boxBottom != null)
			return boxBottom;
		
		else
			return null;
	}
	
	
	
	
}
