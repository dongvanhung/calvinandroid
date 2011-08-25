package com.rent.exchange.common;

import javax.microedition.khronos.opengles.GL10;

import android.content.Context;
import android.content.pm.PackageManager;
import android.util.Log;

public class UmengHelper {

	public static boolean checkPermission(Context paramContext, String paramString)
	  {
	    PackageManager localPackageManager = paramContext.getPackageManager();
	    String str = paramContext.getPackageName();
	    if (localPackageManager.checkPermission(paramString, str) != 0)
	    	return true;
	    return false;
	  }

	  // ERROR //
	  public static String getCPU()
	  {
	    return "";
	  }

	  public static String[] getGPU(GL10 paramGL10)
	  {
	    try
	    {
	      String[] arrayOfString1 = new String[2];
	      String str1 = paramGL10.glGetString(7936);
	      String str2 = paramGL10.glGetString(7937);
	      arrayOfString1[0] = str1;
	      arrayOfString1[1] = str2;
	      return arrayOfString1;
	    }
	    catch (Exception localException)
	    {
	      while (true)
	      {
	        int i = Log.e("MobclickAgent", "Could not read gpu infor:", localException);
	        String[] arrayOfString2 = new String[0];
	      }
	    }
	  }
}
