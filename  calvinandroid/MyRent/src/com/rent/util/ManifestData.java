package com.rent.util;

import android.content.Context;
import android.content.pm.PackageManager;

public class ManifestData {

	 public static Object get(Context paramContext, String paramString)
	  {
	    return readKey(paramContext, paramString);
	  }

	  public static Boolean getBoolean(Context paramContext, String paramString)
	  {
	    return (Boolean)readKey(paramContext, paramString);
	  }

	  public static int getInt(Context paramContext, String paramString)
	  {
	    return ((Integer)readKey(paramContext, paramString)).intValue();
	  }

	  public static String getString(Context paramContext, String paramString)
	  {
	    return (String)readKey(paramContext, paramString);
	  }

	  private static Object readKey(Context paramContext, String paramString)
	  {
		  Object localObject1 = null;
	    try
	    {
	      PackageManager localPackageManager = paramContext.getPackageManager();
	      String str = paramContext.getPackageName();
	      localObject1 = localPackageManager.getApplicationInfo(str, 128).metaData.get(paramString);
	    }
	    catch (PackageManager.NameNotFoundException e)
	    {
	    	e.printStackTrace();
	    }
	    return localObject1;
	  }
	  
}
