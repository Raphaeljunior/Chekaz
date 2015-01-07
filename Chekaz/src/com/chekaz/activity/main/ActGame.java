package com.chekaz.activity.main;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;

import com.chekaz.game.Display;
import com.keysindicet.chekaz.R;

public class ActGame extends ActionBarActivity {
	
	private Display display;
	private LinearLayout llContent;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_game);
		ActionBar action = getSupportActionBar();
		action.setBackgroundDrawable(getResources().getDrawable(R.drawable.bg_topbar));
		
		llContent = (LinearLayout) findViewById(R.id.llContent);
		display = new Display(this);
		display.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		llContent.addView(display);
		
	}
}
