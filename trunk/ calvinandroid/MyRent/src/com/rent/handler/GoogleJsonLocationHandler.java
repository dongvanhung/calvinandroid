package com.rent.handler;

import org.json.JSONObject;

import android.os.Handler;
import android.os.Message;

import com.rent.listener.GoogleJSonLocationListener;

public class GoogleJsonLocationHandler extends Handler {

	private GoogleJSonLocationListener mListener;

	  public GoogleJsonLocationHandler(GoogleJSonLocationListener paramGoogleJSonLocationListener)
	  {
	    this.mListener = paramGoogleJSonLocationListener;
	  }

	  public void handleMessage(Message paramMessage)
	  {
	    super.handleMessage(paramMessage);
	    if (paramMessage.what == 1) {
	      this.mListener.locationObtained(0.0D, 0.0D, 0.0D, false, 0);
	    } else {
	      try
	      {
	        String str = paramMessage.getData().getString("data");
	        JSONObject localJSONObject = new JSONObject(str).getJSONObject("location");
	        double d1 = localJSONObject.getDouble("latitude");
	        double d2 = localJSONObject.getDouble("longitude");
	        double d3 = localJSONObject.getDouble("accuracy");
	        this.mListener.locationObtained(d1, d2, d3, true, 0);
	      }
	      catch (Exception localException)
	      {
	        this.mListener.locationObtained(0.0D, 0.0D, 0.0D, false, 0);
	      }
	    }
	  }
	  
}
