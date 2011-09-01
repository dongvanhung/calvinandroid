package com.rent;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

public class UIUtils {

	public static float dip2Px(Context paramContext, float paramFloat) {
		return paramContext.getResources().getDisplayMetrics().density
				* paramFloat + 0.5F;
	}

	public static void displayLongTimeToast(Context paramContext, int paramInt) {
		String str = paramContext.getString(paramInt);
		Toast.makeText(paramContext, str, 1).show();
	}

	public static void displayLongTimeToast(Context paramContext,
			String paramString) {
		Toast.makeText(paramContext, paramString, 1).show();
	}

	public static void displayToast(Context paramContext, int paramInt) {
		String str = paramContext.getString(paramInt);
		Toast.makeText(paramContext, str, 0).show();
	}

	public static void displayToast(Context paramContext, String paramString) {
		Toast.makeText(paramContext, paramString, 0).show();
	}

	public static boolean isNetworkAvailable(Activity paramActivity)
	  {
		ConnectivityManager cm = (ConnectivityManager)paramActivity.getApplicationContext().getSystemService("connectivity");
	    int i;
	    if (paramActivity == null)
	      return false;
	    else {
	      NetworkInfo[] arrayOfNetworkInfo = cm.getAllNetworkInfo();
	      if (arrayOfNetworkInfo != null)
	      {
	       for (int j = 0; j < arrayOfNetworkInfo.length; j++) {
	    	   NetworkInfo.State localState1 = arrayOfNetworkInfo[j].getState();
		          NetworkInfo.State localState2 = NetworkInfo.State.CONNECTED;
		          if (localState1 == localState2)
		          {
		        	  return true;
		          }
	       }
	      }
	    }
	    return false;
	  }

	public static int px2dip(Context paramContext, float paramFloat) {
		float f = paramContext.getResources().getDisplayMetrics().density;
		return (int) (paramFloat / f + 0.5F);
	}
}
