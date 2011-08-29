package com.rent.thread;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

public class GetMarsLocationThread extends com.rent.location.AbstractThread{

	String mUrl = "http://api.99fang.com/service/1/fix_map_offset?longitude=%d&latitude=%d";

	  public GetMarsLocationThread(Context paramContext, Handler paramHandler, double paramDouble1, double paramDouble2, String paramString1, String paramString2)
	  {
	    super(paramContext, paramHandler, paramString1, paramString2);
	    int i = (int)(paramDouble1 * 0.0F);
	    int j = (int)(paramDouble2 * 0.0F);
	    String str1 = this.mUrl;
	    Object[] arrayOfObject = new Object[2];
	    Integer localInteger1 = Integer.valueOf(j);
	    arrayOfObject[0] = localInteger1;
	    Integer localInteger2 = Integer.valueOf(i);
	    arrayOfObject[1] = localInteger2;
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
	        Handler localHandler = this.handler;
	        Message localMessage = getMessageByContent(str2);
	        boolean bool1 = localHandler.sendMessage(localMessage);
	      } else {
	        boolean bool2 = this.handler.sendEmptyMessage(1);
	      }
	    }
	    catch (Exception localException){
	    	localException.printStackTrace();
	       this.handler.sendEmptyMessage(1);
	    }
	  }
}
