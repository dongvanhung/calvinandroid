package com.rent.exchange.common;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.util.Log;

public class LocationAgent {

	private LocationManager lm;
	  private Context mContext;

	  public LocationAgent(Context paramContext)
	  {
	    this.mContext = paramContext;
	  }

	  public Location getLocation()
	  {
	    try
	    {
	      LocationManager localLocationManager = (LocationManager)this.mContext.getSystemService("location");
	      this.lm = localLocationManager;
	      if (UmengHelper.checkPermission(this.mContext, "android.permission.ACCESS_FINE_LOCATION"))
	      {
	        Location localLocation1 = this.lm.getLastKnownLocation("gps");
	        if (localLocation1 != null)
	        {
	          StringBuilder localStringBuilder1 = new StringBuilder("get location from gps:");
	          double d1 = localLocation1.getLatitude();
	          StringBuilder localStringBuilder2 = localStringBuilder1.append(d1).append(",");
	          double d2 = localLocation1.getLongitude();
	          String str1 = String.valueOf(d2);
	          int i = Log.i("MobclickAgent", str1);
	          return localLocation1;
	        }
	      } else if (UmengHelper.checkPermission(this.mContext, "android.permission.ACCESS_COARSE_LOCATION"))
	        {
	          Location localLocation2 = this.lm.getLastKnownLocation("network");
	          if (localLocation2 != null)
	          {
	            StringBuilder localStringBuilder3 = new StringBuilder("get location from network:");
	            double d3 = localLocation2.getLatitude();
	            StringBuilder localStringBuilder4 = localStringBuilder3.append(d3).append(",");
	            double d4 = localLocation2.getLongitude();
	            String str2 = String.valueOf(d4);
	            int j = Log.i("MobclickAgent", str2);
	            return localLocation2;
	          }
	        } else {
		        int k = Log.i("MobclickAgent", "Could not get location from GPS or Cell-id, lack ACCESS_COARSE_LOCATION or ACCESS_COARSE_LOCATION permission?");
	        }
	    }
	    catch (Exception localException)
	    {
	      while (true)
	      {
	        String str3 = localException.getMessage();
	        int m = Log.e("MobclickAgent", str3);
	        Object localObject = null;
	      }
	    }
	    return null;
	  }
}
