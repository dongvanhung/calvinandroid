package com.rent.handler;

import android.content.Context;
import android.os.Handler;

import com.rent.listener.AddressFromLocationListener;

public class AddressFromLocationHandler extends Handler{

	private Context mContext;
	  private AddressFromLocationListener mListener;

	  public AddressFromLocationHandler(AddressFromLocationListener paramAddressFromLocationListener, Context paramContext)
	  {
	    this.mListener = paramAddressFromLocationListener;
	    this.mContext = paramContext;
	  }

	  // ERROR //
	  public void handleMessage(android.os.Message paramMessage)
	  {
		  
	  }
}
