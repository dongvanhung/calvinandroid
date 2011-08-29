package com.rent.thread;

import android.os.Message;

import com.rent.handler.TimerHandler;

public class TimerThread extends Thread{

	private TimerHandler mHandler;
	  private int mTime;

	  public TimerThread(int paramInt, TimerHandler paramTimerHandler)
	  {
	    this.mTime = paramInt;
	    this.mHandler = paramTimerHandler;
	  }

	  public void run()
	  {
	    try
	    {
	      sleep(this.mTime * 1000);
	      TimerHandler localTimerHandler = this.mHandler;
	      Message localMessage = new Message();
	      boolean bool = localTimerHandler.sendMessage(localMessage);
	    }
	    catch (InterruptedException localInterruptedException)
	    {
	        localInterruptedException.printStackTrace();
	    }
	  }
	  
}
