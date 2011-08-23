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

	/*public static boolean isNetworkAvailable(Activity paramActivity)
	  {
	    paramActivity = (ConnectivityManager)paramActivity.getApplicationContext().getSystemService("connectivity");
	    int i;
	    if (paramActivity == null)
	      i = 0;
	    while (true)
	    {
	      return i;
	      NetworkInfo[] arrayOfNetworkInfo = paramActivity.getAllNetworkInfo();
	      if (arrayOfNetworkInfo != null)
	      {
	        int k = 0;
	        while (true)
	        {
	          int m = arrayOfNetworkInfo.length;
	          if (k >= m)
	            break label72;
	          NetworkInfo.State localState1 = arrayOfNetworkInfo[k].getState();
	          NetworkInfo.State localState2 = NetworkInfo.State.CONNECTED;
	          if (localState1 == localState2)
	          {
	            j = 1;
	            break;
	          }
	          k += 1;
	        }
	      }
	      label72: int j = 0;
	    }
	  }*/

	public static int px2dip(Context paramContext, float paramFloat) {
		float f = paramContext.getResources().getDisplayMetrics().density;
		return (int) (paramFloat / f + 0.5F);
	}
}
