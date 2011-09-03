package com.rent.thread;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.params.HttpClientParams;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.util.EntityUtils;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.rent.PreferenceUtils;

public class AutoCompleteQueryThread extends Thread{

	private Handler mHandler;
	  private String mUrl = "http://www.99fang.com/suggestion/search.fcgi?count=10&ch=Fang&format=json&callback=&city=%s&q=%s";

	  public AutoCompleteQueryThread(Context paramContext, Handler paramHandler, String paramString)
	  {
	    String str1 = PreferenceUtils.getCurrentCityName(paramContext);
	    String str2 = this.mUrl;
	    Object[] arrayOfObject = new Object[2];
	    arrayOfObject[0] = str1;
	    String str3 = paramString.trim();
	    arrayOfObject[1] = str3;
	    String str4 = String.format(str2, arrayOfObject);
	    this.mUrl = str4;
	    this.mHandler = paramHandler;
	  }

	  private Message getMessageByContent(String paramString)
	  {
	    Bundle localBundle = new Bundle();
	    localBundle.putString("data", paramString);
	    Message localMessage = new Message();
	    localMessage.setData(localBundle);
	    return localMessage;
	  }

	  public void run()
	  {
	    try
	    {
	      BasicHttpParams localBasicHttpParams = new BasicHttpParams();
	      HttpConnectionParams.setSoTimeout(localBasicHttpParams, 30000);
	      HttpConnectionParams.setConnectionTimeout(localBasicHttpParams, 30000);
	      HttpClientParams.setRedirecting(localBasicHttpParams, false);
	      DefaultHttpClient localDefaultHttpClient = new DefaultHttpClient(localBasicHttpParams);
	      String str1 = this.mUrl;
//	      HttpGet localHttpGet = new HttpGet(str1);
//	      String str2 = EntityUtils.toString(localDefaultHttpClient.execute(localHttpGet).getEntity(), "UTF-8");
	      String str2 = "{\"ResultSet\": {\"count\": 10, \"status\": \"success\", \"sQuery\": \"\u6768\u6d66\", \"g_nWorkingIndex\": 0, \"callback\": \"\", \"nTotalResults\": 10, \"Result\": [{\"nQuantity\": 14038, \"sKey\": \"\u6768\u6d66\"}, {\"nQuantity\": 75, \"sKey\": \"\u6768\u6d66\u53cc\u9633\"}, {\"nQuantity\": 54, \"sKey\": \"\u6768\u6d66\u6b23\u82d1\"}, {\"nQuantity\": 17, \"sKey\": \"\u6768\u6d66\u6b23\u56ed\"}, {\"nQuantity\": 9, \"sKey\": \"\u6768\u6d66\u516c\u5bd3\"}, {\"nQuantity\": 7, \"sKey\": \"\u6768\u6d66\u5927\u53a6\"}, {\"nQuantity\": 6, \"sKey\": \"\u6768\u6d66\u5c0f\u533a\"}, {\"nQuantity\": 1, \"sKey\": \"\u6768\u6d66\u516c\u5b89\u5927\u697c\"}, {\"nQuantity\": 0, \"sKey\": \"\u6768\u6d66\u5927\u697c\"}, {\"nQuantity\": 0, \"sKey\": \"\u6768\u6d66\u533a\u533a\u5e9c\u529e\u516c\u5ba4\"}], \"channel\": \"Fang\"}}";
	      Handler localHandler = this.mHandler;
	      Message localMessage = getMessageByContent(str2);
	      boolean bool1 = localHandler.sendMessage(localMessage);
	      return;
	    }
	    catch (Exception localException)
	    {
	      while (true)
	      {
	        boolean bool2 = this.mHandler.sendEmptyMessage(1);
	        String str3 = localException.toString();
	        int i = Log.e("ff", str3);
	      }
	    }
	  }
}
