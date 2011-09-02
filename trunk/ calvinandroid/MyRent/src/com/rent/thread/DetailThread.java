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
//	      String str2 = executeGet();
	    String str2 = "{\"message\": \"success\", \"data\": {\"contact_path\": \"\", \"publish_time\": 1313113230, \"room\": \"3\u5ba41\u5385\", \"images\": [\"http://client.fstat.net/origin/20110812/51/120/18283596147282901043.jpg\", \"http://client.fstat.net/origin/20110812/46/35/17483151174682288942.jpg\", \"http://client.fstat.net/origin/20110812/28/19/3017540207780827932.jpg\", \"http://client.fstat.net/origin/20110812/53/14/15416332827625852469.jpg\"], \"title\": \"\u6700\u7cbe\u5f69\u623f\u6e90\u53cc\u9526\u56ed\u4e2d\u88c5\u5168\u9f50\u59273\u5c45\u53ea\u89813300\u597d\u623f\u5b50\u6025\", \"price\": 3300, \"floor\": \"\u4e2d\u95f4\u697c\u5c42/19\u5c42\", \"rent_type\": 0, \"abstract\": \"1.\u623f\u5b50\u662f\u53cc\u9526\u56ed\u5c0f\u533a\u7684\uff0c\u4e2d\u88c5\u5168\u9f503\u5c45\u5ba4\uff0c\u5bb6\u5177\u5bb6\u7535\u5168\u9f50\u7684.20\u53f7\u79df\u6237\u642c\u8d70\uff0c\u53ef\u4ee5\u63d0\u524d\u770b\u623f2.\u623f\u5b50\u662f\u4e2d\u7b49\u88c5\u4fee\u7684\uff0c\u5bb6\u5177\u5bb6\u7535\u5168\u5168\u9f50\uff0c2\u4e2a\u5367\u5ba4\u662f\u671d\u5357\u7684\uff0c\u4e00\u4e2a\u5367\u5ba4\u671d\u5317.\u91cd\u8981\u7684\u662f\u623f\u5b50\u7279\u522b\u4fbf\u5b9c\u554a3.\u623f\u5b50\u7edd\u5bf9\u5e72\u51c0\u6574\u6d01\uff0c\u53ea\u8981\u60a8\u6562\u770b\uff0c\u5c31\u4fdd\u8bc1\u60a8\u60f3\u8212\u9002\u5165\u4f4f4.\u5c0f\u533a\u5bf9\u9762\u662f\u77f3\u666f\u5c71\u5e02\u653f\u5e9c\uff0c\u7d27\u90bb\u4e07\u8fbe\u5e7f\u573a\uff0c\u9644\u8fd1\u6709\u7f8e\u5ec9\u7f8e\u8d85\u5e02\uff0c\u94f6\u6cb3\u5c0f\u5b66\uff0c\u534a\u6708\u516c\u56ed\u7b49\u7b49\u30025.\u9644\u8fd1\u6709\u516b\u5b9d\u5c71\u5730\u94c1\u4e0e\u516b\u89d2\u5730\u94c1\u7ad9\uff0c\u65b9\u4fbf\u5feb\u6377\u3002\u4e3a\u60a8\u7684\u51fa\u884c\u7edd\u5bf9\u8282\u7701\u5f88\u591a\u7684\u65f6\u95f46.\u6211\u662f\u4e1c\u65b9\u6cf0\u548c\u7f6e\u4e1a\u987e\u95ee\u738b\u7389\u971e\uff0c\u6b22\u8fce\u60a8\u7684\u81f4\u7535\uff0c\u6211\u4f1a\u7aed\u8bda\u4e3a\u60a8\u670d\u52a1\u300218210019945\u62161305152****\", \"id\": 104080020, \"area\": 90, \"agency_name\": \"\u5317\u4eac\u4e1c\u65b9\u6cf0\u548c\u5b8f\u8fdc\u623f\u5730\u4ea7\u7ecf\u7eaa\u516c\u53f8\", \"agency_status\": \"\u4e2d\u4ecb\", \"longitude\": 11622176, \"address\": \"\u94f6\u6cb3\u5927\u8857\u4e0e\u9c81\u8c37\u8def\u4ea4\u6c47\u5904.\u77f3\u666f\u5c71\u533a\u653f\u5e9c\u5bf9\u9762\", \"latitude\": 3990214, \"contact_person\": \"\u738b\u7389\u971e\", \"from_site\": \"58\u540c\u57ce\u623f\u4ea7\", \"thumbnail\": \"http://client.fstat.net/thumbnails/20110812/51/120/18283596147282901043.jpg\", \"phone\": \"13051529311\"}}";
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
