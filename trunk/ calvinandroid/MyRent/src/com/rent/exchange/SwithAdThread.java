package com.rent.exchange;

import android.os.Handler;
import android.os.Message;

import com.rent.exchange.common.ExchangeConstants;

public class SwithAdThread extends Thread{

	public SwitchAdListener mSwitchListener;
	  final Handler switchAdHandler;

	  public SwithAdThread(SwitchAdListener paramSwitchAdListener)
	  {
		  SwithAdThread1 local1 = new SwithAdThread1(this);
	    this.switchAdHandler = local1;
	    this.mSwitchListener = paramSwitchAdListener;
	  }
	  
	  class SwithAdThread1 extends Handler
	  {
		  private SwithAdThread st;
		  public SwithAdThread1(SwithAdThread st) {
			  this.st = st;
		  }
	    public void handleMessage(Message paramMessage)
	    {
	      st.mSwitchListener.timeup();
	    }
	  }
	  
	  public void run()
	  {
	    try
	    {
	      Thread.sleep(ExchangeConstants.REFRESH_INTERVAL);
	      boolean bool = this.switchAdHandler.sendEmptyMessage(0);
	      return;
	    }
	    catch (InterruptedException localInterruptedException)
	    {
	      while (true)
	        localInterruptedException.printStackTrace();
	    }
	  }
}
