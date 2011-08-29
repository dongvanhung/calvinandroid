package com.rent.thread;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.params.HttpClientParams;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.util.EntityUtils;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

public class AddressToLocationThread extends Thread{

	 private final int TIMEOUT = 6000;
	  private Handler mHandler;
	  private String mUrl = "http://maps.google.cn/maps/geo?q=%s&key=0RSTRVxpCVRGfIJLY5rlZ5gpQz7bvaLAyK7y_0w";

	  public AddressToLocationThread(Handler paramHandler, String paramString)
	  {
	    this.mHandler = paramHandler;
	    String str1 = this.mUrl;
	    Object[] arrayOfObject = new Object[1];
	    arrayOfObject[0] = paramString;
	    String str2 = String.format(str1, arrayOfObject);
	    this.mUrl = str2;
	  }

	  public void run()
	  {
	    Looper.prepare();
	    try
	    {
	      BasicHttpParams localBasicHttpParams = new BasicHttpParams();
	      HttpConnectionParams.setSoTimeout(localBasicHttpParams, 6000);
	      HttpConnectionParams.setConnectionTimeout(localBasicHttpParams, 6000);
	      HttpClientParams.setRedirecting(localBasicHttpParams, false);
	      DefaultHttpClient localDefaultHttpClient = new DefaultHttpClient(localBasicHttpParams);
	      String str1 = this.mUrl;
	      HttpGet localHttpGet = new HttpGet(str1);
	      String str2 = EntityUtils.toString(localDefaultHttpClient.execute(localHttpGet).getEntity(), "UTF-8");
	      Bundle localBundle = new Bundle();
	      localBundle.putString("data", str2);
	      Message localMessage = new Message();
	      localMessage.setData(localBundle);
	      boolean bool1 = this.mHandler.sendMessage(localMessage);
	      return;
	    }
	    catch (Exception localException)
	    {
	        boolean bool2 = this.mHandler.sendEmptyMessage(1);
	    }
	    finally
	    {
	      Looper.myLooper().quit();
	    }
	  }
	  
}
