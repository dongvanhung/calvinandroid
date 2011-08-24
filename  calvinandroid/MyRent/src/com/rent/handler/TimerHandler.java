package com.rent.handler;

import android.os.Handler;
import android.os.Message;

import com.rent.listener.TimerListener;

public class TimerHandler extends Handler{

	TimerListener mListener;

	  public TimerHandler(TimerListener paramTimerListener)
	  {
	    this.mListener = paramTimerListener;
	  }

	  public void handleMessage(Message paramMessage)
	  {
	    super.handleMessage(paramMessage);
	    this.mListener.timeOut();
	  }
	  
}
