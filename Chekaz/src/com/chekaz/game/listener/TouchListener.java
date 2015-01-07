package com.chekaz.game.listener;

public interface TouchListener {
	void pressed(int x, int y);
	void released(int x, int y);
	void dragged(int x, int y);	
}
