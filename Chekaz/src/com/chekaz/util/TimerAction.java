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
public class TimerAction extends TimerTask{
    Action a=null;    
    public TimerAction(Action action,int delay){
        a = action;
        Timer t = new Timer();
        t.schedule(this, delay,1);
    }

    public void run() {
        if(a!=null){
            a.action();
        }
        this.cancel();
    }
}
