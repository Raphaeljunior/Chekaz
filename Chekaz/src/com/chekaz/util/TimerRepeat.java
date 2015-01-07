/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chekaz.util;

import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author Keysindicet
 */
public class TimerRepeat extends TimerTask{
    private Action a=null; 
    private Action afterRepeat = null;
    private int noTimes = 0;
    private int repeatTimes;
    
    public TimerRepeat(int repeatTimes, int delay, Action actionRepeated, Action afterReapeat){
    	this.repeatTimes = repeatTimes;
        this.a = actionRepeated;
        this.afterRepeat = afterReapeat;
        Timer t = new Timer();
        t.schedule(this, 0, delay);
    
    }

    public void run() {
        if(a!=null){
            a.action();
        }
        ++noTimes;
        
        if(noTimes >= repeatTimes){
        	if(afterRepeat != null)
        		afterRepeat.action();
        	
        	this.cancel();
        }        
    }
}
