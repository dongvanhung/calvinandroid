package com.rent.handler;

import org.json.JSONArray;
import org.json.JSONObject;

import android.os.Handler;
import android.os.Message;

import com.rent.listener.AddressToLocationListener;

public class AddressToLocationHandler extends Handler{

	private AddressToLocationListener mListener;

	  public AddressToLocationHandler(AddressToLocationListener paramAddressToLocationListener)
	  {
	    this.mListener = paramAddressToLocationListener;
	  }

	  public void handleMessage(Message paramMessage)
	  {
	    super.handleMessage(paramMessage);
	    String str = paramMessage.getData().getString("data");
	    try
	    {
	      JSONArray localJSONArray = new JSONObject(str).getJSONArray("Placemark").getJSONObject(0).getJSONObject("Point").getJSONArray("coordinates");
	      AddressToLocationListener localAddressToLocationListener1 = this.mListener;
	      double d1 = localJSONArray.getDouble(1);
	      double d2 = localJSONArray.getDouble(0);
	      localAddressToLocationListener1.locationObtained(d1, d2, true);
	    }
	    catch (Exception localException)
	    {
	        AddressToLocationListener localAddressToLocationListener2 = this.mListener;
	        double d3 = 0.0D;
	        double d4 = 0.0D;
	        localAddressToLocationListener2.locationObtained(d3, d4, false);
	    }
	  }	  
}
