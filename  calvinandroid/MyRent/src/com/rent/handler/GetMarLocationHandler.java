package com.rent.handler;

import org.json.JSONObject;

import android.os.Handler;
import android.os.Message;

import com.rent.listener.GetMarsLocationListener;

public class GetMarLocationHandler extends Handler{
	private GetMarsLocationListener mListener;

	  public GetMarLocationHandler(GetMarsLocationListener paramGetMarsLocationListener)
	  {
	    this.mListener = paramGetMarsLocationListener;
	  }

	  public void handleMessage(Message paramMessage)
	  {
	    super.handleMessage(paramMessage);
	    if (paramMessage.what == 1)
	    {
	      GetMarsLocationListener localGetMarsLocationListener1 = this.mListener;
	      double d1 = 0.0D;
	      localGetMarsLocationListener1.marsLocationObtained(0.0D, d1, false);
	    }
	    while (true)
	    {
	      try
	      {
	        String str = paramMessage.getData().getString("data");
	        JSONObject localJSONObject = new JSONObject(str).getJSONObject("data");
	        long l1 = localJSONObject.getLong("latitude");
	        long l2 = localJSONObject.getLong("longitude");
	        GetMarsLocationListener localGetMarsLocationListener2 = this.mListener;
	        double d2 = l1 / 100000.0D;
	        double d3 = l2 / 100000.0D;
	        localGetMarsLocationListener2.marsLocationObtained(d2, d3, true);
	      }
	      catch (Exception localException)
	      {
	        GetMarsLocationListener localGetMarsLocationListener3 = this.mListener;
	        double d4 = 0.0D;
	        localGetMarsLocationListener3.marsLocationObtained(0.0D, d4, false);
	      }
	    }
	  }
	  
}
