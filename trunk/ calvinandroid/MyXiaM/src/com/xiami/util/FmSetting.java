package com.xiami.util;

import java.io.Serializable;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

public class FmSetting implements Serializable{

	public static final int MESSAGE_NO_WIFI = 4;
	  public static final int MESSAGE_OFFILINE = 3;
	  public static final int MESSAGE_ONLINE = 1;
	  public static final int MESSAGE_SETTING_OFFILINE = 2;
	  public static final int SHOW_MESSAGE_WHEN_NO_WIFI = 5;
	  private static final long serialVersionUID = 3764914703415320724L;
	  int net3g = 1;
	  int offline;
	  int showMessageWhenNoWifi = 1;
	  int wifi;

	  public boolean checkNetwork(Context paramContext, FmSetting paramFmSetting, boolean paramBoolean)
	  {
	    NetworkInfo localNetworkInfo = ((ConnectivityManager)paramContext.getSystemService("connectivity")).getActiveNetworkInfo();
	    boolean i;
	    if (paramFmSetting.isForceOffline() == 1)
	    {
	      if (paramBoolean)
	        NotificationsUtil.ToastLong(paramContext, "Test");
	      i = false;
	    }
	    else
	    {
	      if (localNetworkInfo != null)
	      {
	        int j = localNetworkInfo.getType();
	        if (((1 == j) && (paramFmSetting.getWifi() == 1)) || ((localNetworkInfo.getType() == 0) && (paramFmSetting.getNet3g() == 1)))
	        {
	          i = true;
	        } else {
		        if (paramBoolean)
		          NotificationsUtil.ToastShort(paramContext, "Test");
		        i = false;
	        }
	      }
	      if (paramBoolean)
	        NotificationsUtil.ToastLong(paramContext, "Test");
	      i = false;
	    }
	    return i;
	  }

	  public int getNet3g()
	  {
	    return this.net3g;
	  }

	  public int getShowMessageWhenNoWifi()
	  {
	    return this.showMessageWhenNoWifi;
	  }

	  public int getWifi()
	  {
	    return this.wifi;
	  }

	  public int isAvaliableNetwork(Context paramContext)
	  {
	    ConnectivityManager localConnectivityManager = (ConnectivityManager)paramContext.getSystemService("connectivity");
	    NetworkInfo localNetworkInfo1 = localConnectivityManager.getActiveNetworkInfo();
	    NetworkInfo localNetworkInfo2 = localConnectivityManager.getNetworkInfo(0);
	    int i;
	    if (isForceOffline() == 1)
	      i = 2;
	    else
	    {
	      if (localNetworkInfo1 != null)
	      {
	        String str1 = String.valueOf(localNetworkInfo1.getTypeName());
	        StringBuilder localStringBuilder1 = new StringBuilder(str1);
	        int j = getWifi();
	        String str2 = String.valueOf(j);
	        int k = Log.d("xiami.network:MESSAGE_ONLINE", str2);
	        int m = localNetworkInfo1.getType();
	        if ((1 == m) && (getWifi() == 1))
	        {
	          i = 1;
	        } else {
		        String str3 = String.valueOf(localNetworkInfo1.getTypeName());
		        StringBuilder localStringBuilder2 = new StringBuilder(str3);
		        int n = getNet3g();
		        String str4 = String.valueOf(n);
		        int i1 = Log.d("xiami.network:MESSAGE_ONLINE", str4);
		        if ((localNetworkInfo1.getType() == 0) && (getNet3g() == 1))
		        {
		          i = 1;
		        }
	        }
	      }
	      else
	      {
	        int i2 = Log.d("xiami.network:", "MESSAGE_OFFILINE");
	        i = 3;
	      }
	      int i3 = Log.d("xiami.network:MESSAGE_OFFILINE", "MESSAGE_OFFILINE");
	      i = 3;
	    }
	    return i;
	  }

	  public int isForceOffline()
	  {
	    return this.offline;
	  }

	  public void setForceOffline(int paramInt)
	  {
	    this.offline = paramInt;
	  }

	  public void setNet3g(int paramInt)
	  {
	    this.net3g = paramInt;
	  }

	  public void setShowMessageWhenNoWifi(int paramInt)
	  {
	    this.showMessageWhenNoWifi = paramInt;
	  }

	  public void setWifi(int paramInt)
	  {
	    this.wifi = paramInt;
	  }
}
