package com.rent.thread;

import org.json.JSONObject;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

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

	  // ERROR //
	  public void run()
	  {
	    // Byte code:
	    //   0: getstatic 17	com/songshulin/android/common/location/GoogleJSonLocationThread:TAG	Ljava/lang/String;
	    //   3: astore_1
	    //   4: new 58	java/lang/StringBuilder
	    //   7: dup
	    //   8: invokespecial 59	java/lang/StringBuilder:<init>	()V
	    //   11: ldc 61
	    //   13: invokevirtual 65	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
	    //   16: astore_2
	    //   17: aload_0
	    //   18: getfield 29	com/songshulin/android/common/location/GoogleJSonLocationThread:object	Lorg/json/JSONObject;
	    //   21: invokevirtual 71	org/json/JSONObject:toString	()Ljava/lang/String;
	    //   24: astore_3
	    //   25: aload_2
	    //   26: aload_3
	    //   27: invokevirtual 65	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
	    //   30: invokevirtual 72	java/lang/StringBuilder:toString	()Ljava/lang/String;
	    //   33: astore 4
	    //   35: aload_1
	    //   36: aload 4
	    //   38: invokestatic 78	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
	    //   41: istore 5
	    //   43: new 80	org/apache/http/impl/client/DefaultHttpClient
	    //   46: dup
	    //   47: invokespecial 81	org/apache/http/impl/client/DefaultHttpClient:<init>	()V
	    //   50: astore 6
	    //   52: aload_0
	    //   53: getfield 25	com/songshulin/android/common/location/GoogleJSonLocationThread:mUrl	Ljava/lang/String;
	    //   56: astore 7
	    //   58: new 83	org/apache/http/client/methods/HttpPost
	    //   61: dup
	    //   62: aload 7
	    //   64: invokespecial 86	org/apache/http/client/methods/HttpPost:<init>	(Ljava/lang/String;)V
	    //   67: astore 8
	    //   69: aload_0
	    //   70: getfield 29	com/songshulin/android/common/location/GoogleJSonLocationThread:object	Lorg/json/JSONObject;
	    //   73: invokevirtual 71	org/json/JSONObject:toString	()Ljava/lang/String;
	    //   76: astore 9
	    //   78: new 88	org/apache/http/entity/StringEntity
	    //   81: dup
	    //   82: aload 9
	    //   84: invokespecial 89	org/apache/http/entity/StringEntity:<init>	(Ljava/lang/String;)V
	    //   87: astore 10
	    //   89: aload 8
	    //   91: aload 10
	    //   93: invokevirtual 93	org/apache/http/client/methods/HttpPost:setEntity	(Lorg/apache/http/HttpEntity;)V
	    //   96: aload 6
	    //   98: aload 8
	    //   100: invokeinterface 99 2 0
	    //   105: invokeinterface 105 1 0
	    //   110: invokeinterface 111 1 0
	    //   115: astore 11
	    //   117: new 113	java/io/InputStreamReader
	    //   120: dup
	    //   121: aload 11
	    //   123: invokespecial 116	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
	    //   126: astore 12
	    //   128: new 118	java/io/BufferedReader
	    //   131: dup
	    //   132: aload 12
	    //   134: invokespecial 121	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
	    //   137: astore 8
	    //   139: new 123	java/lang/StringBuffer
	    //   142: dup
	    //   143: invokespecial 124	java/lang/StringBuffer:<init>	()V
	    //   146: astore 6
	    //   148: aload 8
	    //   150: invokevirtual 127	java/io/BufferedReader:readLine	()Ljava/lang/String;
	    //   153: astore 13
	    //   155: aload 13
	    //   157: ifnull +40 -> 197
	    //   160: aload 6
	    //   162: aload 13
	    //   164: invokevirtual 130	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
	    //   167: astore 14
	    //   169: aload 8
	    //   171: invokevirtual 127	java/io/BufferedReader:readLine	()Ljava/lang/String;
	    //   174: astore 15
	    //   176: aload 15
	    //   178: astore 13
	    //   180: goto -25 -> 155
	    //   183: invokevirtual 133	java/io/UnsupportedEncodingException:printStackTrace	()V
	    //   186: aload_0
	    //   187: getfield 27	com/songshulin/android/common/location/GoogleJSonLocationThread:handler	Landroid/os/Handler;
	    //   190: iconst_1
	    //   191: invokevirtual 139	android/os/Handler:sendEmptyMessage	(I)Z
	    //   194: istore 16
	    //   196: return
	    //   197: getstatic 17	com/songshulin/android/common/location/GoogleJSonLocationThread:TAG	Ljava/lang/String;
	    //   200: astore 17
	    //   202: aload 6
	    //   204: invokevirtual 140	java/lang/StringBuffer:toString	()Ljava/lang/String;
	    //   207: astore 18
	    //   209: aload 17
	    //   211: aload 18
	    //   213: invokestatic 78	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
	    //   216: istore 19
	    //   218: aload_0
	    //   219: getfield 27	com/songshulin/android/common/location/GoogleJSonLocationThread:handler	Landroid/os/Handler;
	    //   222: astore 20
	    //   224: aload 6
	    //   226: invokevirtual 140	java/lang/StringBuffer:toString	()Ljava/lang/String;
	    //   229: astore 21
	    //   231: aload_0
	    //   232: aload 21
	    //   234: invokevirtual 142	com/songshulin/android/common/location/GoogleJSonLocationThread:getMessageByContent	(Ljava/lang/String;)Landroid/os/Message;
	    //   237: astore 22
	    //   239: aload 20
	    //   241: aload 22
	    //   243: invokevirtual 146	android/os/Handler:sendMessage	(Landroid/os/Message;)Z
	    //   246: istore 23
	    //   248: goto -52 -> 196
	    //   251: invokevirtual 147	org/apache/http/client/ClientProtocolException:printStackTrace	()V
	    //   254: goto -58 -> 196
	    //   257: invokevirtual 148	java/io/IOException:printStackTrace	()V
	    //   260: goto -64 -> 196
	    //   263: invokevirtual 149	java/lang/Exception:printStackTrace	()V
	    //   266: goto -70 -> 196
	    //
	    // Exception table:
	    //   from	to	target	type
	    //   69	96	183	java/io/UnsupportedEncodingException
	    //   96	176	251	org/apache/http/client/ClientProtocolException
	    //   197	248	251	org/apache/http/client/ClientProtocolException
	    //   96	176	257	java/io/IOException
	    //   197	248	257	java/io/IOException
	    //   96	176	263	java/lang/Exception
	    //   197	248	263	java/lang/Exception
	  }
	  
}
