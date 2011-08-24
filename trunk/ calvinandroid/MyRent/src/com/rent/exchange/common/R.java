package com.rent.exchange.common;

import android.content.Context;

public class R {

	 static Class R_anim;
	  public static Class R_drawable;
	  public static Class R_id = null;
	  public static Class R_layout;
	  public static Context mContext;

	  static
	  {
	    R_drawable = null;
	    R_layout = null;
	    R_anim = null;
	  }

	  public static int anim(String paramString)
	  {
		  int i = 0;
	    try
	    {
	      i = R_anim.getField(paramString).getInt(paramString);
	    }
	    catch (Exception localException)
	    {
	      while (true)
	      {
	        localException.printStackTrace();
	        i = -1;
	      }
	    }
	    return i;
	  }

	  public static int drawable(String paramString)
	  {
		  int i  = 0;
	    try
	    {
	      i = R_drawable.getField(paramString).getInt(paramString);
	    }
	    catch (Exception localException)
	    {
	      while (true)
	      {
	        localException.printStackTrace();
	        i = -1;
	      }
	    }
	    return i;
	  }

	  public static int id(String paramString)
	  {
		  int i = 0;
	    try
	    {
	      i = R_id.getField(paramString).getInt(paramString);
	    }
	    catch (Exception localException)
	    {
	      while (true)
	      {
	        localException.printStackTrace();
	        i = -1;
	      }
	    }
	    return i;
	  }

	  static void init()
	  {
	    try
	    {
	      String str1 = String.valueOf(mContext.getPackageName());
	      R_drawable = Class.forName(str1 + ".R$drawable");
	      String str2 = String.valueOf(mContext.getPackageName());
	      R_layout = Class.forName(str2 + ".R$layout");
	      String str3 = String.valueOf(mContext.getPackageName());
	      R_id = Class.forName(str3 + ".R$id");
	      String str4 = String.valueOf(mContext.getPackageName());
	      R_anim = Class.forName(str4 + ".R$anim");
	      return;
	    }
	    catch (ClassNotFoundException localClassNotFoundException)
	    {
	      while (true)
	        localClassNotFoundException.printStackTrace();
	    }
	  }

	  public static void init(Context paramContext)
	  {
	    mContext = paramContext;
	    init();
	  }

	  public static int layout(String paramString)
	  {
		  int i = 0;
	    try
	    {
	      i = R_layout.getField(paramString).getInt(paramString);
	    }
	    catch (Exception localException)
	    {
	      while (true)
	      {
	        localException.printStackTrace();
	        i = -1;
	      }
	    }
	    return i ;
	  }
}
