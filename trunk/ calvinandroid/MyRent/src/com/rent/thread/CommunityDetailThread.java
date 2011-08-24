package com.rent.thread;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.rent.Rent;

public class CommunityDetailThread extends AbstractThread{

	private String mUrl = "http://api.99fang.com/core/1/comm_detail?channel=rent&city=%s&name=%s";

	  public CommunityDetailThread(Handler paramHandler, String paramString1, String paramString2)
	  {
	    super(paramHandler);
	    String str1 = this.mUrl;
	    Object[] arrayOfObject = new Object[2];
	    arrayOfObject[0] = paramString1;
	    arrayOfObject[1] = paramString2;
	    String str2 = String.format(str1, arrayOfObject);
	    this.mUrl = str2;
	  }

	  protected Message getMessageByContent(String paramString)
	  {
	    Bundle localBundle = new Bundle();
	    localBundle.putString("data", paramString);
	    Message localMessage = new Message();
	    localMessage.setData(localBundle);
	    return localMessage;
	  }

	  protected boolean isSuccessful(String paramString)
	    throws JSONException
	  {
	    if (paramString != null)
	    {
	      String str = new JSONObject(paramString).getString("message");
	      if (!"success".equals(str))
	    	  return false;
	      return true;
	    }
	    return false;
	  }

	  public void run()
	  {
	    String str1 = this.mUrl;
	    super.setUrl(str1);
	    StringBuilder localStringBuilder = new StringBuilder().append("Url=");
	    String str2 = this.mUrl;
	    String str3 = str2;
	    Rent.MyLog("CommunityDetailThread.run()", str3);
	    try
	    {
	      String str4 = executeGet();
	      if (isSuccessful(str4))
	      {
	        Handler localHandler = this.handler;
	        Message localMessage = getMessageByContent(str4);
	        boolean bool1 = localHandler.sendMessage(localMessage);
	      } else {
	        boolean bool2 = this.handler.sendEmptyMessage(1);
	      }
	    }
	    catch (Exception localException)
	    {
	        boolean bool3 = this.handler.sendEmptyMessage(1);
	    }
	  }
}
