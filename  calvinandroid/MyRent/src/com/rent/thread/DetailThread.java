package com.rent.thread;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.os.Message;

import com.rent.handler.ThreadHandler;

public class DetailThread extends AbstractThread {

	private final String URL = "http://api.99fang.com/core/1/item_detail?channel=rent&item_id=%d";
	  private ThreadHandler mHandler;
	  private Long mId;
	  private String mUrl;

	  public DetailThread(ThreadHandler paramThreadHandler, long paramLong)
	  {
	    super(paramThreadHandler);
	    this.mHandler = paramThreadHandler;
	    Object[] arrayOfObject = new Object[1];
	    Long localLong1 = Long.valueOf(paramLong);
	    arrayOfObject[0] = localLong1;
	    String str = String.format("http://api.99fang.com/core/1/item_detail?channel=rent&item_id=%d", arrayOfObject);
	    this.mUrl = str;
	    Long localLong2 = Long.valueOf(paramLong);
	    this.mId = localLong2;
	  }

	  protected Message getMessageByContent(String paramString)
	  {
	    Bundle localBundle = new Bundle();
	    localBundle.putString("handler_data", paramString);
	    long l = this.mId.longValue();
	    localBundle.putLong("mId", l);
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
	      if (!"success".equals(str)) {
	    	  return false;
	      }
	      return true;
	    }
	    return false;
	  }

	  public void run()
	  {
	    String str1 = this.mUrl;
	    super.setUrl(str1);
	    try
	    {
	      String str2 = executeGet();
	      if (isSuccessful(str2))
	      {
	        ThreadHandler localThreadHandler = this.mHandler;
	        Message localMessage = getMessageByContent(str2);
	        boolean bool1 = localThreadHandler.sendMessage(localMessage);
	      } else
	      {
	        boolean bool2 = this.mHandler.sendEmptyMessage(3);
	      }
	    }
	    catch (Exception localException)
	    {
	        this.mHandler.sendEmptyMessage(3);
	    }
	  }
	  
}
