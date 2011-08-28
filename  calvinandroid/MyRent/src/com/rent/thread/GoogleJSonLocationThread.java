package com.rent.thread;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

public class GoogleJSonLocationThread extends Thread{

	public static String TAG = "GoogleJSonLocationThread";
	  protected final Handler handler;
	  String mUrl = "http://www.google.com/loc/json";
	  JSONObject object;

	  public GoogleJSonLocationThread(Handler paramHandler, JSONObject paramJSONObject)
	  {
	    this.handler = paramHandler;
	    this.object = paramJSONObject;
	  }

	  protected Message getMessageByContent(String paramString)
	  {
	    Bundle localBundle = new Bundle();
	    localBundle.putString("data", paramString);
	    Message localMessage = new Message();
	    localMessage.setData(localBundle);
	    return localMessage;
	  }

	  public void run()
	  {
	        Object obj1;
	        String s = TAG;
	        try {
				StringBuilder stringbuilder = (new StringBuilder()).append("requestLocation: ");
				String s1 = object.toString();
				String s2 = stringbuilder.append(s1).toString();
				Log.d(s, s2);
				DefaultHttpClient defaultHttpClient = new DefaultHttpClient();
				String s3 = mUrl;
				obj1 = new HttpPost(s3);
				String s4 = object.toString();
				StringEntity stringentity = new StringEntity(s4);
				((HttpPost) (obj1)).setEntity(stringentity);
				String s5;
				java.io.InputStream inputstream = defaultHttpClient.execute(((org.apache.http.client.methods.HttpUriRequest) (obj1))).getEntity().getContent();
				InputStreamReader inputstreamreader = new InputStreamReader(inputstream);
				obj1 = new BufferedReader(inputstreamreader);
				StringBuffer obj = new StringBuffer();
				while((s5 = ((BufferedReader) (obj1)).readLine()) != null) {
					obj.append(s5);
				}
				String s7 = TAG;
			    String s8 = ((StringBuffer) (obj)).toString();
			    int j = Log.d(s7, s8);
			    Handler handler1 = handler;
			    String s9 = ((StringBuffer) (obj)).toString();
			    Message message = getMessageByContent(s9);
			    boolean flag1 = handler1.sendMessage(message);
			} catch (Exception e) {
				boolean flag = handler.sendEmptyMessage(1);
			}
	  }
	  
}
